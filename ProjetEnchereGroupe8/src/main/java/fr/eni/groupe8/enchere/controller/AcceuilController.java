package fr.eni.groupe8.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
=======
>>>>>>> refs/remotes/origin/main
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.groupe8.enchere.bll.ArticlesService;
<<<<<<< HEAD
import fr.eni.groupe8.enchere.bll.CategorieService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Utilisateur;
=======
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bo.Utilisateur;

>>>>>>> refs/remotes/origin/main


@Controller
public class AcceuilController { //Contrôleur pour la page d'accueil et la recherche d'articles.
	private ArticlesService service;
<<<<<<< HEAD
	private CategorieService categorieService;
	private UtilisateurService utilisateurService;
=======
	private UtilisateurService utilisateurService; 
	
>>>>>>> refs/remotes/origin/main
	
	
	@Autowired
<<<<<<< HEAD
	public AcceuilController(ArticlesService service, CategorieService categorieService, UtilisateurService utilisateurService) {
=======
	public AcceuilController(ArticlesService service,UtilisateurService utilisateurService) {
>>>>>>> refs/remotes/origin/main
		this.service = service; 
<<<<<<< HEAD
		this.categorieService=categorieService;
		this.utilisateurService=utilisateurService;
=======
		this.utilisateurService = utilisateurService; 
>>>>>>> refs/remotes/origin/main
	}

	@GetMapping({"/", "Acceuil"})
	public String afficherAcceuil(Model model) {
		model.addAttribute("articles", service.findAllArticles());
		return "Acceuil";
	}
	
	@GetMapping({"/NouvelleVente"})
	public String afficherNouvelleVente( @ModelAttribute Article article) {
		article.addAttribute("categorie", categorieService.getListCategorie());
		return "NouvelleVente";
	}
	
	
	
	
	@GetMapping("/Connexion")
	public String afficherConnexion() {
		return "Connexion";
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
		
		return "redirect:/Acceuil";
	}
	
}

