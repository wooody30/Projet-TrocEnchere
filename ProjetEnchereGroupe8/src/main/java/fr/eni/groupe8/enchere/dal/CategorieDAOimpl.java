package fr.eni.groupe8.enchere.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.groupe8.enchere.bo.Categorie;


public class CategorieDAOimpl implements CategorieDAO {

	private List<Categorie>lstCategorie;
	
	public CategorieDAOimpl() {
		lstCategorie = new ArrayList<Categorie>();
		
		lstCategorie.add(new Categorie(1,"Informatique"));
		
	}
	
}
