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
	private Utilisateur utilisateur;

	@Autowired

	public AcceuilController(ArticlesService service, CategorieService categorieService,
			UtilisateurService utilisateurService) {

		this.service = service;

		this.categorieService = categorieService;
		this.utilisateurService = utilisateurService;

	}

	@GetMapping({ "/", "/Acceuil" })
	public String afficherAcceuil(Model model) {
		model.addAttribute("articles", service.findAllArticles());
		System.out.println("mappingAcceuil");
		return "Acceuil";
	}

	@GetMapping({ "/NouvelleVente" })
	public String afficherNouvelleVente(@ModelAttribute Article article) {
		System.out.println("mappingnewvente");
		return "NouvelleVente";
	}

	@PostMapping("/ajouterVente")
	public String ajouterVente(@ModelAttribute Article article) {
		// cree un utilisateur en dur avec un article.setutilisateurs qui utiliserait le constructeur 
		// avec juste un numero utilisateur
		Utilisateur utilisateur = utilisateurService.findUtilisateurById(2); 
		article.setVendeur(utilisateur);
		System.out.println("utilisateur associer a l'article");
		
		service.ajouterArticle(article);
		System.out.println("mappingAjouterVente");
		return "redirect:/AcceuilConnexion";

		
	}


}
