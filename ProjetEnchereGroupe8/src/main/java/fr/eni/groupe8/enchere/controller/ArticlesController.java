package fr.eni.groupe8.enchere.controller;

import java.math.BigDecimal;
import java.security.Principal;

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
import fr.eni.groupe8.enchere.bll.contexte.ContexteService;
import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Enchere;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
public class ArticlesController { // Contrôleur pour les fonctionnalités liées aux articles (vente, enchère//
									// détails, etc.) {

	private ArticlesService service;
	private ContexteService contexteService;
	private UtilisateurService utilisateurService;
	private ArticlesService articleService;
	private EncheresService encheres;

	@Autowired
	public ArticlesController(ArticlesService service, UtilisateurService utilisateurService,
			ArticlesService articleService, EncheresService encheresService, ContexteService contexteService) {

		this.service = service;
		this.utilisateurService = utilisateurService;
		this.articleService = articleService;
		this.contexteService = contexteService;

		// this.encheres = enchere;

	}

	@GetMapping({ "/NouvelleVente" })
	public String afficherNouvelleVente(@ModelAttribute Article article, Principal principal, Model model) {

		/*
		 * String email = principal.getName(); Utilisateur aCharger =
		 * contexteService.findUtilisateurByEmail(email); model.addAttribute(aCharger);
		 * ya quelque chose a creuser!
		 */

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
	public String detailArticle(Integer noArticle, Model model,Integer prixVente) {
		
		Article article = articleService.articleById(noArticle);
		//Article prixV = articleService.articleByPrixVente(prixVente);
		model.addAttribute("article", article);
		model.addAttribute("montantEnchere", article.getPrixInitial());
		System.out.println(article);
		//System.out.println(prixV);
		return "detailArticle";
	}

	@PostMapping("/encherir")
	public String encherir(@ModelAttribute Enchere enchere, Integer noArticle, Model model, Principal principal) {

		Article article = articleService.articleById(noArticle);
		String email = principal.getName();
		Utilisateur utilisateur = contexteService.findUtilisateurByEmail(email);
		System.out.println("encherir utilisateur" + utilisateur);

		if (encheres.enchereValideSi(enchere.getMontantEnchere(), article, utilisateur)) {
			System.out.println("enchere valide si");
			// Si il n'y a déjà une enchère
			if (article.getPrixVente() == null) {

				// Enchere enchere= new Enchere();

				enchere.setMontantEnchere(enchere.getMontantEnchere());
				enchere.setEncherisseur(utilisateur);
				encheres.SaveNewEnchere(enchere);
				System.out.println("if article null");
			}

			/*else
				article.setPrixVente(enchere.getMontantEnchere());
		*/}

		return "redirect:/Acceuil";

	}
}

/*
 * @PostMapping("/encherir") public String
 * encherir(@RequestParam("montantEnchere") int montantEnchere, Integer
 * noArticle,
 * 
 * Model model, Enchere enchere, Principal principal) {
 * 
 * Article article = articleService.articleById(noArticle); String email =
 * principal.getName(); Utilisateur utilisateur =
 * contexteService.findUtilisateurByEmail(email);
 * 
 * if (encheres.enchereValideSi(montantEnchere, article, utilisateur)) {
 * 
 * article.setPrixVente(montantEnchere);
 * 
 * enchere.setMontantEnchere(montantEnchere); enchere.setNoArticle(article);
 * enchere.setEncherisseur(utilisateur); encheres.SaveNewEnchere(enchere);
 * 
 * //model.addAttribute("article", article); // Pourquoi cette ligne?
 * //model.addAttribute("encheres", enchere); // Pourquoi cette ligne? } // TODO
 * :Retrouver l'enchère existante si elle existe //Débiter le nouvel
 * enchérisseur // Recréditer l'ancien // Insérer une enchère dans la table
 * enchère
 * 
 * return "redirect:/Acceuil";
 */