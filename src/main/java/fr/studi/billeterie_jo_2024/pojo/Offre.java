package fr.studi.billeterie_jo_2024.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Offre {
	@Id
	String nomOffre;

	Integer nbPlaces;

	Double discount;

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getNomOffre() {
		return nomOffre;
	}

	public void setNomOffre(String nomOffre) {
		this.nomOffre = nomOffre;
	}

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

}
