package fr.eni.groupe8.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Categorie;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Repository
public class ArticlesDAOimpl implements ArticlesDAO {

	private static final String FIND_ALL = "select no_article, nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente"
			+ "								no_utilisateur, no_categorie  from ARTICLES_VENDUS";

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
		
			
			Utilisateur vendeur = null;
			vendeur = utilisateurDAO.readUtilisateur(rs.getInt("no_utilisateur"));
			article.setVendeur(vendeur);
			
			Categorie noCategorie = null;
			noCategorie = categorieDAO.readCategorie(rs.getInt("no_categorie"));
			article.setNoCategorie(noCategorie);
			
			
			return article;
		}

	}

	@Override
	public List<Article> findAll() {
		List<Article> lstArticle = namedParameterJdbcTemplate.query(FIND_ALL, new ArticleRowMapper());

		return lstArticle;

	}

}
