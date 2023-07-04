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
	//private final String SELECT_USER = "select email, mot_de_passe, 1 from UTILISATEURS where email=?";
	//private final String SELECT_ROLES = "select email, 'admin' from UTILISATEURS where email=?";
	private final String SELECT_USER = "select email, mot_de_passe, 1 from UTILISATEURS where ? IN (pseudo, email)";
	private final String SELECT_ROLES = "select email, 'admin' from UTILISATEURS where ? IN (pseudo, email)";
	
	
	@Autowired
    private DataSource dataSource ;
	
    /*@Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.jdbcAuthentication()
            .dataSource( dataSource )
            .usersByUsernameQuery( "SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE pseudo = ? " )
            .authoritiesByUsernameQuery( "SELECT ?, 'admin' " )
            .passwordEncoder( passwordEncoder )
            ;
    }*/
    
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		auth.jdbcAuthentication()
			.dataSource( dataSource )
			.usersByUsernameQuery( SELECT_USER )
			.authoritiesByUsernameQuery( SELECT_ROLES )
			.passwordEncoder( passwordEncoder )
			;
	}
    
    
	/*@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);

		return jdbcUserDetailsManager;
	}*/
	

    //private final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder() ;

	/*
    private final PasswordEncoder passwordEncoder  = new PasswordEncoder() {

        @Override
        public String encode(CharSequence rawPassword) {
            System.out.println( "encode: " + rawPassword);
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            System.out.println( "matches: " + rawPassword + " " + encodedPassword );
            if ( encodedPassword.startsWith("{noop}") ) {
                return encodedPassword.endsWith(rawPassword.toString());
            } else if (encodedPassword.startsWith("$2a$")) {
                var result = new BCryptPasswordEncoder().matches( rawPassword, encodedPassword ) ;
                return result ;
            } else {
            	return encodedPassword.endsWith(rawPassword.toString());
            }
        }
    } ;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    } */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}

	private final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder() ;

	/*private final PasswordEncoder passwordEncoder  = new PasswordEncoder() {

		@Override
		public String encode(CharSequence rawPassword) {
			System.out.println( "encode: " + rawPassword);
			return rawPassword.toString();
		}

		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			System.out.println( "matches: " + rawPassword + " " + encodedPassword );
			if ( encodedPassword.startsWith("{noop}") ) {
				return encodedPassword.endsWith(rawPassword.toString());
			} else if (encodedPassword.startsWith("$2a$")) {
				var result = new BCryptPasswordEncoder().matches( rawPassword, encodedPassword ) ;
				return result ;
			} else {
				return encodedPassword.endsWith(rawPassword.toString());
			}
		}
	} ;*/

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// Customiser le formulaire
		/*http.formLogin(form -> {
			form.loginProcessingUrl("/login")
			//.loginPage("/login")
			//.defaultSuccessUrl("/AcceuilConnexion")
			//.usernameParameter("pseudo")
			//.passwordParameter("motDePasse")
			.permitAll()
			;
		});*/
		
		http.formLogin( login -> {
			login
				.loginPage("/Connexion")
				.loginProcessingUrl("/session")
				.defaultSuccessUrl("/AcceuilConnexion")
				.failureUrl("/login")
				.usernameParameter("pseudo")
				.passwordParameter("motDePasse")
				.permitAll()
				;
		});

		// /logout --> vider la session et le contexte de sécurité
		/*http.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/SeDeconnecter")).logoutSuccessUrl("/Acceuil")
		// .permitAll()*/
		
		http.logout( logout ->
		logout
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/SeDeconnecter"))
			.logoutSuccessUrl("/Acceuil")
		//	.permitAll()
		);

		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET, "/login").permitAll()

					// Permettre aux visiteurs d'accéder à la page d'accueil
					//.requestMatchers(HttpMethod.GET, "/Acceuil").permitAll()
					//.requestMatchers(HttpMethod.GET, "/").permitAll()
			.requestMatchers( HttpMethod.GET, "/" ).permitAll()
			.requestMatchers( HttpMethod.GET, "/Acceuil" ).permitAll()
			.requestMatchers( HttpMethod.GET, "/AcceuilConnexion" ).authenticated()
			
			
					// Permettre aux visiteurs d'accéder à la page de création d'un compte
					//.requestMatchers(HttpMethod.POST, "/CreerCompte").permitAll()
					//.requestMatchers(HttpMethod.GET, "/CreerCompte").permitAll()
			.requestMatchers( HttpMethod.GET, "/CreerCompte" ).permitAll()
			.requestMatchers( HttpMethod.POST, "/CreerCompte" ).permitAll()
			
			
					// Permettre à tous d'afficher correctement les images et CSS
					//.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
			.requestMatchers("/css/*").permitAll()
			.requestMatchers("/images/*").permitAll()
					
					// Il faut être connecté pour toutes autres URLs

					//.requestMatchers(HttpMethod.POST, "/session").permitAll()
					//.anyRequest().authenticated()
					//.anyRequest().permitAll();
			.requestMatchers( HttpMethod.POST, "/session" ).permitAll()
			.requestMatchers("/**").authenticated()
		//	.anyRequest().permitAll()
					;
		});
		return http.build();
	}
}
