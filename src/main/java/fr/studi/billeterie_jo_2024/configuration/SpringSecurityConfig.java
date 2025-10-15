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
				.permitAll().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/2fa").hasRole("PRE_AUTH")
				.anyRequest().hasAnyRole("ADMIN", "USER"))
				.formLogin(formLogin -> formLogin.loginPage("/login").usernameParameter("mail")

						.authenticationDetailsSource(authenticationDetailsSourceConfig)
						.successHandler(twoFASuccessHandler))
				.logout(logout -> logout.logoutSuccessUrl("/accueil").invalidateHttpSession(true)
						.deleteCookies("JSESSIONID").permitAll())
				.sessionManagement(session -> session.sessionConcurrency(
						concurrency -> concurrency.maximumSessions(1).maxSessionsPreventsLogin(false)));
		return http.build();
	}

}
