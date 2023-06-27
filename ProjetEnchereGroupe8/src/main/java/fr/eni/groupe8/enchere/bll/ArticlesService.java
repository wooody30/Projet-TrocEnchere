package fr.eni.groupe8.enchere.bll;

import java.util.List;
import fr.eni.groupe8.enchere.bo.Article;


public interface ArticlesService { //Interface de service pour la gestion des articles.

	List<Article> findAllArticles();
	
}
