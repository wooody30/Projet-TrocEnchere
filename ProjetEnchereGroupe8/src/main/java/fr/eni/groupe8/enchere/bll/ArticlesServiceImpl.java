package fr.eni.groupe8.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Utilisateur;
import fr.eni.groupe8.enchere.dal.ArticlesDAO;
import fr.eni.groupe8.enchere.dal.UtilisateurDAO;

@Service
public class ArticlesServiceImpl implements ArticlesService {

	private ArticlesDAO articlesDAO;

	public ArticlesServiceImpl(ArticlesDAO articlesDAO, UtilisateurDAO utilisateurDAO) {
		this.articlesDAO = articlesDAO;
	}

	@Override
	public List<Article> findAllArticles() {
		return articlesDAO.findAll();
	}

	@Override
	public void ajouterArticle(Article article) {
		articlesDAO.saveArticle(article);
		System.out.println("saveArticle");

	}

	@Override
	public Article articleById(Integer noArticle) {

		return articlesDAO.find(noArticle);

	}

	@Override
	public Article articleByPrixVente(Integer prixVente) {
		return articlesDAO.find(prixVente);
	}

	@Override
	public void encherir(Article noArticle, Utilisateur utilisateur) {
		// TODO Auto-generated method stub
	}

}
