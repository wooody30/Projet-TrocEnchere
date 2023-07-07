package fr.eni.groupe8.enchere.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import fr.eni.groupe8.enchere.bll.ArticlesService;
import fr.eni.groupe8.enchere.bll.EncheresService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bll.contexte.ContexteService;
import fr.eni.groupe8.enchere.bo.Article;
import fr.eni.groupe8.enchere.bo.Enchere;
import fr.eni.groupe8.enchere.bo.Retrait;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
public class ArticlesController { // Contrôleur pour les fonctionnalités liées aux articles (vente, enchère//
									// détails, etc.) {

	private ArticlesService service;
	private ContexteService contexteService;
	private ArticlesService articleService;
	private EncheresService encheres;

	@Autowired
	public ArticlesController(ArticlesService service, UtilisateurService utilisateurService,
			ArticlesService articleService, EncheresService encheresService, ContexteService contexteService) {

		this.service = service;
		this.articleService = articleService;
		this.contexteService = contexteService;
	}

	@GetMapping({ "/NouvelleVente" })
	public String afficherNouvelleVente(@ModelAttribute Article article, Principal principal, Model model) {
		Retrait retrait = new Retrait();
		String email = principal.getName();
		Utilisateur aCharger = contexteService.findUtilisateurByEmail(email);
		retrait.setRue(aCharger.getRue());
		retrait.setCode_postal(aCharger.getCodePostal());
		retrait.setVille(aCharger.getVille());
		model.addAttribute("user", aCharger);
		model.addAttribute("retrait", retrait);
		System.out.println("mappingnewvente");
		return "NouvelleVente";
	}

	@PostMapping("/ajouterVente")
	public String ajouterVente(@ModelAttribute Article article, @ModelAttribute Retrait retrait, Integer noUtilisateur,
			Principal principal) {

		String email = principal.getName();
		Utilisateur utilisateur = contexteService.findUtilisateurByEmail(email);
		System.out.println("encherir utilisateur" + utilisateur);

		article.setVendeur(utilisateur);
		System.out.println("utilisateur associer a l'article");

		service.ajouterArticle(article); // la meme rentrait
		// service.ajouterRetrait(retrait);

		retrait.setNoArticle(article.getNoArticle());
		System.out.println("mappingAjouterVente");
		return "redirect:/AcceuilConnexion";

	}

	@GetMapping("/detailarticle")
	public String detailArticle(Integer noArticle, Model model, Integer prixVente) {

		Article article = articleService.articleById(noArticle);
		model.addAttribute("article", article);
		model.addAttribute("montantEnchere", article.getPrixInitial());
		System.out.println(article);
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

			/*
			 * else article.setPrixVente(enchere.getMontantEnchere());
			 */}

		return "redirect:/Acceuil";

	}
}
