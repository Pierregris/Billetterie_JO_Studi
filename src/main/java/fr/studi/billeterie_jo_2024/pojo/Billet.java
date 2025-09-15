package fr.studi.billeterie_jo_2024.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Billet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "evenement_id")
	private Evenement evenement;

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	private Float prix;

}
