package fr.eni.groupe8.enchere.bll.contexte;

import fr.eni.groupe8.enchere.bo.Utilisateur;

	public interface ContexteService {
		Utilisateur findUtilisateurByEmail(String email);

	}

