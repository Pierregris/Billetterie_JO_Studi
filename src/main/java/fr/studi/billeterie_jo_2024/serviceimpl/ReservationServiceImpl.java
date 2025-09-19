package fr.studi.billeterie_jo_2024.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.repository.ReservationRepository;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.status.StatusReservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	EvenementRepository evenementRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Override
	public void createReservation(Reservation reservation) {
		reservationRepository.save(reservation);
	}

	@Override
	public Reservation ajouterAuPanier(AAjouterAuPanierDTO panierDTO) {
		Reservation panier = new Reservation();
		panier.setStatusReservation(StatusReservation.PANIER);
		panier.setDate(LocalDate.now());
		panier.setMontant(panierDTO.getMontant());
		panier.setOffreChoisie(panierDTO.getOffreChoisie());
		Evenement evenement = evenementRepository.findById(panierDTO.getEvenement_id()).orElse(null);
		panier.setEvenement(evenement);
		String mail = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByMail(mail).orElse(null);
		panier.setUtilisateur(utilisateur);
		evenement.setBilletsVendus(evenement.getBilletsVendus() + panierDTO.getOffreChoisie().nbPlaces());
		evenementRepository.save(evenement);
		return panier;
	}

	@Override
	public List<Reservation> getPanier(Utilisateur utilisateur) {
		return (reservationRepository.findByUtilisateurAndStatusReservation(utilisateur, StatusReservation.PANIER));

	}

	@Override
	public List<Reservation> getReservationsFinalisees(Utilisateur utilisateur) {
		return (reservationRepository.findByUtilisateurAndStatusReservation(utilisateur, StatusReservation.FINALISEE));

	}

	@Override
	public void validerPanier(List<Reservation> panier) {
		panier.forEach(reservation -> reservation.setStatusReservation(StatusReservation.FINALISEE));
	}

	@Override
	public void supprimerDuPanier(Long reservation_id) {
		Reservation reservation = reservationRepository.findById(reservation_id).orElse(null);
		Evenement evenement = reservation.getEvenement();
		evenement.setBilletsVendus(evenement.getBilletsVendus() - reservation.getOffreChoisie().nbPlaces());
		reservationRepository.deleteById(reservation_id);

	}

	@Override
	public void validerReservation(Long reservation_id) {
		Reservation reservation = reservationRepository.findById(reservation_id).orElse(null);
		reservation.setStatusReservation(StatusReservation.FINALISEE);
		reservationRepository.save(reservation);

	}

}
