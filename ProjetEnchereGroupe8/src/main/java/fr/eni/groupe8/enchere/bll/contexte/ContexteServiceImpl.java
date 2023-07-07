package fr.eni.groupe8.enchere.bll.contexte;

import org.springframework.stereotype.Service;

import fr.eni.groupe8.enchere.bo.Utilisateur;
import fr.eni.groupe8.enchere.dal.UtilisateurDAO;

@Service
public class ContexteServiceImpl implements ContexteService {

	private UtilisateurDAO utilisateurDAO;

	public ContexteServiceImpl(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}

	public Utilisateur chargerUtilisateur(Integer noUtilisateur) {

		return utilisateurDAO.readUtilisateur(noUtilisateur);
	}

/**********************************************************************************************************************************/	
	@Override
	public Utilisateur findUtilisateurByEmail(String email) {

		return utilisateurDAO.readUtilisateur_email(email);
	}

}
/******************************************************************************************************************************/