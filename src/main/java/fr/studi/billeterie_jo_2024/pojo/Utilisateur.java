package fr.studi.billeterie_jo_2024.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	private String nom;
	@Column
	private String prenom;
	@Column
	private String adresse;
	@Column
	private String codePostal;
	@Column
	private String ville;
	@Column
	private String mail;
	@Column
	private String numeroTelephone;

	@OneToMany(mappedBy = "utilisateur")
	private List<Reservation> reservations;

}
