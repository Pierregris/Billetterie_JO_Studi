package fr.studi.billeterie_jo_2024.controllertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.studi.billeterie_jo_2024.configuration.AuthenticationDetailsSourceConfig;
import fr.studi.billeterie_jo_2024.configuration.SpringSecurityConfig;
import fr.studi.billeterie_jo_2024.configuration.TwoFactorAuthenticationSuccessHandler;
import fr.studi.billeterie_jo_2024.controller.UtilisateurController;
import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
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

	@MockitoBean
	AuthenticationDetailsSourceConfig authenticationDetailsSourceConfig;

	@MockitoBean
	TwoFactorAuthenticationSuccessHandler twoFactorAuthenticationSuccessHandler;

	@MockitoBean
	UtilisateurService utilisateurService;

	@MockitoBean
	EmailService emailService;

	@MockitoBean
	ReservationService reservationService;

	@MockitoBean
	UtilisateurRepository utilisateurRepository;

	@Test
	public void testGetRegister() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("register"))
				.andExpect(model().attributeExists("utilisateur"));
	}

	@Test
	@WithMockUser
	public void testRegisterUtilisateurValide() throws Exception {
		ArgumentCaptor<Utilisateur> utilisateurCaptor = ArgumentCaptor.forClass(Utilisateur.class);

		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("nom", "Dupond")
				.param("prenom", "Jean").param("adresse", "rue de la Paix").param("codePostal", "75001")
				.param("ville", "Paris").param("mail", "jeandupond@mail.fr").param("telephone", "0607080910")
				.param("password", "Motdepasse123!").with(csrf())).andExpect(redirectedUrl("/accueil"))
				.andExpect(flash().attribute("messageSucces",
						"Votre compte a été créé avec succès, il ne vous reste plus qu'à l'activer en cliquant sur le lien reçu par email!"));

		verify(utilisateurService, times(1)).createUtilisateur(utilisateurCaptor.capture());
		Utilisateur utilisateur = utilisateurCaptor.getValue();
		assertThat(utilisateur.getNom().equals("Dupond"));
		verify(emailService, times(1)).sendEmail(eq("jeandupond@mail.fr"), eq("Inscription validée"), anyString());
	}

	@Test
	@WithMockUser
	public void testRegisterUtilisateurDejaEnBase() throws Exception {
		when(utilisateurService.getUtilisateurbyMail("jeandupond@mail.fr")).thenReturn(new Utilisateur());
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("nom", "Dupond")
				.param("prenom", "Jean").param("adresse", "rue de la Paix").param("codePostal", "75001")
				.param("ville", "Paris").param("mail", "jeandupond@mail.fr").param("telephone", "0607080910")
				.param("motDePasse", "motdepasse").with(csrf())).andExpect(redirectedUrl("/register"))
				.andExpect(flash().attribute("messageErreurDoublon",
						"Un compte existe déjà avec cette adresse mail. Connectez-vous directement : "));
	}

	@Test
	public void testAffPageLogin() throws Exception {
		mockMvc.perform(get("/login")).andExpect(view().name("login"));
	}

	@Test
	public void testAffPageLoginError() throws Exception {
		mockMvc.perform(get("/login").param("error", "true")).andExpect(model().attributeExists("errorMessage"));
	}

	@Test
	public void testAffPageAccueil() throws Exception {
		mockMvc.perform(get("/accueil", "/")).andExpect(view().name("accueil"));
	}

	@Test
	public void testGet2fa() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setRole(Role.PRE_AUTH);
		Authentication authentification = new UsernamePasswordAuthenticationToken(utilisateur, "password",
				utilisateur.getAuthorities());

		mockMvc.perform(get("/2fa").with(authentication(authentification))).andExpect(view().name("2fa"));
	}

	@Test
	public void testCorrectOtp() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setRole(Role.PRE_AUTH);
		Authentication authentification = new UsernamePasswordAuthenticationToken(utilisateur, "password",
				utilisateur.getAuthorities());
		utilisateur.setOtpValidity(LocalDateTime.now().plusMinutes(5));
		String otp = "otpTest";
		when(utilisateurRepository.findByOtp(otp)).thenReturn(Optional.of(utilisateur));
		mockMvc.perform(post("/2fa").param("otp", otp).with(authentication(authentification)).with(csrf()))
				.andExpect(redirectedUrl("/accueil"));
	}

	@Test
	public void testInvalidOTP() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setRole(Role.PRE_AUTH);
		Authentication authentification = new UsernamePasswordAuthenticationToken(utilisateur, "password",
				utilisateur.getAuthorities());
		utilisateur.setOtpValidity(LocalDateTime.now().plusMinutes(5));
		String otp = "otpTest";
		when(utilisateurRepository.findByOtp(otp)).thenReturn(Optional.empty());
		mockMvc.perform(post("/2fa").param("otp", otp).with(authentication(authentification)).with(csrf()))
				.andExpect(flash().attribute("erreurOTP", "Le code saisi est incorrect, veuillez réessayer"))
				.andExpect(redirectedUrl("/2fa"));
	}

	@Test
	public void testExpiredOTP() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setRole(Role.PRE_AUTH);
		Authentication authentification = new UsernamePasswordAuthenticationToken(utilisateur, "password",
				utilisateur.getAuthorities());
		utilisateur.setOtpValidity(LocalDateTime.now().minusMinutes(5));
		String otp = "otpTest";
		when(utilisateurRepository.findByOtp(otp)).thenReturn(Optional.of(utilisateur));
		mockMvc.perform(post("/2fa").param("otp", otp).with(authentication(authentification)).with(csrf()))
				.andExpect(flash().attribute("erreurOTPexpire", "OTP expiré")).andExpect(redirectedUrl("/2fa"));
	}

	@Test
	public void testAffReservations() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setRole(Role.USER);
		Authentication authentification = new UsernamePasswordAuthenticationToken(utilisateur, "password",
				utilisateur.getAuthorities());

		mockMvc.perform(get("/moncompte").with(authentication(authentification)))
				.andExpect(model().attributeExists("reservations"));
	}

	@Test
	@WithMockUser
	public void testValidationEmailValide() throws Exception {
		UUID token = UUID.randomUUID();
		when(utilisateurRepository.findByActivationToken(token)).thenReturn(Optional.of(new Utilisateur()));
		mockMvc.perform(get("/activation").param("token", token.toString())).andExpect(redirectedUrl("/accueil"))
				.andExpect(flash().attribute("messageActivation", "Votre compte a été activé avec succès !"));

		ArgumentCaptor<Utilisateur> utilisateurCaptor = ArgumentCaptor.forClass(Utilisateur.class);
		verify(utilisateurRepository).save(utilisateurCaptor.capture());
		Utilisateur utilisateur = utilisateurCaptor.getValue();
		assertThat(utilisateur.isEnabled()).isTrue();
		assertThat(utilisateur.getActivationToken()).isNull();
	}

	@Test
	@WithMockUser
	public void testValidationEmailNonValide() throws Exception {
		UUID token = UUID.randomUUID();
		when(utilisateurRepository.findByActivationToken(token)).thenReturn(Optional.empty());
		mockMvc.perform(get("/activation").param("token", token.toString())).andExpect(redirectedUrl("/accueil"))
				.andExpect(flash().attribute("erreurActivation", "Le lien d'activation n'est pas valide"));
	}

}
