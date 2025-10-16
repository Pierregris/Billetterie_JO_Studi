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
		List<String> sports = evenementService.getDistinctSports();
		model.addAttribute("sports", sports);
		return "billetterie/accueilbilletterie";
	}

	@GetMapping("/pagesport")
	public String affPageSport(Model model, @RequestParam(name = "sport", required = true) String sport) {
		System.out.println(sport);
		List<Evenement> evenements = evenementService.getEvenementsBySport(sport);
		List<Offre> offres = offreRepository.findByActive(true);
		offres.sort(Comparator.comparingInt(Offre::getNbPlaces));
		model.addAttribute("evenements", evenements);
		model.addAttribute("offres", offres);
		return "billetterie/pagesport";
	}

	@GetMapping("/panier")
	public String affPanier(Model model) {
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Reservation> panier = reservationService.getPanier(utilisateur).getReservations();
		Boolean suppression = reservationService.getPanier(utilisateur).getSuppression();
		model.addAttribute("panier", panier);
		model.addAttribute("suppression", suppression);
		return "billetterie/panier";
	}

	@GetMapping("/commande")
	public String affCommande(Model model) {
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
