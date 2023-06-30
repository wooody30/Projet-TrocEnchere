package fr.eni.groupe8.enchere.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bll.contexte.ContexteService;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
//Injection de la liste des attributs en session
@SessionAttributes({ "utilisateurEnSession" })
public class ConnexionController {

	private ContexteService service;
	private UtilisateurService utilisateurService;

	public ConnexionController(ContexteService service, UtilisateurService utilisateurService) {
		this.service = service;
		this.utilisateurService = utilisateurService;
	}

	// @GetMapping("/login")
	// String login() {
	// return "login";
	// }

	@GetMapping("/login")
	public String afficherConnexion() {
		return "loginForm";
	}

	@PostMapping("/session")
	String chargerMembreEnSession(@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession,
			Principal principal) {
		String email = principal.getName();
		Utilisateur aCharger = service.findUtilisateurByEmail(email);
		if (aCharger != null) {
			utilisateurEnSession.setEmail(aCharger.getEmail());
			utilisateurEnSession.setMotDePasse(aCharger.getMotDePasse());
			utilisateurEnSession.setPseudo(aCharger.getPseudo());

		} else {

			utilisateurEnSession.setEmail(null);
			utilisateurEnSession.setMotDePasse(null);

		}
		System.out.println(utilisateurEnSession);

		return "redirect:/AcceuilConnexion";
	}

	
	// Cette méthode met par défaut un nouveau membre en session
	@ModelAttribute("utilisateurEnSession")
	public Utilisateur membreEnSession() {
		System.out.println("Add Attribut Session");
		return new Utilisateur();
	}

}
