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

	public String generateOtp() {
		String chararacters = "ABCDEFGHIJKLMNOP0123456789";
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

		Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("PRE_AUTH"));

		Authentication preAuth = new UsernamePasswordAuthenticationToken(utilisateur, authentication.getCredentials(),
				authorities);

		Boolean isAdmin = utilisateur.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

		if (isAdmin) {
			response.sendRedirect("/accueil");
		} else {

			SecurityContextHolder.getContext().setAuthentication(preAuth);

			String otp = generateOtp();
			utilisateur.setOtp(otp);
			utilisateur.setOtpValidity(LocalDateTime.now().plusMinutes(5));
			utilisateurRepository.save(utilisateur);

			emailService.sendEmail(utilisateur.getMail(), "Code de vérification",
					"Voici votre code de vérification : " + otp);

			response.sendRedirect("/2fa");
		}

	}

}
