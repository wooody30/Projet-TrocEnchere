package fr.eni.groupe8.enchere.dal;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.groupe8.enchere.bo.Utilisateur;

@Repository
public class UtilisateurDAOimpl implements UtilisateurDAO {

	private static final String FIND_ALL = "select * from UTILISATEURS";
	private static final String FIND_BY_ID = "select * from UTILISATEURS where no_utilisateur=:no_utilisateur";

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Utilisateur> findAllUtilisateur() {

		List<Utilisateur> lstUtilisteur = namedParameterJdbcTemplate.query(FIND_ALL,
				new BeanPropertyRowMapper<>(Utilisateur.class));

		return lstUtilisteur;
	}

	public Utilisateur readUtilisateur(Integer no_utilisateur) {
		Map<String, Object> params = new HashMap<>();
		params.put("no_utilisateur", no_utilisateur);

		Utilisateur util = null;

		util = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, params,
				new BeanPropertyRowMapper<>(Utilisateur.class));
		return util;

	}
}