package fr.studi.billeterie_jo_2024.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.studi.billeterie_jo_2024.pojo.Offre;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.service.EvenementService;
import fr.studi.billeterie_jo_2024.service.OffreService;

@Controller
@RequestMapping("/admin")
public class OffreController {
	@Autowired
	OffreRepository offreRepository;

	@Autowired
	EvenementService evenementService;

	@Autowired
	OffreService offreService;

	@GetMapping("/creerOffre")
	public String affPageOffre(Model model) {
		model.addAttribute("offre", new Offre());
		return "admin/creerOffre";
	}

	@PostMapping("/creerOffre")
	public String ajouterOffre(@ModelAttribute Offre offre, RedirectAttributes redirectAttributes) {
		offre.setDiscount((100 - offre.getDiscount()) / 100);
		if (!offreRepository.findById(offre.getNomOffre()).isEmpty()) {
			redirectAttributes.addFlashAttribute("offreExistante",
					"Une offre existe déjà avec ce nom, veuillez en choisir un autre");
			return "redirect:/admin/creerOffre";
		}
		offreRepository.save(offre);
		redirectAttributes.addFlashAttribute("succesAjoutOffre",
				"L'offre a bien été ajoutée, elle sera affichée dans la billetterie");
		return "redirect:/admin/creerOffre";
	}

	@GetMapping("/gereroffres")
	public String gererOffre(Model model) {
		List<Offre> offres = offreRepository.findAll();
		offres.sort(Comparator.comparingInt(Offre::getNbPlaces));
		model.addAttribute("offres", offres);
		return "admin/gereroffres";
	}

	@PostMapping("/desactiveroffre")
	public String desactiverOffre(RedirectAttributes redirectAttributes, @RequestParam String nom_offre) {
		System.out.println("Entrée endpoint");
		offreService.desactiverOffre(nom_offre);
		redirectAttributes.addFlashAttribute("offreDesactivee", "L'offre a bien été désactivée");
		return "redirect:/admin/gereroffres";
	}

	@PostMapping("/activeroffre")
	public String activerOffre(RedirectAttributes redirectAttributes, @RequestParam String nom_offre) {
		offreService.activerOffre(nom_offre);
		redirectAttributes.addFlashAttribute("offreActivee", "L'offre a bien été activée");
		return "redirect:/admin/gereroffres";
	}

}
