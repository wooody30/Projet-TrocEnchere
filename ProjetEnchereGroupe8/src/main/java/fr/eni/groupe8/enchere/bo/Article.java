package fr.eni.groupe8.enchere.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Article {

	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private LocalTime heureDebutEnchere;
	private LocalTime heureFinEnchere;
	private int prixInitial;
	private Integer prixVente;
	private boolean etatVente;
	private Utilisateur vendeur;
	private Categorie categorie;
	private int meilleurPrix;
	private Utilisateur meilleurEncherisseur;

	public Article() { //Constructeur vide

	}

	public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, LocalTime heureDebutEnchere, LocalTime heureFinEnchere, int prixInitial,
			Integer prixVente, Boolean etatVente, Utilisateur vendeur, Categorie categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.heureDebutEnchere = heureDebutEnchere;
		this.heureFinEnchere = heureFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}
	
	public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, Boolean etatVente, Utilisateur vendeur,
			Categorie categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}

	public Integer getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public Integer getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public Boolean getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public LocalTime getHeureDebutEnchere() {
		return heureDebutEnchere;
	}

	public void setHeureDebutEnchere(LocalTime heureDebutEnchere) {
		this.heureDebutEnchere = heureDebutEnchere;
	}

	public LocalTime getHeureFinEnchere() {
		return heureFinEnchere;
	}

	public void setHeureFinEnchere(LocalTime heureFinEnchere) {
		this.heureFinEnchere = heureFinEnchere;
	}

	public int getMeilleurPrix() {
		return meilleurPrix;
	}

	public void setMeilleurPrix(int meilleurPrix) {
		this.meilleurPrix = meilleurPrix;
	}

	public Utilisateur getMeilleurEncherisseur() {
		return meilleurEncherisseur;
	}

	public void setMeilleurEncherisseur(Utilisateur meilleurEncherisseur) {
		this.meilleurEncherisseur = meilleurEncherisseur;
	}

	@Override
	public String toString() {
		return "Article [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres
				+ ", heureDebutEnchere=" + heureDebutEnchere + ", heureFinEnchere=" + heureFinEnchere + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", vendeur=" + vendeur
				+ ", categorie=" + categorie + "]";
	}


	public void addAttribute(String string, List<Categorie> listCategorie) {
		// TODO Auto-generated method stub

	}

}
