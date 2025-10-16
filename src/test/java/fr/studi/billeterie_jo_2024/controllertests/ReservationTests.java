
package fr.studi.billeterie_jo_2024.controllertests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.studi.billeterie_jo_2024.controller.ReservationRestController;
import fr.studi.billeterie_jo_2024.dto.AAjouterAuPanierDTO;
import fr.studi.billeterie_jo_2024.pojo.Evenement;
import fr.studi.billeterie_jo_2024.pojo.Reservation;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.repository.ReservationRepository;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.status.ResultatGetPanier;
import fr.studi.billeterie_jo_2024.status.Role;

@WebMvcTest(ReservationRestController.class)
public class ReservationTests {

	@Autowired
	MockMvc mockMvc;

	@MockitoBean
	ReservationService reservationService;

	@MockitoBean
	EvenementRepository evenementRepository;

	@MockitoBean
	ReservationRepository reservationRepository;

	Utilisateur user;
	Authentication authentication;

	@BeforeEach
	void setup() {
		user = new Utilisateur();
		user.setNom("Dupond");
		user.setPrenom("Jean");
		user.setRole(Role.USER);
		user.setCleUtilisateur(UUID.randomUUID());
		authentication = new UsernamePasswordAuthenticationToken(user, "password",
				List.of(new SimpleGrantedAuthority("ROLE_USER")));
	}

	@Test
	void testPlacesRestantes() throws Exception {
		Evenement e = new Evenement();
		e.setCapaciteMax(1500);
		e.setBilletsVendus(1000);
		when(evenementRepository.findById(1L)).thenReturn(Optional.of(e));

		mockMvc.perform(post("/apiReservation/placesrestantes").param("evenement_id", "1")
				.with(authentication(authentication)).with(csrf())).andExpect(status().isOk())
				.andExpect(content().string("500"));
	}

	@Test
	void testAjouterAuPanier() throws Exception {
		AAjouterAuPanierDTO dto = new AAjouterAuPanierDTO();
		dto.setEvenement_id(1L);
		dto.setNomOffre("DUO");
		dto.setMontant(100F);

		Reservation res = new Reservation();
		when(reservationService.ajouterAuPanier(dto)).thenReturn(res);

		mockMvc.perform(post("/apiReservation/ajouteraupanier").contentType(MediaType.APPLICATION_JSON)
				.content("{\"evenement_id\":1,\"nomOffre\":\"DUO\",\"montant\":100}")
				.with(authentication(authentication)).with(csrf())).andExpect(status().isOk());

		verify(reservationService).ajouterAuPanier(any(AAjouterAuPanierDTO.class));

	}

	@Test
	void testNbElementsPanier() throws Exception {
		Reservation res1 = new Reservation();
		Reservation res2 = new Reservation();
		ResultatGetPanier panier = new ResultatGetPanier(List.of(res1, res2), false);
		when(reservationService.getPanier(user)).thenReturn(panier);

		mockMvc.perform(post("/apiReservation/getnbelementspanier").with(authentication(authentication)).with(csrf()))
				.andExpect(status().isOk()).andExpect(content().string("2"));
	}

	@Test
	void testSupprimerDuPanier() throws Exception {
		mockMvc.perform(post("/apiReservation/supprimerdupanier").param("reservation_id", "1")
				.with(authentication(authentication)).with(csrf())).andExpect(status().isOk());

		verify(reservationService).supprimerDuPanier(1L);
	}

	@Test
	void testValiderPanier() throws Exception {
		String json = "[1,2,3]";

		mockMvc.perform(post("/apiReservation/validerPanier").contentType(MediaType.APPLICATION_JSON).content(json)
				.with(authentication(authentication)).with(csrf())).andExpect(status().isOk());

		verify(reservationService).validerReservation(1L);
		verify(reservationService).validerReservation(2L);
		verify(reservationService).validerReservation(3L);
	}

	@Test
	void testCreerClesAchat() throws Exception {
		String json = "[1,2]";

		mockMvc.perform(post("/apiReservation/creerclesachat").contentType(MediaType.APPLICATION_JSON).content(json)
				.with(authentication(authentication)).with(csrf())).andExpect(status().isOk());

		verify(reservationService).genererCleAchat(1L);
		verify(reservationService).genererCleAchat(2L);
	}

	@Test
	void testGenererQRCode() throws Exception {
		UUID cleAchat = UUID.randomUUID();
		Reservation res = new Reservation();
		res.setCléAchat(cleAchat);
		when(reservationRepository.findById(1L)).thenReturn(Optional.of(res));
		when(reservationService.genererQRCode(cleAchat, user.getCleUtilisateur(), 100, 100))
				.thenReturn("QRCodeBase64String");

		mockMvc.perform(post("/apiReservation/genererqrcode").contentType(MediaType.APPLICATION_JSON)
				.content("{\"reservation_id\":1,\"width\":100,\"height\":100}").with(authentication(authentication))
				.with(csrf())).andExpect(status().isOk()).andExpect(content().string("QRCodeBase64String"));
	}
}
