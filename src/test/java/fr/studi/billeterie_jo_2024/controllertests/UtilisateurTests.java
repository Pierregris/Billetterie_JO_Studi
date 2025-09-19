package fr.studi.billeterie_jo_2024.controllertests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.studi.billeterie_jo_2024.configuration.SpringSecurityConfig;
import fr.studi.billeterie_jo_2024.controller.UtilisateurController;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.service.EmailService;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;
import fr.studi.billeterie_jo_2024.status.Role;

@Import(SpringSecurityConfig.class)
@WebMvcTest(UtilisateurController.class)
public class UtilisateurTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	UtilisateurService utilisateurService;

	@MockBean
	EmailService emailService;

	@MockBean
	ReservationService reservationService;

	@Test
	public void testGetRegister() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("register"))
				.andExpect(model().attributeExists("utilisateur"));
	}

	@Test
	@WithMockUser
	public void TestRegisterUtilisateurValide() throws Exception {
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("nom", "Dupond")
				.param("prenom", "Jean").param("adresse", "rue de la Paix").param("codePostal", "75001")
				.param("ville", "Paris").param("mail", "jeandupond@mail.fr").param("telephone", "0607080910")
				.param("motDePasse", "motdepasse").with(csrf())).andExpect(redirectedUrl("/accueil"))
				.andExpect(flash().attribute("messageSucces",
						"Votre compte a été créé avec succès, vous pouvez maintenant vous connecter et accéder à la billetterie !"));

		verify(emailService, times(1)).sendEmail("jeandupond@mail.fr", "Inscription validée",
				"Vous êtes bien inscrit !");
	}

	@Test
	@WithMockUser
	public void TestRegisterUtilisateurDejaEnBase() throws Exception {
		when(utilisateurService.getUtilisateurbyMail("jeandupond@mail.fr")).thenReturn(new Utilisateur());
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("nom", "Dupond")
				.param("prenom", "Jean").param("adresse", "rue de la Paix").param("codePostal", "75001")
				.param("ville", "Paris").param("mail", "jeandupond@mail.fr").param("telephone", "0607080910")
				.param("motDePasse", "motdepasse").with(csrf())).andExpect(redirectedUrl("/register"))
				.andExpect(flash().attribute("messageErreurDoublon",
						"Un compte existe déjà avec cette adresse mail. Connectez-vous directement : "));
	}

	@Test
	public void TestAffPageLogin() throws Exception {
		mockMvc.perform(get("/login")).andExpect(view().name("login"));
	}

	@Test
	public void TestAffPageLoginError() throws Exception {
		mockMvc.perform(get("/login").param("error", "true")).andExpect(model().attributeExists("errorMessage"));
	}

	@Test
	public void TestAffPageCompte() throws Exception {
		mockMvc.perform(get("/accueil", "/")).andExpect(view().name("accueil"));
	}

	@Test
	@WithMockUser
	public void TestAffReservations() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setRole(Role.USER);
		Authentication authentification = new UsernamePasswordAuthenticationToken(utilisateur, "password",
				utilisateur.getAuthorities());

		mockMvc.perform(get("/moncompte").with(authentication(authentification)))
				.andExpect(model().attributeExists("reservations"));
	}

}
