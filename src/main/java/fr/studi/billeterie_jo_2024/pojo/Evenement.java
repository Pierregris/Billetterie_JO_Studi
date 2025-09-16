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

	private String epreuve;

	private int capaciteMax;

	private int billetsVendus = 0;

	private LocalDate dateEvenement;

	private LocalTime heureEvenement;

	private String lieu;

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

	public String getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(String epreuve) {
		this.epreuve = epreuve;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

	public int getBilletsVendus() {
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

}
