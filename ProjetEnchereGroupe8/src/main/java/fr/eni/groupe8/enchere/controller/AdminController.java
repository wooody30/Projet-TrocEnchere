package fr.eni.groupe8.enchere.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.groupe8.enchere.bll.ArticlesService;
import fr.eni.groupe8.enchere.bll.CategorieService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bll.contexte.ContexteService;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
public class AdminController { // Contrôleur pour les fonctionnalités d'administration. {

	private ContexteService service;
	private UtilisateurService utilisateurService;

	@Autowired

	public AdminController(ContexteService service, UtilisateurService utilisateurService) {

		this.service = service;
		this.utilisateurService = utilisateurService;

	}

	@GetMapping("/profil")
	public String profil(Model model, Principal principal) {

		String email = principal.getName();
		Utilisateur utilisateur = service.findUtilisateurByEmail(email);
		model.addAttribute("utilisateur", utilisateur);
		System.out.println(utilisateur);
		return "Profil";
	}

}
