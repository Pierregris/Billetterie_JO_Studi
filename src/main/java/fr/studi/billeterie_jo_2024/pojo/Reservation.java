package fr.studi.billeterie_jo_2024.pojo;

import java.time.LocalDateTime;
import java.util.UUID;

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

	private LocalDateTime date;

	private LocalDateTime validite;

	public LocalDateTime getValidite() {
		return validite;
	}

	public void setValidite(LocalDateTime validite) {
		this.validite = validite;
	}

	@ManyToOne
	@JoinColumn(name = "nom_offre")
	private Offre offreChoisie;

	private StatusReservation statusReservation;

	private UUID cléAchat;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	private Float montant;

	@ManyToOne
	@JoinColumn(name = "evenement_id")
	private Evenement evenement;

	public UUID getCléAchat() {
		return cléAchat;
	}

	public void setCléAchat(UUID cléAchat) {
		this.cléAchat = cléAchat;
	}

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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
