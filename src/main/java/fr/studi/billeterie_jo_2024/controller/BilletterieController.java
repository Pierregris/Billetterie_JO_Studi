package fr.studi.billeterie_jo_2024.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Offre;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.service.EvenementService;
import fr.studi.billeterie_jo_2024.service.ReservationService;

@Controller
@RequestMapping("/billetterie")
public class BilletterieController {

	@Autowired
	EvenementService evenementService;

	@Autowired
	ReservationService reservationService;

	@Autowired
	OffreRepository offreRepository;

	@GetMapping("")
	public String affBilletterie(Model model) {
		List<String> sports = evenementService.getDistinctSports();// On récupère tous les sports différents à afficher
		model.addAttribute("sports", sports);// On ajoute la liste des sports au modèle pour transmission par Thymeleaf
		return "billetterie/accueilbilletterie";
	}

	@GetMapping("/pagesport")
	public String affPageSport(Model model, @RequestParam(name = "sport", required = true) String sport) {
		List<Evenement> evenements = evenementService.getEvenementsBySport(sport); // On récupère tous les événements
																					// liés au sport choisi
		List<Offre> offres = offreRepository.findByActive(true);
		offres.sort(Comparator.comparingInt(Offre::getNbPlaces));// On récupère toutes les offres actives et on les trie
																	// par ordre de nombre de places croissant, pour un
																	// affichage cohérent
		model.addAttribute("evenements", evenements);
		model.addAttribute("offres", offres);// On ajoute les événements et les offres au modèle
		return "billetterie/pagesport";
	}

	@GetMapping("/panier")
	public String affPanier(Model model) {
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// On récupère l'ensemble des réservations du panier de l'utilisateur. La
		// variable suppression permet de définir si une réservation était expirée
		List<Reservation> panier = reservationService.getPanier(utilisateur).getReservations();
		Boolean suppression = reservationService.getPanier(utilisateur).getSuppression();
		model.addAttribute("panier", panier);
		model.addAttribute("suppression", suppression);
		return "billetterie/panier";
	}

	@GetMapping("/commande")
	public String affCommande(Model model) {
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// On récupère l'ensemble des réservations du panier de l'utilisateur. La
		// variable suppression permet de définir si une réservation était expirée
		List<Reservation> panier = reservationService.getPanier(utilisateur).getReservations();
		Boolean suppression = reservationService.getPanier(utilisateur).getSuppression();
		model.addAttribute("panier", panier);
		model.addAttribute("suppression", suppression);
		return "billetterie/commande";
	}

	@GetMapping("/paiementsuccess")
	public String affPageSuccess() {
		return "billetterie/paiementsuccess";
	}
}
