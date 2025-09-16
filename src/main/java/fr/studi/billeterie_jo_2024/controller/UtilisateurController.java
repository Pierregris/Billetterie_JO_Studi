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
import fr.studi.billeterie_jo_2024.service.EmailService;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;

@RequestMapping("/")
@Controller
public class UtilisateurController {

	@Autowired
	EmailService emailService;

	@Autowired
	UtilisateurService utilisateurService;

	// A l'arrivée sur la page de connexion, on crée un utilisateur vide pour
	// permettre le stockage des valeurs données dans le formulaire
	@GetMapping("/register")
	public String showRegisterPage(Model model, @RequestParam(value = "error", required = false) String error) {
		if (error != null) {
			model.addAttribute("errorMessageDoublon",
					"Un compte existant a été déctecté avec cet email. Vous pouvez vous connecter directement. <a href=\"/login\">Page de connexion</a>");
		}
		model.addAttribute("utilisateur", new Utilisateur());
		return "register";
	}

	// On récupère les infos du formulaire et on crée l'utilisateur dans la base de
	// données à la validation du formulaire
	@PostMapping("/register")
	public String inscrireUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		if (this.utilisateurService.getUtilisateurbyMail(utilisateur.getMail()) != null) { // On vérifie ici que le mail
																							// renseigné par
																							// l'utilisateur n'est pas
																							// déjà affilié à un compte
																							// dans la base de données
			return "redirect:/register?error";
		}
		this.utilisateurService.createUtilisateur(utilisateur);
		emailService.sendEmail(utilisateur.getMail(), "Inscription validée", "Vous êtes bien inscrit !");
		return "redirect:/accueil";
	}

	@GetMapping("/login")
	public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
		// Si le paramètre error est présent dans l'url, on affiche un message à
		// l'utilisateur
		if (error != null) {
			model.addAttribute("errorMessage", "Identifiant ou mot de passe incorrects");
		}
		// Sinon, on affiche la page de connexion
		return "login";
	}

	@GetMapping("/moncompte")
	public String showMonCompte() {
		return "moncompte";
	}

	@GetMapping({ "/", "/accueil" })
	public String showAccueil() {
		return "accueil";
	}
}
