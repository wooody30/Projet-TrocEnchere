package fr.eni.groupe8.enchere.dal;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.groupe8.enchere.bo.Categorie;

@Repository
public class CategorieDAOimpl implements CategorieDAO {

	private static final String FIND_ALL = "select * from CATEGORIES";
	private static final String FIND_BY_ID = "select * from CATEGORIES where no_categorie=:no_categorie";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Categorie> findAllCategorie() {
		List<Categorie> lstCategorie = namedParameterJdbcTemplate.query(FIND_ALL,
				new BeanPropertyRowMapper<>(Categorie.class));
		System.out.println("findAllCategorie");
		return lstCategorie;
	}

	public Categorie readCategorie(Integer nocategorie) {
		Map<String, Object> params = new HashMap<>();
		params.put("no_categorie", nocategorie);

		Categorie cat = null;
		cat = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, params,
				new BeanPropertyRowMapper<>(Categorie.class));
		System.out.println("readCategorie");
		return cat;
	}

}
