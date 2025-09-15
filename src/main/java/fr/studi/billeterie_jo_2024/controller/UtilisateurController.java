package fr.studi.billeterie_jo_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;

@RequestMapping("/")
@Controller
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}

	@PostMapping("/register")
	public String inscrireUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		this.utilisateurService.createUtilisateur(utilisateur);
		return "monCompte";
	}

	@GetMapping("/moncompte")
	public String showMonCompte() {
		return "moncompte";
	}
}
