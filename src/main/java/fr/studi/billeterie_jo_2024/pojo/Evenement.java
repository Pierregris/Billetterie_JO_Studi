package fr.studi.billeterie_jo_2024.pojo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Evenement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String sport;

	private String nomEvenement;

	private Integer capaciteMax;

	private Integer billetsVendus = 0;

	private LocalDate dateEvenement;

	private LocalTime heureEvenement;

	private String lieu;

	private Float prixBillet;

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	@OneToMany(mappedBy = "evenement")
	private List<Billet> billets;

	public Evenement() {
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getNomEvenement() {
		return nomEvenement;
	}

	public void setNomEvenement(String nomEvenement) {
		this.nomEvenement = nomEvenement;
	}

	public Integer getCapaciteMax() {
		return capaciteMax;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

	public Integer getBilletsVendus() {
		return billetsVendus;
	}

	public void setBilletsVendus(int billetsVendus) {
		this.billetsVendus = billetsVendus;
	}

	public LocalDate getDateEvenement() {
		return dateEvenement;
	}

	public void setDateEvenement(LocalDate dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	public LocalTime getHeureEvenement() {
		return heureEvenement;
	}

	public void setHeureEvenement(LocalTime heureEvenement) {
		this.heureEvenement = heureEvenement;
	}

	public Float getPrixBillet() {
		return prixBillet;
	}

	public void setPrixBillet(float prixBillet) {
		this.prixBillet = prixBillet;
	}

}
