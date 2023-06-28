package fr.eni.groupe8.enchere.bll;

import java.util.List;

import fr.eni.groupe8.enchere.bo.Utilisateur;

public interface UtilisateurService { //Interface de service pour la gestion des utilisateurs.

	public List<Utilisateur> getListUtilisateur();
	
	public Utilisateur findUtilisateurById (Integer noUtilisateur);
	
	void enregistrerUtilisateurs(Utilisateur utilisateur);
	
	
}
