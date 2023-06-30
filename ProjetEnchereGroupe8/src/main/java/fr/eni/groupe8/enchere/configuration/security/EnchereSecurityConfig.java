package fr.eni.groupe8.enchere.configuration.security;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EnchereSecurityConfig {

	protected final Log logger = LogFactory.getLog(getClass());
	private final String SELECT_USER = "select email, mot_de_passe, 1 from UTILISATEURS where email=?";

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);

		return jdbcUserDetailsManager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// Customiser le formulaire
		http.formLogin(form -> {
			form.loginPage("/Connexion").defaultSuccessUrl("/AcceuilConnexion");
		});

		// /logout --> vider la session et le contexte de sécurité
		http.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/SeDeconnecter")).logoutSuccessUrl("/Acceuil")
				.permitAll());
		
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET,"/Connexion").permitAll()
					
					// Permettre aux visiteurs d'accéder à la page d'accueil
					.requestMatchers(HttpMethod.GET, "/Acceuil").permitAll().requestMatchers(HttpMethod.GET, "/")
					.permitAll()
					// Permettre aux visiteurs d'accéder à la page de création d'un compte
					.requestMatchers(HttpMethod.GET, "/CreerCompte").permitAll()
					// .requestMatchers(HttpMethod.POST, "/CreerCompte").permitAll()

					// Permettre à tous d'afficher correctement les images et CSS
					.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
					// Il faut être connecté pour toutes autres URLs
					.anyRequest().authenticated();
		});


		return http.build();
	}
}
