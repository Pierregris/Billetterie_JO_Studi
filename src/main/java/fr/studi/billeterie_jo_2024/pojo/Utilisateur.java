package fr.studi.billeterie_jo_2024.pojo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.studi.billeterie_jo_2024.status.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Utilisateur implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID cleUtilisateur;

	private boolean active = false;

	private UUID activationToken;

	private String otp;
	private LocalDateTime otpValidity;

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
	@Column
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "utilisateur")
	private List<Reservation> reservations;

	public Utilisateur(String nom, String prenom, String adresse, String codePostal, String ville, String mail,
			String numeroTelephone, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mail = mail;
		this.numeroTelephone = numeroTelephone;
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpValidity() {
		return otpValidity;
	}

	public void setOtpValidity(LocalDateTime otpValidity) {
		this.otpValidity = otpValidity;
	}

	public UUID getActivationToken() {
		return activationToken;
	}

	public void setActivationToken(UUID activationToken) {
		this.activationToken = activationToken;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UUID getCleUtilisateur() {
		return cleUtilisateur;
	}

	public void setCleUtilisateur(UUID cleUtilisateur) {
		this.cleUtilisateur = cleUtilisateur;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

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

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}

	@Override
	public String getUsername() {
		return mail;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
