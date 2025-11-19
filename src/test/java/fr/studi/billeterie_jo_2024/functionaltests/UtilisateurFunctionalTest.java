package fr.studi.billeterie_jo_2024.functionaltests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.EmailService;

@AutoConfigureMockMvc
@SpringBootTest(properties = { "spring.session.store-type=none",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,"
				+ "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration",
		"app.base-url=http://localhost:8081" })
@ActiveProfiles("test")

public class UtilisateurFunctionalTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	PasswordEncoder passwordEncoder;

	@MockitoBean
	EmailService emailService;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@BeforeEach
	public void setUp() {
		utilisateurRepository.deleteAll();
		Utilisateur utilisateur = new Utilisateur("Duval", "Maurice", "Champs Elysées", "75016", "Paris",
				"mauriceduval@mail.fr", "0610111213", passwordEncoder.encode("mdpMDuval123!"));
		utilisateur.setActive(true);
		utilisateurRepository.save(utilisateur);
	}

	@Test
	public void testCreateUtilisateur() throws Exception {
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("nom", "Dupond")
				.param("prenom", "Jean").param("adresse", "rue de la Paix").param("codePostal", "75001")
				.param("ville", "Paris").param("mail", "jeandupond@mail.fr").param("telephone", "0607080910")
				.param("password", "Motdepasse123!").with(csrf())).andExpect(redirectedUrl("/accueil"))
				.andExpect(flash().attribute("messageSucces",
						"Votre compte a été créé avec succès, il ne vous reste plus qu'à l'activer en cliquant sur le lien reçu par email!"));

		Utilisateur utilisateur = utilisateurRepository.findByMail("jeandupond@mail.fr").orElse(null);
		assertThat(utilisateur != null);
		assertThat(utilisateur.getNom().equals("Dupond"));
		assertThat(utilisateur.getActivationToken() != null);

		String lienActivation = "http://localhost:8081/activation?token=" + utilisateur.getActivationToken();
		verify(emailService).sendEmail("jeandupond@mail.fr", "Inscription validée",
				"Vous êtes bien inscrit ! Activez dès maintenant votre compte en cliquant sur ce bouton :"
						+ lienActivation);
	}

	@Test
	public void testExistingMail() throws Exception {
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("nom", "Dupond")
				.param("prenom", "Jean").param("adresse", "rue de la Paix").param("codePostal", "75001")
				.param("ville", "Paris").param("mail", "mauriceduval@mail.fr").param("telephone", "0607080910")
				.param("password", "Motdepasse123!").with(csrf())).andExpect(redirectedUrl("/register"))
				.andExpect(flash().attribute("messageErreurDoublon",
						"Un compte existe déjà avec cette adresse mail. Connectez-vous directement : "));

	}

}
