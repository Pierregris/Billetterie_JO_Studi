package fr.studi.billeterie_jo_2024.dto;

import fr.studi.billeterie_jo_2024.status.Offre;

public class AAjouterAuPanierDTO {
	private Offre offreChoisie;
	private Float montant;
	private Long evenement_id;

	public Offre getOffreChoisie() {
		return offreChoisie;
	}

	public void setOffreChoisie(Offre offreChoisie) {
		this.offreChoisie = offreChoisie;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public Long getEvenement_id() {
		return evenement_id;
	}

	public void setEvenement_id(Long evenement_id) {
		this.evenement_id = evenement_id;
	}
}
