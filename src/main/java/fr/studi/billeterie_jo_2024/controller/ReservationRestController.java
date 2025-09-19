package fr.studi.billeterie_jo_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@DeleteMapping("/supprimerdupanier")
	public void supprimerDuPanier(@RequestParam Long reservation_id) {
		reservationService.supprimerDuPanier(reservation_id);
	}

	@PostMapping("/paiement")
	public boolean simulerPaiement(@RequestBody @Valid InfosPaiementDTO infosPaiementDTO) throws InterruptedException {
		Thread.sleep(3000);
		return true;
	}

	@PostMapping("/validerPanier")
	public void validerPanier(@RequestBody List<Long> reservationListId) {
		reservationListId.forEach(reservation_id -> {
			System.out.println(reservation_id);
			reservationService.validerReservation(reservation_id);

		});
	}

}
