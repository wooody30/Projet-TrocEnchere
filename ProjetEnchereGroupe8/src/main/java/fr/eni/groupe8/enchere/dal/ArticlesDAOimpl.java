package fr.eni.groupe8.enchere.dal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Categorie;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Repository
public class ArticlesDAOimpl implements ArticlesDAO {

	List<Article> articles = new ArrayList<>();
	private static Integer compteur=1;
	
	
	@Override
	public List<Article> findAllArticles() {
		return articles;
	}
	
	
}
