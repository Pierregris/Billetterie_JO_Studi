package fr.studi.billeterie_jo_2024.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public org.springframework.security.crypto.password.PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers("/login", "/register", "/accueil")
				.permitAll().requestMatchers("/admin").hasAuthority("ADMIN").anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.permitAll().defaultSuccessUrl("/moncompte"))
				.logout(logout -> logout.permitAll());
		return http.build();
	}

}
