package fr.studi.billeterie_jo_2024.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void createUtilisateur(Utilisateur utilisateur) {
		String password_encode = passwordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(password_encode);
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
	public Utilisateur getUtilisateurbyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
