package fr.studi.billeterie_jo_2024.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InfosPaiementDTO {
	@Size(min = 16, max = 16)
	private String numeroCarte;

	@NotBlank
	private String nom;

	@Size(min = 3, max = 3)
	private String codeSecurite;

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCodeSecurite() {
		return codeSecurite;
	}

	public void setCodeSecurite(String codeSecurite) {
		this.codeSecurite = codeSecurite;
	}

}
