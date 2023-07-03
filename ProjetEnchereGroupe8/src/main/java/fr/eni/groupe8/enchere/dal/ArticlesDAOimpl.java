package fr.eni.groupe8.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Categorie;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Repository
public class ArticlesDAOimpl implements ArticlesDAO {

	private static final String FIND_ALL = "select no_article, nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie  from ARTICLES_VENDUS";
	private static final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) values (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :no_utilisateur, :no_categorie)";

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
	public void saveArticle(Article article) {

		MapSqlParameterSource paramSrc = new MapSqlParameterSource("nom_article", article.getNomArticle());
		paramSrc.addValue("description", article.getDescription());
		paramSrc.addValue("no_categorie",
				article.getCategorie() == null ? null : article.getCategorie().getNoCategorie());
		paramSrc.addValue("prix_initial", article.getPrixInitial());
		paramSrc.addValue("date_debut_encheres", article.getDateDebutEncheres());
		paramSrc.addValue("date_fin_encheres", article.getDateFinEncheres());
		System.out.println("saveArticle");
		namedParameterJdbcTemplate.update(INSERT, paramSrc);

	}
}
