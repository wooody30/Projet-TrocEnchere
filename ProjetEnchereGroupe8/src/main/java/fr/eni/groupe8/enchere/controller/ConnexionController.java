package fr.eni.groupe8.enchere.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import fr.eni.groupe8.enchere.bll.UtilisateurService;
import fr.eni.groupe8.enchere.bll.contexte.ContexteService;
import fr.eni.groupe8.enchere.bo.Utilisateur;

@Controller
//Injection de la liste des attributs en session
@SessionAttributes({ "utilisateurEnSession" })
public class ConnexionController {

	private ContexteService service;

	public ConnexionController(ContexteService service, UtilisateurService utilisateurService) {
		this.service = service;
	}

	@GetMapping("/Connexion")

	public String afficherConnexion() {
		return "loginForm";

	}

	@PostMapping("/session")
	String chargerMembreEnSession(
			// @ModelAttribute("utilisateur") Utilisateur utilisateurEnSession,
			Principal principal) {

		System.out.println(principal);

		if (principal != null) {
			System.out.println(principal.getName());
			String email = principal.getName();
			Utilisateur aCharger = service.findUtilisateurByEmail(email);
			if (aCharger != null) {
				// utilisateurEnSession.setEmail(aCharger.getEmail());
				// utilisateurEnSession.setMotDePasse(aCharger.getMotDePasse());
				// utilisateurEnSession.setPseudo(aCharger.getPseudo());

			} else {

				// utilisateurEnSession.setEmail(null);
				// utilisateurEnSession.setMotDePasse(null);

			}
		}

		return "redirect:/AcceuilConnexion";
	}

}
