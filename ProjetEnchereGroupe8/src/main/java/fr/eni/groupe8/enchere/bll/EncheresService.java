package fr.eni.groupe8.enchere.bll;

import java.math.BigDecimal;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Utilisateur;

public interface EncheresService { // Interface de service pour la gestion des enchères.


	public boolean enchereValideSi(BigDecimal montantEnchere, Article article, Utilisateur utilisateur);
	
}
