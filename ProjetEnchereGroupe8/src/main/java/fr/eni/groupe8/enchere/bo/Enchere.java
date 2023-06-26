package fr.eni.groupe8.enchere.bo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Enchere {

	private LocalDateTime dateEnchere;
	private BigDecimal montantEnchere;
	private Utilisateur encherisseur;
	private Article noArticle;
	private int noEnchere;

	
	
	public Enchere() {
		
	}
	
	
	
	public Enchere(LocalDateTime dateEnchere, BigDecimal montantEnchere, Utilisateur encherisseur, Article noArticle,
			int noEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.encherisseur=encherisseur;
		this.noArticle = noArticle;
		this.noEnchere = noEnchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public BigDecimal getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(BigDecimal montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}


	public Article getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Article noArticle) {
		this.noArticle = noArticle;
	}



	public Utilisateur getEncherisseur() {
		return encherisseur;
	}



	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}



	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", encherisseur="
				+ encherisseur + ", noArticle=" + noArticle + ", noEnchere=" + noEnchere + "]";
	}



}
