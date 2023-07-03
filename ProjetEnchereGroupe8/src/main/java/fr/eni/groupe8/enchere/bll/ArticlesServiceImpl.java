package fr.eni.groupe8.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.dal.ArticlesDAO;


@Service
public class ArticlesServiceImpl implements ArticlesService {

private ArticlesDAO articlesDAO;
	
	public ArticlesServiceImpl(ArticlesDAO articlesDAO) {
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
	
}
