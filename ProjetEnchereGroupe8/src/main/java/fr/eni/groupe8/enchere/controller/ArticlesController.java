package fr.eni.groupe8.enchere.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.groupe8.enchere.bll.ArticlesService;
import fr.eni.groupe8.enchere.bll.EncheresService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Enchere;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
public class ArticlesController { // Contrôleur pour les fonctionnalités liées aux articles (vente, enchère//
									// détails, etc.) {

	private ArticlesService service;
	private UtilisateurService utilisateurService;
	private ArticlesService articleService;
	private EncheresService encheres;

	@Autowired
	public ArticlesController(ArticlesService service,UtilisateurService utilisateurService, 
			ArticlesService articleService, EncheresService encheresService,
			EncheresService enchere) {

		this.service = service;
		this.utilisateurService = utilisateurService;
		this.articleService = articleService;
		this.encheres = enchere;

	}

	@GetMapping({ "/NouvelleVente" })
	public String afficherNouvelleVente(@ModelAttribute Article article) {
		System.out.println("mappingnewvente");
		return "NouvelleVente";
	}

	@PostMapping("/ajouterVente")
	public String ajouterVente(@ModelAttribute Article article) {
		// cree un utilisateur en dur avec un article.setutilisateurs qui utiliserait le
		// constructeur
		// avec juste un numero utilisateur

		Utilisateur utilisateur = utilisateurService.findUtilisateurById(2);

		article.setVendeur(utilisateur);
		System.out.println("utilisateur associer a l'article");

		service.ajouterArticle(article);
		System.out.println("mappingAjouterVente");
		return "redirect:/AcceuilConnexion";

	}

	@GetMapping("/detailarticle")
	public String detailArticle(Integer noArticle, Model model) {

		Article article = articleService.articleById(noArticle);
		model.addAttribute("article", article);
		return "detailArticle";
	}

	@PostMapping("/encherir")
	public String encherir(@RequestParam("propositionAcheteur") BigDecimal montantEnchere, Integer noArticle,
			Model model, Enchere enchere, Utilisateur utilisateur) {
		Article article = articleService.articleById(noArticle);
		Utilisateur acheteur = utilisateurService.findUtilisateurById(2);
		
		
		//if (encheresService.enchereValideSi(null, article, acheteur)) { // null doit représenter la proposition de l'acheteur// enchere.setMontantEnchere(propositionAcheteur);
		//encheresService.enchereValideSi(enchere.getMontantEnchere(), article, acheteur);

		if (encheres.enchereValideSi(montantEnchere, article, acheteur)) {
			encheres.SaveNewEnchere(enchere);
			
			model.addAttribute("article", article); // Pourquoi cette ligne?
			model.addAttribute("encheres", enchere); // Pourquoi cette ligne?
		}

		// A poursuivre // articleService.encherir(article, propositionAcheteur,
		// acheteur);
		return "redirect:/Acceuil";

	}

}
