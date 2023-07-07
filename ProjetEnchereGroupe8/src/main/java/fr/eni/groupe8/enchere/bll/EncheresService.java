package fr.eni.groupe8.enchere.bll;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Enchere;
import fr.eni.groupe8.enchere.bo.Utilisateur;

public interface EncheresService { // Interface de service pour la gestion des enchères.

	void SaveNewEnchere(Enchere enchere);

	boolean enchereValideSi(int montantEnchere, Article article, Utilisateur utilisateur);

}
