package fr.eni.groupe8.enchere.bll;

import java.util.List;

import fr.eni.groupe8.enchere.bo.Categorie;

public interface CategorieService { // Interface de service pour la gestion des catégories.

	public List<Categorie> getListCategorie();

	public Categorie findCategorieById(Integer noCategorie);

}
