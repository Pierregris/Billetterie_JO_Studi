package fr.studi.billeterie_jo_2024.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
	// L'identifiant utilisé pour la connexion sera l'adresse mail. Elle est unique
	// pour chaque utilisateur
	Optional<Utilisateur> findByMail(String mail);

	Optional<Utilisateur> findByActivationToken(UUID activationToken);

	Optional<Utilisateur> findByOtp(String otp);

}
