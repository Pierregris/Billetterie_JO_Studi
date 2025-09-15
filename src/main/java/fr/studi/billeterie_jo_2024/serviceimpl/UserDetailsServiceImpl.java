package fr.studi.billeterie_jo_2024.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Utilisateur utilisateur = utilisateurRepository.findByMail(mail).orElse(null);
		if (utilisateur == null) {
			throw new UsernameNotFoundException("Identifiant ou mot de passe incorrect");
		} else {
			return utilisateur;
		}
	}

}
