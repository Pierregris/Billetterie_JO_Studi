package fr.studi.billeterie_jo_2024.configuration;

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

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(
				authorizeRequests -> authorizeRequests
						.requestMatchers("/style/**", "/scripts/**", "/img/**", "/fonts/**", "/login", "/register",
								"/accueil", "/")
						.permitAll().requestMatchers("/admin").hasAuthority("ADMIN").anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login").usernameParameter("mail"))
				.logout(logout -> logout.logoutSuccessUrl("/accueil").permitAll());
		return http.build();
	}

}
