package fr.eni.groupe8.enchere.dal;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import fr.eni.groupe8.enchere.bo.Utilisateur;



@Repository
public class UtilisateurDAOimpl implements UtilisateurDAO {

	private static final String FIND_ALL = "select * from UTILISATEURS";
	private static final String FIND_BY_ID = "select * from UTILISATEURS where no_utilisateur=:no_utilisateur";
	private final static String INSERT = "insert into UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values ( :pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, :administrateur)" ;
	private final static String UPDATE = "update UTILISATEURS set pseudo=:pseudo, nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, code_postal=:codePostal, ville=:ville, mot_de_passe=:motDePasse, :credit=:credit, administrateur=:administrateur" ;
	private static final String FIND_BY_EMAIL = "select * from UTILISATEURS WHERE email=:email";
//	private static final String FIND_BY_EMAIL_2 = "select * from UTILISATEURS WHERE email=?"; // Autre méthode proposée par le formateur
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Utilisateur> findAllUtilisateur() {

		List<Utilisateur> lstUtilisteur = namedParameterJdbcTemplate.query(FIND_ALL,
				new BeanPropertyRowMapper<>(Utilisateur.class));

		return lstUtilisteur;
	}

	
	
	//@Override
	public Utilisateur readUtilisateur_email(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("email", email);
		return namedParameterJdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters, new BeanPropertyRowMapper<>(Utilisateur.class));
	}
	
		
	/** Autre méthode proposée par le formateur
	@Override
	public Utilisateur readUtilisateur2(String email) {
		
		return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(FIND_BY_EMAIL, new BeanPropertyRowMapper<>(Utilisateur.class), email);
	}
	
	 */
	
	
	
	public Utilisateur readUtilisateur(Integer noUtilisateur) {
		Map<String, Object> params = new HashMap<>();
		params.put("no_utilisateur", noUtilisateur);

		Utilisateur util = null;

		util = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, params,
				new BeanPropertyRowMapper<>(Utilisateur.class));
		return util;

	}
	
	
	@Override
	public void save(Utilisateur utilisateur) {
		utilisateur.setAdministrateur(false);
		
	    if (utilisateur.getNoUtilisateur() == null) {
	        // Insertion d'un nouvel utilisateur
	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        namedParameterJdbcTemplate.update(INSERT, new BeanPropertySqlParameterSource(utilisateur), keyHolder);
	        utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
	        System.out.println("Utilisateur inséré : " + utilisateur);
	    } else {
	        // Mise à jour d'un utilisateur existant
	        namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(utilisateur));
	    }
	}

	
	
	
}