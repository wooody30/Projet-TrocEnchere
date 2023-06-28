package fr.eni.groupe8.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.groupe8.enchere.bll.ArticlesService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
public class UtilisateurController{ //Contrôleur pour les fonctionnalités liées aux utilisateurs (inscription, connexion, profil, etc.) 
	private UtilisateurService utilisateurService; 
	private ArticlesService service;
	
	@Autowired
	public UtilisateurController(ArticlesService service,UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService; 
		this.service = service; 
	}
	
	@GetMapping("/CreerCompte")
	public String afficherCreeCompte(Model model) {
	    model.addAttribute("utilisateur", new Utilisateur());
	    return "CreerCompte";
	}

	
	@PostMapping("/CreerCompte")
	public String newUtilisateurs(Utilisateur utilisateur) {
		utilisateurService.enregistrerUtilisateurs(utilisateur);
		
		System.out.println("enregistrement de : " + utilisateur);
		
		return "redirect:/AcceuilConnexion";
	}
	
	@GetMapping("/AcceuilConnexion")
	public String afficherAcceuilConnecter(Model model) {
		model.addAttribute("articles", service.findAllArticles());
		return "AcceuilConnexion";
	}

}
