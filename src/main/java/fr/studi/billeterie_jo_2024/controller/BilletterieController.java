package fr.studi.billeterie_jo_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.service.EvenementService;

@Controller
@RequestMapping("/billetterie")
public class BilletterieController {

	@Autowired
	EvenementService evenementService;

	@GetMapping("")
	public String affBilletterie(Model model) {
		List<Evenement> evenements = evenementService.getAllEvenements();
		model.addAttribute("evenements", evenements);
		return "billetterie";
	}
}
