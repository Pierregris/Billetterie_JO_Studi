package fr.studi.billeterie_jo_2024.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	private String nom;
	@Column
	private String prenom;
//	@Column
//	private String adresse;
//	@Column
//	private String codePostal;
//	@Column
//	private String ville;
	@Column
	private String mail;

	public Utilisateur() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// @Column
//	private String numeroTelephone;
	@Column
	private String password;

//	@OneToMany(mappedBy = "utilisateur")
//	private List<Reservation> reservations;

}
