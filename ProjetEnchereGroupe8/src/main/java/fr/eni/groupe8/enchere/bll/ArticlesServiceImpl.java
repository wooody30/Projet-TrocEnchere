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
	private Article article;
	private Utilisateur utilisateur;
	private UtilisateurDAO utilisateurDAO;

	public ArticlesServiceImpl(ArticlesDAO articlesDAO, UtilisateurDAO utilisateurDAO) {
		this.articlesDAO = articlesDAO;
		this.utilisateurDAO = utilisateurDAO;
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
	public void encherir(Article noArticle, Utilisateur utilisateur) {
		// TODO Auto-generated method stub



	}


	/*
	 * @Override public void encherir(Article article, Utilisateur utilisateur) {
	 * 
	 * // Check le crédit de l'acheteur potentiel if (utilisateur.getCredit() <
	 * utilisateur.getPropositionAcheteur()) { throw new
	 * IllegalArgumentException("Crédits insuffisants"); }
	 * 
	 * // Check si le prix proposé est sup au prix initial if
	 * (utilisateur.getPropositionAcheteur() <= article.getPrixInitial()) { throw
	 * new
	 * IllegalArgumentException("Le prix proposé doit être supérieur à la mise à prix"
	 * ); }
	 * 
	 * // MAJ l'article avec le nouveau meilleur prix et le meilleur enchérisseur
	 * article.setMeilleurPrix(utilisateur.getPropositionAcheteur());
	 * article.setMeilleurEncherisseur(utilisateur);
	 * articlesDAO.saveArticle(article);
	 * 
	 * // Débiter le crédit du user utilisateur.setCredit(utilisateur.getCredit() -
	 * utilisateur.getPropositionAcheteur()); utilisateurDAO.save(utilisateur);
	 * 
	 * }
	 */



}
