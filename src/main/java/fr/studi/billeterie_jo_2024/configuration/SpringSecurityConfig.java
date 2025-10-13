package fr.studi.billeterie_jo_2024.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	AuthenticationDetailsSourceConfig authenticationDetailsSourceConfig;

	@Autowired
	TwoFactorAuthenticationSuccessHandler twoFASuccessHandler;

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				.requestMatchers("/style/**", "/scripts/**", "/img/**", "/fonts/**", "/login", "/register", "/accueil",
						"/", "/activation")
				.permitAll().requestMatchers("/admin/**").hasAuthority("ADMIN").requestMatchers("/2fa")
				.hasAuthority("PRE_AUTH").anyRequest().hasAnyAuthority("ADMIN", "USER"))
				.formLogin(formLogin -> formLogin.loginPage("/login").usernameParameter("mail")
						.successHandler(twoFASuccessHandler))
				.logout(logout -> logout.logoutSuccessUrl("/accueil").permitAll());
		return http.build();
	}

}
