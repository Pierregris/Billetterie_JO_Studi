package fr.studi.billeterie_jo_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;

@RequestMapping("/")
@Controller
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "register";
	}

	@PostMapping("/register")
	public String inscrireUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		this.utilisateurService.createUtilisateur(utilisateur);
		return "accueil";
	}

	@GetMapping("/login")
	public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("errorMessage", "Identifiant ou mot de passe incorrects");
		}
		return "login";
	}

	@GetMapping("/moncompte")
	public String showMonCompte() {
		return "moncompte";
	}

	@GetMapping("/accueil")
	public String showAccueil() {
		return "accueil";
	}
}
