package fr.studi.billeterie_jo_2024.controller;

import java.util.List;
import java.util.UUID;

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
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.EmailService;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;
import jakarta.validation.Valid;

@RequestMapping("/")
@Controller
public class UtilisateurController {

	private final UtilisateurRepository utilisateurRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	ReservationService reservationService;

	UtilisateurController(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

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
		// On génère un lien avec le token d'activation qui permet de valider le compte
		// dès que le lien est cliqué
		String lienActivation = "http://localhost:8081/activation?token=" + utilisateur.getActivationToken();
		// Envoi du lien par mail pour activation du compte
		String message = "Vous êtes bien inscrit ! Activez dès maintenant votre compte en cliquant sur ce bouton :"
				+ lienActivation;
		emailService.sendEmail(utilisateur.getMail(), "Inscription validée", message);
		// On affiche un message sur le site indiquant qu'il reste à valider le compte
		redirectAttributes.addFlashAttribute("messageSucces",
				"Votre compte a été créé avec succès, il ne vous reste plus qu'à l'activer en cliquant sur le lien reçu par email!");
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

	@GetMapping("/activation")
	public String activationUtilisateur(@RequestParam(value = "token", required = true) UUID activationToken,
			Model model, RedirectAttributes redirectAttribute) {
		Utilisateur utilisateur = utilisateurRepository.findByActivationToken(activationToken).orElse(null);
		if (utilisateur == null) {
			redirectAttribute.addFlashAttribute("erreurActivation", "Le lien d'activation n'est pas valide");
			return ("redirect:/accueil");
		}
		utilisateur.setActive(true);
		utilisateur.setActivationToken(null);
		utilisateurRepository.save(utilisateur);
		redirectAttribute.addFlashAttribute("messageActivation", "Votre compte a été activé avec succès !");
		return ("redirect:/accueil");
	}

}
