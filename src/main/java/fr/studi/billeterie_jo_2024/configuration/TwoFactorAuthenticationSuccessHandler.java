package fr.studi.billeterie_jo_2024.configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import fr.studi.billeterie_jo_2024.pojo.Utilisateur;
import fr.studi.billeterie_jo_2024.repository.UtilisateurRepository;
import fr.studi.billeterie_jo_2024.service.EmailService;
import fr.studi.billeterie_jo_2024.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class TwoFactorAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	EmailService emailService;

	// Génération de l'OTP, 6 caractères parmi lettre et /ou chiffres
	public String generateOtp() {
		String chararacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			otp.append(chararacters.charAt(random.nextInt(chararacters.length())));
		}
		return otp.toString();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// On récupère l'utilisateur "préconnecté", et on lui attribue le rôle
		// "PRE_AUTH" qui donne accès à la page "2fa"
		Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_PRE_AUTH"));

		Authentication preAuth = new UsernamePasswordAuthenticationToken(utilisateur, authentication.getCredentials(),
				authorities);

		Boolean isAdmin = utilisateur.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

		// Si l'utilisateur est admin, on ne redirige pas vers le 2fa
		if (isAdmin) {
			response.sendRedirect("/accueil");
		} else {

			SecurityContextHolder.getContext().setAuthentication(preAuth);
			// On génère l'OTP et on l'assigne à l'utilisateur
			String otp = generateOtp();
			utilisateur.setOtp(otp);
			utilisateur.setOtpValidity(LocalDateTime.now().plusMinutes(5));
			utilisateurRepository.save(utilisateur);
			// On envoie l'OTP par mail à l'utilisateur
			emailService.sendEmail(utilisateur.getMail(), "Code de vérification",
					"Voici votre code de vérification : " + otp);
			// On redirige l'utilisateur vers la page 2fa
			response.sendRedirect("/2fa");
		}

	}

}
