package fr.studi.billeterie_jo_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Offre;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.service.EvenementService;

@Controller
@RequestMapping("/admin")
public class EvenementController {

	private final OffreRepository offreRepository;

	@Autowired
	EvenementService evenementService;

	EvenementController(OffreRepository offreRepository) {
		this.offreRepository = offreRepository;
	}

	@GetMapping("/creerEvenement")
	public String affPageAdmin(Model model) {
		model.addAttribute("evenement", new Evenement());
		return "admin/creerEvenement";
	}

	@PostMapping("/creerEvenement")
	public String ajouterEvenement(@ModelAttribute Evenement evenement) {
		evenement.setSport(evenement.getSport().replace(" ", "-"));
		evenementService.createEvenement(evenement);
		return "redirect:/admin/creerEvenement";
	}

	@GetMapping("/creerOffre")
	public String affPageOffre(Model model) {
		model.addAttribute("offre", new Offre());
		return "admin/creerOffre";
	}

	@PostMapping("/creerOffre")
	public String ajouterOffre(@ModelAttribute Offre offre, RedirectAttributes redirectAttributes) {
		offre.setDiscount((100 - offre.getDiscount()) / 100);
		offreRepository.save(offre);
		redirectAttributes.addFlashAttribute("succesAjoutOffre",
				"L'offre a bien été ajoutée, elle sera affichée dans la billetterie");
		return "redirect:/admin/creerOffre";
	}

	@GetMapping("/consulterEvenement")
	public String affPageConsultation(Model model) {
		List<Evenement> evenements = evenementService.getAllEvenements();
		model.addAttribute("evenements", evenements);
		return "admin/consulterEvenement";
	}

	@GetMapping({ "/accueilAdmin", "/" })
	public String affPageAdmin() {
		return "admin/accueilAdmin";
	}
}
