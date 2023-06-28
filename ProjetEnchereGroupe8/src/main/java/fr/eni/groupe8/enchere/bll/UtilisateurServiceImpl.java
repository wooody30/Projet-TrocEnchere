package fr.eni.groupe8.enchere.bll;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Utilisateur;
import fr.eni.groupe8.enchere.dal.UtilisateurDAO;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}
	
	@Override
	public void enregistrerUtilisateurs(Utilisateur utilisateur) {
		
		utilisateurDAO.save(utilisateur);
		
		
	}
	
}
