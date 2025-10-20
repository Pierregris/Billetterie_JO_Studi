package fr.studi.billeterie_jo_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.dto.InfosPaiementDTO;
import fr.studi.billeterie_jo_2024.dto.QRCodeGenerationDTO;
import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.repository.ReservationRepository;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/apiReservation")
public class ReservationRestController {

	private final EvenementRepository evenementRepository;

	private final ReservationRepository reservationRepository;

	@Autowired
	ReservationService reservationService;

	ReservationRestController(ReservationRepository reservationRepository, EvenementRepository evenementRepository) {
		this.reservationRepository = reservationRepository;
		this.evenementRepository = evenementRepository;
	}

	@PostMapping("/ajouteraupanier")
	public void ajouterAuPanier(@RequestBody AAjouterAuPanierDTO aAjouterAuPanier) {
		// On crée la réservation sur la base des paramètres transmis par la requête
		// Post
		Reservation ajoutPanier = reservationService.ajouterAuPanier(aAjouterAuPanier);
		reservationService.createReservation(ajoutPanier);
	}

	@PostMapping("/placesrestantes")
	public int placesRestantes(@RequestParam Long evenement_id) {
		// Fonction pour renvoyer le nombre de places restantes pour un événement
		// transmis en paramètre
		Evenement evenement = evenementRepository.findById(evenement_id).orElse(null);
		return evenement.getCapaciteMax() - evenement.getBilletsVendus();
	}

	@PostMapping("/getnbelementspanier")
	public int nbElementsPanier() {
		// Fonction pour renvoyer le nombre d'éléments que contient le panier
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return reservationService.getPanier(utilisateur).getReservations().size();
	}

	@PostMapping("/supprimerdupanier")
	public void supprimerDuPanier(@RequestParam Long reservation_id) {
		// Fonction pour supprimer un élément du panier
		reservationService.supprimerDuPanier(reservation_id);
	}

	@PostMapping("/paiement")
	// On simule un paiement avec un temps d'attente de 5sec. Les informations
	// doivent être valides pour que le paiement réussisse
	public boolean simulerPaiement(@RequestBody @Valid InfosPaiementDTO infosPaiementDTO) throws InterruptedException {
		Thread.sleep(5000);
		return true;
	}

	@PostMapping("/validerPanier")
	public void validerPanier(@RequestBody List<Long> reservationListId) {
		// Chaque réservation contenue dans le panier est validée (statut finalisée,
		// voir la fonction validerReservation du service)
		reservationListId.forEach(reservation_id -> {
			reservationService.validerReservation(reservation_id);
		});
	}

	@PostMapping("/creerclesachat")
	public void creerClesAchat(@RequestBody List<Long> reservationListId) {
		// On génère une clé d'achat pour chaque réservation validée
		reservationListId.forEach(reservation_id -> {
			reservationService.genererCleAchat(reservation_id);
		});
	}

	@PostMapping("/genererqrcode")
	public String genererQRCode(@RequestBody QRCodeGenerationDTO qrCodeGenerationDTO) {
		Reservation reservation = reservationRepository.findById(qrCodeGenerationDTO.getReservation_id()).orElse(null);
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// On génère un QRCode sur la base des informations récupérées ci-dessus
		return (reservationService.genererQRCode(reservation.getCléAchat(), utilisateur.getCleUtilisateur(),
				qrCodeGenerationDTO.getWidth(), qrCodeGenerationDTO.getHeight()));

	}

}
