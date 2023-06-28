package fr.eni.groupe8.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Categorie;
import fr.eni.groupe8.enchere.dal.CategorieDAO;

@Service
public class CategorieServiceImpl implements CategorieService {

	private CategorieDAO categorieDAO;
	
	public CategorieServiceImpl (CategorieDAO categorieDAO) {
	this.categorieDAO=categorieDAO;
	}
	
	@Override
	public List<Categorie> getListCategorie() {
		
		return categorieDAO.findAllCategorie();
	}

	@Override
	public Categorie findCategorieById(Integer noCategorie) {
		
		return categorieDAO.readCategorie(noCategorie);
	}

}
