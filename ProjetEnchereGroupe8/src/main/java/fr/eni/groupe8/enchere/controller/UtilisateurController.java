package fr.eni.groupe8.enchere.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import fr.eni.groupe8.enchere.bll.ArticlesService;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bll.contexte.ContexteService;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
public class UtilisateurController { // Contrôleur pour les fonctionnalités liées aux utilisateurs (inscription,
										// connexion, profil, etc.)
	private UtilisateurService utilisateurService;
	private ContexteService service;
	private ArticlesService articleService;

	@Autowired
	public UtilisateurController(ArticlesService articleService, UtilisateurService utilisateurService,
			ContexteService service) {
		this.utilisateurService = utilisateurService;
		this.service = service;
		this.articleService = articleService;
	}

	@GetMapping("/CreerCompte")
	public String afficherCreeCompte(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		System.out.println("mapping CreerCompte");
		return "CreerCompte";
	}

	@PostMapping("/CreerCompte")
	public String newUtilisateurs(Utilisateur nouvelleDonneesUtilisateur, Principal principal) {

		if (principal == null) {

		} else {
			String email = principal.getName();
			Utilisateur ancienneDonneesUtilisateur = service.findUtilisateurByEmail(email);
			nouvelleDonneesUtilisateur.setNoUtilisateur(ancienneDonneesUtilisateur.getNoUtilisateur());
		}
		utilisateurService.enregistrerUtilisateurs(nouvelleDonneesUtilisateur);

		return "redirect:/AcceuilConnexion";
	}
/***************************************************************************************************/
	@GetMapping("/profil")
	public String profil(Model model, Principal principal) {

		String email = principal.getName(); //extraction de l'email de l'utilisateur 
		Utilisateur utilisateur = service.findUtilisateurByEmail(email); // injection des info dans utilisateur
		model.addAttribute("utilisateur", utilisateur); //injection des info dans le model
		System.out.println(utilisateur); 
		return "Profil";
	}
/****************************************************************************************************************/
	@GetMapping("/ModifierProfil")
	public String afficherModifierProfil(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		System.out.println("mapping CreerCompte");
		return "ModifierProfil";
	}

	@GetMapping("/annuler")
	public String annuler() {
		return "redirect:/Acceuil.html";
	}

	@GetMapping("/AcceuilConnexion")
	public String afficherAcceuilConnecter(Model model) {
		model.addAttribute("articles", articleService.findAllArticles());
		System.out.println("mappingAcceuilConnexion");
		return "AcceuilConnexion";
	}

}
