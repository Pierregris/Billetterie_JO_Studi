package fr.studi.billeterie_jo_2024.functionaltests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.EvenementRepository;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.status.Role;

@AutoConfigureMockMvc
@SpringBootTest(properties = { "spring.session.store-type=none",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,"
				+ "org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration",
		"app.base-url=http://localhost:8081" })
@ActiveProfiles("test")
public class BilletterieFunctionnalTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	OffreRepository offreRepository;

	@Autowired
	EvenementRepository evenementRepository;

	@BeforeEach
	public void setUp() {
		utilisateurRepository.deleteAll();
		Utilisateur utilisateur = new Utilisateur("Duval", "Maurice", "Champs Elysées", "75016", "Paris",
				"mauriceduval@mail.fr", "0610111213", passwordEncoder.encode("mdpMDuval123!"));
		utilisateur.setActive(true);
		utilisateur.setRole(Role.USER);
		utilisateurRepository.save(utilisateur);
	}

	@Test
	public void testAffPageBilletterie() throws Exception {
		Utilisateur utilisateur = utilisateurRepository.findByMail("mauriceduval@mail.fr").orElse(null);
		org.springframework.security.core.Authentication authentication = new UsernamePasswordAuthenticationToken(
				utilisateur, "mdpMDuval123!", utilisateur.getAuthorities());
		mockMvc.perform(get("/billetterie").with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(view().name("billetterie/accueilbilletterie")).andExpect(model().attributeExists("sports"));
	}

	@Test
	public void testAffPageSport() throws Exception {
		Utilisateur utilisateur = utilisateurRepository.findByMail("mauriceduval@mail.fr").orElse(null);
		org.springframework.security.core.Authentication authentication = new UsernamePasswordAuthenticationToken(
				utilisateur, "mdpMDuval123!", utilisateur.getAuthorities());
		mockMvc.perform(get("/billetterie/pagesport").param("sport", "Basketball")
				.with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(view().name("billetterie/pagesport")).andExpect(model().attributeExists("offres"))
				.andExpect(model().attributeExists("evenements"));
	}
}
