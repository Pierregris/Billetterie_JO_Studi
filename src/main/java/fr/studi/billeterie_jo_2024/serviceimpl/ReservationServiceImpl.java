package fr.studi.billeterie_jo_2024.serviceimpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Offre;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.repository.ReservationRepository;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.QRCodeService;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.status.ResultatGetPanier;
import fr.studi.billeterie_jo_2024.status.StatusReservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	EvenementRepository evenementRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	OffreRepository offreRepository;

	@Autowired
	QRCodeService qrCodeService;

	@Override
	public void createReservation(Reservation reservation) {
		reservationRepository.save(reservation);
	}

	@Override
	public Reservation ajouterAuPanier(AAjouterAuPanierDTO panierDTO) {
		// On crée un nouvel objet panier
		Reservation panier = new Reservation();
		panier.setStatusReservation(StatusReservation.PANIER);
		// On lui attribue l'heure à laquelle elle a été ajoutée au panier, pour pouvoir
		// déterminer son heure d'expiration
		panier.setDate(LocalDateTime.now());
		panier.setValidite(panier.getDate().plusHours(1));
		// On attribue les paramètres envoyés par la request
		panier.setMontant(panierDTO.getMontant());
		Offre offre = offreRepository.findById(panierDTO.getNomOffre()).orElse(null);
		panier.setOffreChoisie(offre);
		Evenement evenement = evenementRepository.findById(panierDTO.getEvenement_id()).orElse(null);
		panier.setEvenement(evenement);
		// On attribue la réservation à l'utilisateur connecté
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		panier.setUtilisateur(utilisateur);
		// On met à jour le nombre de billets disponibles
		evenement.setBilletsVendus(evenement.getBilletsVendus() + offre.getNbPlaces());
		evenementRepository.save(evenement);
		return panier;
	}

	@Override
	public ResultatGetPanier getPanier(Utilisateur utilisateur) {
		// On récupère l'ensemble des réservations de l'utilisateur connecté avec le
		// statut Panier
		List<Reservation> reservations = reservationRepository.findByUtilisateurAndStatusReservation(utilisateur,
				StatusReservation.PANIER);
		Boolean suppression = false;
		// On utilise un iterator pour supprimer les réservations expirées
		Iterator<Reservation> iterator = reservations.iterator();
		while (iterator.hasNext()) {
			Reservation reservation = iterator.next();
			if (reservation.getValidite().isBefore(LocalDateTime.now())) {
				this.supprimerDuPanier(reservation.getId());
				// Si une réservation est expirée on passe le paramètre suppression à true
				suppression = true;
				iterator.remove();
			}
		}
		return new ResultatGetPanier(reservations, suppression);
	}

	@Override
	public List<Reservation> getReservationsFinalisees(Utilisateur utilisateur) {
		return (reservationRepository.findByUtilisateurAndStatusReservation(utilisateur, StatusReservation.FINALISEE));

	}

	@Override
	public void supprimerDuPanier(Long reservation_id) {
		// On récupère la réservation passée en paramètre
		Reservation reservation = reservationRepository.findById(reservation_id).orElse(null);
		// On récupère l'événement associé
		Evenement evenement = reservation.getEvenement();
		// On remet en vente les billets supprimés
		evenement.setBilletsVendus(evenement.getBilletsVendus() - reservation.getOffreChoisie().getNbPlaces());
		// La réservation passe au statut Expirée
		reservation.setStatusReservation(StatusReservation.EXPIREE);
		reservationRepository.save(reservation);

	}

	@Override
	public void validerReservation(Long reservation_id) {
		// On valide la réservation passée en paramètre en la passant au statut
		// Finalisée
		Reservation reservation = reservationRepository.findById(reservation_id).orElse(null);
		reservation.setStatusReservation(StatusReservation.FINALISEE);
		reservationRepository.save(reservation);

	}

	@Override
	public void genererCleAchat(Long reservation_id) {
		// On génère un UUID Random pour la clé d'achat, que l'on assigne à la
		// réservation passée en paramètre
		Reservation reservation = reservationRepository.findById(reservation_id).orElse(null);
		reservation.setCléAchat(UUID.randomUUID());
		reservationRepository.save(reservation);
	}

	@Override
	public String genererQRCode(UUID cleAchat, UUID utilisateur_id, int width, int height) {
		// On génère un QR Code sur la base des informations passées en paramètres
		try {
			return qrCodeService.generateQRCodeBase64(width, height, cleAchat.toString() + utilisateur_id.toString());
		} catch (WriterException | IOException e) {
			return e.toString();
		}
	}

}
