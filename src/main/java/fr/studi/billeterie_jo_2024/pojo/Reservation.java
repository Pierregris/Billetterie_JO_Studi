package fr.studi.billeterie_jo_2024.pojo;

import java.time.LocalDate;
import java.util.List;

import fr.studi.billeterie_jo_2024.status.Offre;
import fr.studi.billeterie_jo_2024.status.StatusReservation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	private Offre offreChoisie;

	private StatusReservation statusReservation;

	private Utilisateur utilisateur;

	@OneToMany(mappedBy = "reservation")
	private List<Billet> billets;
}
