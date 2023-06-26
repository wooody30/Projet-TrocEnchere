package fr.eni.groupe8.enchere.dal;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.eni.ToDo.bo.ToDo;

@Repository
public class ArticlesDAOimpl implements ArticlesDAO {

	List<Article> articles = new ArrayList<>();
	
	
	
	@Override
	public List<Article> findAllArticles() {
		return articles;
	}
	
	
}
