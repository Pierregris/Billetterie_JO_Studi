package fr.studi.billeterie_jo_2024.service;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;

public interface UtilisateurService {

	public void createUtilisateur(Utilisateur utilisateur);

	public void updateUtilisateur();

	public void deleteUtilisateur();

	public Utilisateur getUtilisateurbyMail(String mail);
}
