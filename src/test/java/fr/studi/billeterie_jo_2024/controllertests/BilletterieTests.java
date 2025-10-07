package fr.studi.billeterie_jo_2024.controllertests;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.studi.billeterie_jo_2024.configuration.SpringSecurityConfig;
import fr.studi.billeterie_jo_2024.controller.BilletterieController;
import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.service.EvenementService;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.status.ResultatGetPanier;
import fr.studi.billeterie_jo_2024.status.Role;

@Import(SpringSecurityConfig.class)
@WebMvcTest(BilletterieController.class)
public class BilletterieTests {

	@Autowired
	MockMvc mockMvc;

	@MockitoBean
	EvenementService evenementService;

	@MockitoBean
	ReservationService reservationService;

	Utilisateur user;
	Authentication authentication;

	@BeforeEach
	public void creationUtilisateur() {
		user = new Utilisateur();
		user.setPrenom("Jean");
		user.setNom("Dupond");
		user.setRole(Role.USER);
		authentication = new UsernamePasswordAuthenticationToken(user, "password", user.getAuthorities());
	}

	@Test
	public void testAfficherBilletterie() throws Exception {
		List<String> sports = List.of("Basketball", "Football", "Athlétisme");
		when(evenementService.getDistinctSports()).thenReturn(sports);
		mockMvc.perform(get("/billetterie").with(authentication(authentication)))
				.andExpect(model().attribute("sports", sports))
				.andExpect(view().name("/billetterie/accueilbilletterie"));
	}

	@Test
	public void testAffPageSport() throws Exception {
		Evenement ev1 = new Evenement();
		ev1.setSport("basketball");
		ev1.setBilletsVendus(0);
		ev1.setCapaciteMax(1000);
		ev1.setPrixBillet(50);
		Evenement ev2 = new Evenement();
		ev2.setSport("basketball");
		ev2.setBilletsVendus(0);
		ev2.setCapaciteMax(1000);
		ev2.setPrixBillet(50);
		List<Evenement> evenements = List.of(ev1, ev2);
		when(evenementService.getEvenementsBySport("basketball")).thenReturn(evenements);
		mockMvc.perform(get("/billetterie/pagesport").param("sport", "basketball").with(authentication(authentication)))
				.andExpect(model().attribute("evenements", evenements))
				.andExpect(view().name("/billetterie/pagesport"));

	}

	@Test
	public void testAfficherPanier() throws Exception {
		Evenement ev1 = new Evenement();
		ev1.setSport("basketball");
		ev1.setBilletsVendus(0);
		ev1.setCapaciteMax(1000);
		ev1.setPrixBillet(50);
		Reservation resa1 = new Reservation();
		resa1.setEvenement(ev1);
		Reservation resa2 = new Reservation();
		resa2.setEvenement(ev1);
		ResultatGetPanier resultatGetPanier = new ResultatGetPanier(List.of(resa1, resa2), false);
		when(reservationService.getPanier(user)).thenReturn(resultatGetPanier);
		mockMvc.perform(get("/billetterie/panier").with(authentication(authentication)))
				.andExpect(model().attribute("panier", List.of(resa1, resa2)))
				.andExpect(model().attribute("suppression", false)).andExpect(view().name("/billetterie/panier"));
	}

	@Test
	public void testAfficherCommande() throws Exception {
		Evenement ev1 = new Evenement();
		ev1.setSport("basketball");
		ev1.setBilletsVendus(0);
		ev1.setCapaciteMax(1000);
		ev1.setPrixBillet(50);
		Reservation resa1 = new Reservation();
		resa1.setEvenement(ev1);
		Reservation resa2 = new Reservation();
		resa2.setEvenement(ev1);
		ResultatGetPanier resultatGetPanier = new ResultatGetPanier(List.of(resa1, resa2), false);
		when(reservationService.getPanier(user)).thenReturn(resultatGetPanier);
		mockMvc.perform(get("/billetterie/commande").with(authentication(authentication)))
				.andExpect(model().attribute("panier", List.of(resa1, resa2)))
				.andExpect(model().attribute("suppression", false)).andExpect(view().name("/billetterie/commande"));
	}

	@Test
	public void testAfficherSuccesCommande() throws Exception {
		mockMvc.perform(get("/billetterie/paiementsuccess").with(authentication(authentication)))
				.andExpect(view().name("/billetterie/paiementsuccess"));
	}
}
