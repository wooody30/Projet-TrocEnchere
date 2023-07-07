package fr.eni.groupe8.enchere.configuration.security;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EnchereSecurityConfig {

	protected final Log logger = LogFactory.getLog(getClass());

	private final String SELECT_USER = "select email, mot_de_passe, 1 from UTILISATEURS where ? IN (pseudo, email)";
	private final String SELECT_ROLES = "select email, 'admin' from UTILISATEURS where ? IN (pseudo, email)";

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(SELECT_USER)
				.authoritiesByUsernameQuery(SELECT_ROLES).passwordEncoder(passwordEncoder);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.formLogin(login -> {
			login.loginPage("/Connexion").loginProcessingUrl("/session").defaultSuccessUrl("/AcceuilConnexion")
					.failureUrl("/login").usernameParameter("pseudo").passwordParameter("motDePasse").permitAll();
		});

		http.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/SeDeconnecter")).logoutSuccessUrl("/Acceuil")
		);

		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET, "/login").permitAll()

					// Permettre aux visiteurs d'accéder à la page d'accueil

					.requestMatchers(HttpMethod.GET, "/").permitAll().requestMatchers(HttpMethod.GET, "/Acceuil")
					.permitAll().requestMatchers(HttpMethod.GET, "/AcceuilConnexion").authenticated()
					.requestMatchers(HttpMethod.GET, "/detailarticle").permitAll()
					.requestMatchers(HttpMethod.GET, "/profil").authenticated()
					.requestMatchers(HttpMethod.GET, "/NouvelleVente").authenticated()
					.requestMatchers(HttpMethod.POST, "/ajouterVente").authenticated()

					// Permettre aux visiteurs d'accéder à la page de création d'un compte

					.requestMatchers(HttpMethod.GET, "/CreerCompte").permitAll()
					.requestMatchers(HttpMethod.POST, "/CreerCompte").permitAll()

					// Permettre à tous d'afficher correctement les images et CSS
					
					.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()

					// Il faut être connecté pour toutes autres URLs

					.requestMatchers(HttpMethod.POST, "/session").authenticated();

		});
		return http.build();

	}
}