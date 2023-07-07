package fr.eni.groupe8.enchere.bll;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Enchere;
import fr.eni.groupe8.enchere.bo.Utilisateur;
import fr.eni.groupe8.enchere.dal.EncheresDAO;

@Service("encheresService")
public class EncheresServiceImpl implements EncheresService {

	private EncheresDAO encheresDAO;

	@Override
	public void SaveNewEnchere(Enchere enchere) {
		encheresDAO.saveEnchere(enchere);

	}

	@Override
	public boolean enchereValideSi(int montantEnchere, Article article, Utilisateur utilisateur) {

		// if enchere inferieur a montant return false
		if (montantEnchere < (article.getPrixInitial())) { // TODO : prix intial si pas d'enchère sinon prix de la plus
															// grande enchère
			return false;
		}
		// if solde utilisateur inferieur a enchere return false
		if ((utilisateur.getCredit()) < montantEnchere) {
			return false;
		}
		// if dateDebut < enchere < dateFin return false

		return true;

	}

}
