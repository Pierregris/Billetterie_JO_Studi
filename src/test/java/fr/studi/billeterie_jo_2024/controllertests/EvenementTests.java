package fr.studi.billeterie_jo_2024.controllertests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.studi.billeterie_jo_2024.configuration.AuthenticationDetailsSourceConfig;
import fr.studi.billeterie_jo_2024.configuration.SpringSecurityConfig;
import fr.studi.billeterie_jo_2024.configuration.TwoFactorAuthenticationSuccessHandler;
import fr.studi.billeterie_jo_2024.controller.EvenementController;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.OffreRepository;
import fr.studi.billeterie_jo_2024.service.EvenementService;
import fr.studi.billeterie_jo_2024.status.Role;

@Import(SpringSecurityConfig.class)
@WebMvcTest(EvenementController.class)
public class EvenementTests {

	@Autowired
	MockMvc mockMvc;

	@MockitoBean
	AuthenticationDetailsSourceConfig authenticationDetailsSourceConfig;

	@MockitoBean
	TwoFactorAuthenticationSuccessHandler twoFactorAuthenticationSuccessHandler;

	@MockitoBean
	EvenementService evenementService;

	@MockitoBean
	OffreRepository offreRepository;

	@Test
	public void testGetCreerEvenementWithAdminProfile() throws Exception {
		Utilisateur admin = new Utilisateur();
		admin.setRole(Role.ADMIN);
		Authentication authentication = new UsernamePasswordAuthenticationToken(admin, "password",
				admin.getAuthorities());
		mockMvc.perform(
				get("/admin/creerEvenement").with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(model().attributeExists("evenement")).andExpect(view().name("admin/creerEvenement"));
	}

	@Test
	public void testGetCreerEvenementWithUserProfile() throws Exception {
		Utilisateur user = new Utilisateur();
		user.setRole(Role.USER);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, "password",
				user.getAuthorities());
		mockMvc.perform(
				get("/admin/creerEvenement").with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testAjouterEvenement() throws Exception {
		Utilisateur admin = new Utilisateur();
		admin.setRole(Role.ADMIN);
		Authentication authentication = new UsernamePasswordAuthenticationToken(admin, "password",
				admin.getAuthorities());
		mockMvc.perform(
				post("/admin/creerEvenement").with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("sport", "Basketball")
						.param("nomEvenement", "France-USA").param("capaciteMax", "3000").param("billetsVendus", "0")
						.param("dateEvenement", "2024-08-01").param("heureEvenement", "18:00")
						.param("lieu", "Accor Arena").param("prixBillet", "50").with(csrf()))
				.andExpect(redirectedUrl("/admin/creerEvenement"));
	}

	@Test
	public void testConsulterEvenement() throws Exception {
		Utilisateur admin = new Utilisateur();
		admin.setRole(Role.ADMIN);
		Authentication authentication = new UsernamePasswordAuthenticationToken(admin, "password",
				admin.getAuthorities());
		mockMvc.perform(get("/admin/consulterEvenement")
				.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))).andExpect(status().isOk())
				.andExpect(view().name("admin/consulterEvenement")).andExpect(model().attributeExists("evenements"));
	}

	@Test
	public void testConsulterEvenementWithUserProfile() throws Exception {
		Utilisateur user = new Utilisateur();
		user.setRole(Role.USER);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, "password",
				user.getAuthorities());
		mockMvc.perform(get("/admin/consulterEvenement")
				.with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testAccueilAdmin() throws Exception {
		Utilisateur admin = new Utilisateur();
		admin.setRole(Role.ADMIN);
		Authentication authentication = new UsernamePasswordAuthenticationToken(admin, "password",
				admin.getAuthorities());
		mockMvc.perform(get("/admin/").with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(status().isOk()).andExpect(view().name("admin/accueilAdmin"));

		mockMvc.perform(
				get("/admin/accueilAdmin").with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(status().isOk()).andExpect(view().name("admin/accueilAdmin"));
	}

	@Test
	public void testAccueilAdminWithUserProfile() throws Exception {
		Utilisateur user = new Utilisateur();
		user.setRole(Role.USER);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, "password",
				user.getAuthorities());
		mockMvc.perform(get("/admin/").with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(status().isForbidden());
		mockMvc.perform(
				get("/admin/accueilAdmin").with(SecurityMockMvcRequestPostProcessors.authentication(authentication)))
				.andExpect(status().isForbidden());
	}

}
