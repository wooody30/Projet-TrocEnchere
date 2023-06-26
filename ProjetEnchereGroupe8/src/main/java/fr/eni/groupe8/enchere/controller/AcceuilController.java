package fr.eni.groupe8.enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcceuilController { //Contrôleur pour la page d'accueil et la recherche d'articles. {

	@GetMapping("/")
	public static Acceuil() {
		return "acceuil"
	}
}
