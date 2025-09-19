package fr.studi.billeterie_jo_2024;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.studi.billeterie_jo_2024.controller.UtilisateurController;
import fr.studi.billeterie_jo_2024.service.EmailService;
import fr.studi.billeterie_jo_2024.service.ReservationService;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;

@WebMvcTest(UtilisateurController.class)
class BilleterieJo2024ApplicationTests {

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
	@WithMockUser
	public void registerUtilisateur() throws Exception {
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("nom", "Dupond")
				.param("prenom", "Jean").param("adresse", "rue de la Paix").param("codePostal", "75001")
				.param("ville", "Paris").param("email", "jeandupond@mail.fr").param("telephone", "0607080910")
				.param("motDePasse", "motdepasse").with(csrf())).andExpect(redirectedUrl("/accueil"))
				.andExpect(flash().attribute("messageSucces",
						"Votre compte a été créé avec succès, vous pouvez maintenant vous connecter et accéder à la billetterie !"));
		;

	}

}
