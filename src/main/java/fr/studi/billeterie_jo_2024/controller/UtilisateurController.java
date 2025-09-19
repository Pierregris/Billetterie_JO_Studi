package fr.studi.billeterie_jo_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.service.EmailService;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;
import jakarta.validation.Valid;

@RequestMapping("/")
@Controller
public class UtilisateurController {

	@Autowired
	EmailService emailService;

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	ReservationService reservationService;

	// A l'arrivée sur la page de connexion, on crée un utilisateur vide pour
	// permettre le stockage des valeurs données dans le formulaire
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "register";
	}

	// On récupère les infos du formulaire et on crée l'utilisateur dans la base de
	// données à la validation du formulaire
	@PostMapping("/register")
	public String inscrireUtilisateur(@Valid @ModelAttribute Utilisateur utilisateur,
			RedirectAttributes redirectAttributes) {
		if (this.utilisateurService.getUtilisateurbyMail(utilisateur.getMail()) != null) { // On vérifie ici que le mail
																							// renseigné par
																							// l'utilisateur n'est pas
																							// déjà affilié à un compte
																							// dans la base de données
			redirectAttributes.addFlashAttribute("messageErreurDoublon",
					"Un compte existe déjà avec cette adresse mail. Connectez-vous directement : ");
			return "redirect:/register";
		}
		this.utilisateurService.createUtilisateur(utilisateur);
		emailService.sendEmail(utilisateur.getMail(), "Inscription validée", "Vous êtes bien inscrit !");
		redirectAttributes.addFlashAttribute("messageSucces",
				"Votre compte a été créé avec succès, vous pouvez maintenant vous connecter et accéder à la billetterie !");
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
	public String showMonCompte(Model model) {
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Reservation> reservations = reservationService.getReservationsFinalisees(utilisateur);
		model.addAttribute("reservations", reservations);
		return "moncompte";
	}

	@GetMapping({ "/", "/accueil" })
	public String showAccueil() {
		return "accueil";
	}
}
