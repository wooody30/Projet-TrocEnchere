package fr.eni.groupe8.enchere.bll;


import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Utilisateur;
import fr.eni.groupe8.enchere.dal.UtilisateurDAO;


@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO=utilisateurDAO;
	}

	@Override
	public List<Utilisateur> getListUtilisateur() {
		
		return utilisateurDAO.findAllUtilisateur();
	}

	
	
	
	
	
	@Override
	public Utilisateur findUtilisateurById(Integer noUtilisateur) {
		
		return utilisateurDAO.readUtilisateur(noUtilisateur);
	}
	
	
	@Override
	public void enregistrerUtilisateurs(Utilisateur utilisateur) {
		
		utilisateurDAO.save(utilisateur);
		
		
	}




	

	
}
