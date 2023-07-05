package fr.eni.groupe8.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.groupe8.enchere.bll.ArticlesService;
import fr.eni.groupe8.enchere.bll.CategorieService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;

@Controller
public class AcceuilController { // Contrôleur pour la page d'accueil et la recherche d'articles.
	private ArticlesService service;

	@Autowired
	public AcceuilController(ArticlesService service, CategorieService categorieService,
			UtilisateurService utilisateurService, ArticlesService articleService) {

		this.service = service;

	}

	@GetMapping({ "/", "/Acceuil" })
	public String afficherAcceuil(Model model) {
		model.addAttribute("articles", service.findAllArticles());
		System.out.println("mappingAcceuil");
		return "Acceuil";
	}

}
