package fr.studi.billeterie_jo_2024.pojo;

import java.time.LocalDate;

import fr.studi.billeterie_jo_2024.status.StatusReservation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "nom_offre")
	private Offre offreChoisie;

	private StatusReservation statusReservation;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	private Float montant;

	@ManyToOne
	@JoinColumn(name = "evenement_id")
	private Evenement evenement;

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Offre getOffreChoisie() {
		return offreChoisie;
	}

	public void setOffreChoisie(Offre offreChoisie) {
		this.offreChoisie = offreChoisie;
	}

	public StatusReservation getStatusReservation() {
		return statusReservation;
	}

	public void setStatusReservation(StatusReservation statusReservation) {
		this.statusReservation = statusReservation;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}
}
