package fr.eni.groupe8.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import fr.eni.groupe8.enchere.bll.ArticlesService;
import fr.eni.groupe8.enchere.bll.CategorieService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Utilisateur;
import jakarta.validation.Valid;

@Controller
public class AcceuilController { // Contrôleur pour la page d'accueil et la recherche d'articles.
	private ArticlesService service;

	private CategorieService categorieService;
	private UtilisateurService utilisateurService;

	@Autowired

	public AcceuilController(ArticlesService service, CategorieService categorieService,
			UtilisateurService utilisateurService) {

		this.service = service;

		this.categorieService = categorieService;
		this.utilisateurService = utilisateurService;

	}

	@GetMapping({ "/", "Acceuil" })
	public String afficherAcceuil(Model model) {
		model.addAttribute("articles", service.findAllArticles());
		return "Acceuil";
	}

	@GetMapping({ "/NouvelleVente" })
	public String afficherNouvelleVente(@ModelAttribute Article article) {
		
		return "NouvelleVente";
	}

	
	@PostMapping("/ajouterVente")
	public void ajouterVente(@ModelAttribute Article article) {
	
		service.ajouterArticle(article);
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
