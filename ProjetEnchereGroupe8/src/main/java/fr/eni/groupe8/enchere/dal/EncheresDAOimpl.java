package fr.eni.groupe8.enchere.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import fr.eni.groupe8.enchere.bo.Enchere;

@Repository
public class EncheresDAOimpl implements EncheresDAO {

	private static final String INSERT = "insert into ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void saveEnchere(Enchere enchere) {

		// if (enchere.getNoEnchere() == null) {
		// Insertion d'une nouvelle enchère
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(INSERT, new BeanPropertySqlParameterSource(enchere), keyHolder);
		enchere.setNoEnchere(keyHolder.getKey().intValue());
		System.out.println("Enchère insérée : " + enchere);
		// } else {
		// Mise à jour d'une enchère existante
		// namedParameterJdbcTemplate.update(UPDATE, new
		// BeanPropertySqlParameterSource(enchere));
		System.out.println("saveEnchere");
		// }
	}
}