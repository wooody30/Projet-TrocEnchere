package fr.eni.groupe8.enchere.dal;

import java.util.List;

import fr.eni.groupe8.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	
	List<Utilisateur>findAllUtilisateur();

<<<<<<< HEAD
	Utilisateur readUtilisateur(Integer noUtilisateur);
=======
	Utilisateur readUtilisateur(Integer no_utilisateur);

	void save(Utilisateur utilisateur);
>>>>>>> refs/remotes/origin/main
	
	void save(Utilisateur utilisateur);


}
