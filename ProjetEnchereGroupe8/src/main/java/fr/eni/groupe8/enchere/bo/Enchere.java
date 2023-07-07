package fr.eni.groupe8.enchere.bo;

import java.time.LocalDateTime;

public class Enchere {

	private LocalDateTime dateEnchere;
	private Integer montantEnchere;
	private Utilisateur encherisseur;
	private Article noArticle;
	private Integer noEnchere;

	public Enchere() {

	}

	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, Utilisateur encherisseur, Article noArticle,
			Integer noEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.encherisseur = encherisseur;
		this.noArticle = noArticle;
		this.noEnchere = noEnchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
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

	public Integer getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", encherisseur="
				+ encherisseur + ", noArticle=" + noArticle + ", noEnchere=" + noEnchere + "]";
	}

}
