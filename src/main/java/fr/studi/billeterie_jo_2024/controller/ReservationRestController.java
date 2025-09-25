package fr.studi.billeterie_jo_2024.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.dto.InfosPaiementDTO;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.repository.ReservationRepository;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/apiReservation")
public class ReservationRestController {

	private final ReservationRepository reservationRepository;

	@Autowired
	ReservationService reservationService;

	ReservationRestController(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@PostMapping("/ajouteraupanier")
	public void ajouterAuPanier(@RequestBody AAjouterAuPanierDTO aAjouterAuPanier) {
		Reservation ajoutPanier = reservationService.ajouterAuPanier(aAjouterAuPanier);
		reservationService.createReservation(ajoutPanier);
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
		System.out.println(reservationListId);
		System.out.println("entrée dans le script");

		reservationListId.forEach(reservation_id -> {
			Reservation reservation = reservationRepository.findById(reservation_id).orElse(null);
			reservation.setCléAchat(UUID.randomUUID());
			reservationRepository.save(reservation);

		});
	}

}
