package fr.eni.groupe8.enchere.dal;

import java.util.List;

import fr.eni.groupe8.enchere.bo.Article;

public interface ArticlesDAO {

	List<Article> findAll();

	void saveArticle(Article article);

	Article find(Integer noArticle);

	// void saveRetrait(Retrait retrait);

}
