package fr.eni.groupe8.enchere.dal;

import java.util.List;

import fr.eni.groupe8.enchere.bo.Utilisateur;

public interface UtilisateurDAO {

	
	List<Utilisateur>findAllUtilisateur();

	Utilisateur readUtilisateur(Integer noUtilisateur);
	
	void save(Utilisateur utilisateur);
}
