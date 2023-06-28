package fr.eni.groupe8.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.groupe8.enchere.bll.CategorieServiceImpl;
import fr.eni.groupe8.enchere.bo.Categorie;



@Component
public class StringToCategorieController implements Converter<String, Categorie>{

	
	private CategorieServiceImpl categorieServiceImpl;
	
	@Autowired
	public void setCategorieService(CategorieServiceImpl categorieServiceImpl) {
		this.categorieServiceImpl=categorieServiceImpl;
	}
	
	@Override
	public Categorie convert(String noCategorie) {
		Integer theid =Integer.parseInt(noCategorie);
		return categorieServiceImpl.findCategorieById(theid);
	}

}
