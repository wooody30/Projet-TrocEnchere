package fr.eni.groupe8.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Categorie;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Repository
public class ArticlesDAOimpl implements ArticlesDAO {

	private static final String FIND_ALL = "select no_article, nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie  from ARTICLES_VENDUS";
	private static final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) values (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :no_utilisateur, :no_categorie)";
//	private static final String UPDATE = "update ARTICLES_VENDUS set nom_article=:nom_article, description=:description, date_debut_encheres=:date_debut_encheres, date_fin_encheres=:date_fin_encheres, prix_initial=:prix_initial, no_utilisateur=:no_utilisateur, no_categorie=:no_categorie";
	private static final String FIND_BY_ID = "select * from ARTICLES_VENDUS where no_article=:no_article";
//	private static final String INSERT_RETRAITS = "insert into RETRAITS (rue, code_postal, ville) values (:rue, :code_postal, :ville)";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private UtilisateurDAO utilisateurDAO;

	@Autowired
	private CategorieDAO categorieDAO;

	class ArticleRowMapper implements RowMapper<Article> {

		@Override
		public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
			Article article = new Article();
			// Mapping
			article.setNoArticle(rs.getInt("no_article"));
			article.setNomArticle(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
			article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
			article.setPrixInitial(rs.getInt("prix_initial"));
			article.setPrixVente(rs.getInt("prix_vente"));

			Utilisateur vendeur;
			vendeur = utilisateurDAO.readUtilisateur(rs.getInt("no_utilisateur"));
			article.setVendeur(vendeur);

			Categorie categorie;
			categorie = categorieDAO.readCategorie(rs.getInt("no_categorie"));
			article.setCategorie(categorie);
			System.out.println("mapRow");
			return article;
		}

	}

	@Override
	public List<Article> findAll() {
		List<Article> lstArticle = namedParameterJdbcTemplate.query(FIND_ALL, new ArticleRowMapper());
		System.out.println("findAll");
		return lstArticle;

	}

	@Override
	public Article find(Integer noArticle) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_article", noArticle);

		Article article = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, paramSrc, new ArticleRowMapper());
		return article;
	}
/*******************************************************************************************************/
	@Override
	public void saveArticle(Article article) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); //stockage de la clé generee lors de l'insertion de l'article en SQL

		MapSqlParameterSource paramSrc = new MapSqlParameterSource("nom_article", article.getNomArticle()); //objet crée pour stocker les param de l'article
		paramSrc.addValue("no_utilisateur", article.getVendeur().getNoUtilisateur());
		paramSrc.addValue("description", article.getDescription());
		paramSrc.addValue("no_categorie",
				//les addValue ajoute les parametres un par un 

				// TODO pas de categorie null notmalement puisquil ya une categorie NOT NULL
				article.getCategorie() == null ? null : article.getCategorie().getNoCategorie());

		paramSrc.addValue("prix_initial", article.getPrixInitial());
		paramSrc.addValue("date_debut_encheres", article.getDateDebutEncheres());
		paramSrc.addValue("date_fin_encheres", article.getDateFinEncheres());
		System.out.println("saveArticle");
		namedParameterJdbcTemplate.update(INSERT, paramSrc, keyHolder);
		article.setNoArticle(keyHolder.getKey().intValue());
/************************************************************************************************************/
	}

	/*
	 * @Override public void saveRetrait(Retrait retrait) { KeyHolder keyHolder =
	 * new GeneratedKeyHolder(); MapSqlParameterSource paramSrc = new
	 * MapSqlParameterSource("rue", retrait.getRue());
	 * paramSrc.addValue("code_postal", retrait.getCode_postal());
	 * paramSrc.addValue("ville", retrait.getVille());
	 * namedParameterJdbcTemplate.update(INSERT_RETRAITS, paramSrc,keyHolder);
	 * retrait.setNoArticle(keyHolder.getKey().intValue()); }
	 */

}
