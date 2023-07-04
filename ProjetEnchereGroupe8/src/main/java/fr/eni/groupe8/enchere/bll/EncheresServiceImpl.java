package fr.eni.groupe8.enchere.bll;

import java.math.BigDecimal;

import javax.management.loading.PrivateClassLoader;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Enchere;
import fr.eni.groupe8.enchere.bo.Utilisateur;
import fr.eni.groupe8.enchere.dal.UtilisateurDAOimpl;

@Service ("encheresService")
public class EncheresServiceImpl implements EncheresService {

	/*private Utilisateur Utilisateur;
	private Article article;
	private Enchere enchere;
	
	public EncheresServiceImpl(Utilisateur utilisateur, Article article) {
		this.Utilisateur = utilisateur;
		this.article = article;
	}*/
	
	@Override
	public boolean enchereValideSi(BigDecimal montantEnchere, Article article, Utilisateur utilisateur) {
		
		//if enchere inferieur a montant return false
		if (montantEnchere.compareTo(article.getPrixVente()) <= 0) {
	        return false;
	    }
		//if solde utilisateur inferieur a enchere return false
		if (utilisateur.getCredit().compareTo(montantEnchere) < 0) {
	        return false;
	    }
		//if  dateDebut < enchere < dateFin return false
		
		return true;
		
	}
	
	/*
	 * @Override public Article encherir() {
	 * 
	 * if (article.getPrixVente() > article.getPrixInitial() &&
	 * utilisateur.getCredit() > 0 ) {
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * prix_vente > prix_initial ET credit > 0
	 * 
	 * 
	 * credit - prix_vente ET proposition = meilleure enchère
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return null; }
	 */

}
