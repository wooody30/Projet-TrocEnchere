package fr.eni.groupe8.enchere.bll;

<<<<<<< HEAD
import java.util.List;

import fr.eni.groupe8.enchere.bo.Utilisateur;

public interface UtilisateurService { //Interface de service pour la gestion des utilisateurs.
=======
import fr.eni.groupe8.enchere.bo.Utilisateur;

public interface UtilisateurService {

	void enregistrerUtilisateurs(Utilisateur utilisateur); //Interface de service pour la gestion des utilisateurs.


>>>>>>> refs/remotes/origin/main

	public List<Utilisateur> getListUtilisateur();
	
	public Utilisateur findUtilisateurById (Integer noUtilisateur);
	
	void enregistrerUtilisateurs(Utilisateur utilisateur);
	
	
}
