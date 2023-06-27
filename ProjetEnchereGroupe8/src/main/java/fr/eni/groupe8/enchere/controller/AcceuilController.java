package fr.eni.groupe8.enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.groupe8.enchere.bll.ArticlesService;


@Controller
public class AcceuilController { //Contrôleur pour la page d'accueil et la recherche d'articles.
	private ArticlesService service;
	
	@Autowired
	public AcceuilController(ArticlesService service) {
		this.service = service; 
	}

	@GetMapping({"/", "acceuil"})
	public String afficherAcceuil() {
		return "Acceuil";
	}
	
	
	}

