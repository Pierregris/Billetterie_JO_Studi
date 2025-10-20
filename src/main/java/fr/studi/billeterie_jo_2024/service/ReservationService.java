package fr.studi.billeterie_jo_2024.service;

import java.util.List;
import java.util.UUID;

import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.status.ResultatGetPanier;

public interface ReservationService {

	void createReservation(Reservation reservation);

	Reservation ajouterAuPanier(AAjouterAuPanierDTO panierDTO);

	ResultatGetPanier getPanier(Utilisateur utilisateur);

	void supprimerDuPanier(Long reservation_id);

	void validerReservation(Long reservation_id);

	List<Reservation> getReservationsFinalisees(Utilisateur utilisateur);

	void genererCleAchat(Long reservation_id);

	String genererQRCode(UUID cleAchat, UUID utilisateur_id, int width, int height);
}
