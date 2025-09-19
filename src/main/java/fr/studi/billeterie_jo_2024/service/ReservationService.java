package fr.studi.billeterie_jo_2024.service;

import java.util.List;

import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;

public interface ReservationService {

	void createReservation(Reservation reservation);

	Reservation ajouterAuPanier(AAjouterAuPanierDTO panierDTO);

	List<Reservation> getPanier(Utilisateur utilisateur);

	void validerPanier(List<Reservation> panier);

	void supprimerDuPanier(Long reservation_id);

	void validerReservation(Long reservation_id);

	List<Reservation> getReservationsFinalisees(Utilisateur utilisateur);
}
