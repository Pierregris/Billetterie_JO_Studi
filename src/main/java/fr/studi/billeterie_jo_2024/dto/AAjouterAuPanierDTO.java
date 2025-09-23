package fr.studi.billeterie_jo_2024.dto;

public class AAjouterAuPanierDTO {
	private String nomOffre;
	private Float montant;
	private Long evenement_id;

	public String getNomOffre() {
		return nomOffre;
	}

	public void setNomOffre(String nomOffre) {
		this.nomOffre = nomOffre;
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
