package fr.eni.groupe8.enchere.dal;

import java.util.List;

import fr.eni.groupe8.enchere.bo.Categorie;

public interface CategorieDAO {

	List<Categorie>findAllCategorie();

	Categorie readCategorie(Integer nocategorie);

	

}
