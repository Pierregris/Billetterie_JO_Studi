package fr.studi.billeterie_jo_2024.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;
import fr.studi.billeterie_jo_2024.status.Role;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Override
	public void createUtilisateur(Utilisateur utilisateur) {
		utilisateur.setRole(Role.USER);
		utilisateur.setActivationToken(UUID.randomUUID());
		this.utilisateurRepository.save(utilisateur);
	}

	@Override
	public void updateUtilisateur() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUtilisateur() {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur getUtilisateurbyMail(String mail) {
		return utilisateurRepository.findByMail(mail).orElse(null);
	}

}
