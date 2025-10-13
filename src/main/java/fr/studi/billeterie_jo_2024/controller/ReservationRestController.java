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
		Reservation ajoutPanier = reservationService.ajouterAuPanier(aAjouterAuPanier);
		reservationService.createReservation(ajoutPanier);
	}

	@PostMapping("/placesrestantes")
	public int placesRestantes(@RequestParam Long evenement_id) {
		Evenement evenement = evenementRepository.findById(evenement_id).orElse(null);
		return evenement.getCapaciteMax() - evenement.getBilletsVendus();
	}

	@PostMapping("/getnbelementspanier")
	public int nbElementsPanier() {
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(utilisateur);
		System.out.println(reservationService.getPanier(utilisateur).getReservations());
		return reservationService.getPanier(utilisateur).getReservations().size();
	}

	@PostMapping("/supprimerdupanier")
	public void supprimerDuPanier(@RequestParam Long reservation_id) {
		reservationService.supprimerDuPanier(reservation_id);
	}

	@PostMapping("/paiement")
	public boolean simulerPaiement(@RequestBody @Valid InfosPaiementDTO infosPaiementDTO) throws InterruptedException {
		Thread.sleep(5000);
		return true;
	}

	@PostMapping("/validerPanier")
	public void validerPanier(@RequestBody List<Long> reservationListId) {
		reservationListId.forEach(reservation_id -> {
			reservationService.validerReservation(reservation_id);
		});
	}

	@PostMapping("/creerclesachat")
	public void creerClesAchat(@RequestBody List<Long> reservationListId) {
		reservationListId.forEach(reservation_id -> {
			reservationService.genererCleAchat(reservation_id);
		});
	}

	@PostMapping("/genererqrcode")
	public String genererQRCode(@RequestBody QRCodeGenerationDTO qrCodeGenerationDTO) {
		Reservation reservation = reservationRepository.findById(qrCodeGenerationDTO.getReservation_id()).orElse(null);
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return (reservationService.genererQRCode(reservation.getCléAchat(), utilisateur.getCleUtilisateur(),
				qrCodeGenerationDTO.getWidth(), qrCodeGenerationDTO.getHeight()));

	}

}
