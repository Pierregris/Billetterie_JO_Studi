package fr.studi.billeterie_jo_2024.configuration;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationDetailsSourceConfig extends WebAuthenticationDetailsSource {

	public WebAuthenticationDetails builDetails(HttpServletRequest request) {
		return new AuthenticationDetailsConfig(request);
	}
}
