package fr.eni.groupe8.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import fr.eni.groupe8.enchere.bll.UtilisateurServiceImpl;
import fr.eni.groupe8.enchere.bo.Utilisateur;

//@Component
public class StringToUtilisateurController implements Converter<String, Utilisateur> {

	private UtilisateurServiceImpl utilisateurServiceImpl;

	@Autowired
	public void setUtilisateurService(UtilisateurServiceImpl utilisateurServiceImpl) {
		this.utilisateurServiceImpl = utilisateurServiceImpl;
	}

	@Override
	public Utilisateur convert(String noUtilisateur) {
		Integer theid = Integer.parseInt(noUtilisateur);
		return utilisateurServiceImpl.findUtilisateurById(theid);
	}

}
