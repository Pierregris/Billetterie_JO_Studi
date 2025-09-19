package fr.studi.billeterie_jo_2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.status.StatusReservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByUtilisateurAndStatusReservation(Utilisateur utilisateur,
			StatusReservation statusReservation);

}
