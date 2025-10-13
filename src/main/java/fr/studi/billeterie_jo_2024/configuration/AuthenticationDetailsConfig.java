package fr.studi.billeterie_jo_2024.configuration;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationDetailsConfig extends WebAuthenticationDetails {
	private static final long serialVersionUID = 1L;

	private final String otp;

	public AuthenticationDetailsConfig(HttpServletRequest request) {
		super(request);
		otp = request.getParameter("otp");
	}

	public String getOtp() {
		return otp;
	}

}
