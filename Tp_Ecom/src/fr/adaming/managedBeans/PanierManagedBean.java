package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.CommandeServiceImpl;
import fr.adaming.service.LigneCommandeServiceImpl;
import fr.adaming.service.ProduitServiceImpl;

@ManagedBean(name = "panMB")
@SessionScoped
public class PanierManagedBean implements Serializable {

	// attribut
	private Panier panier;
	private LigneCommande ligneCommande;
	private Produit produit;
	private Commande commande;

	private LigneCommandeServiceImpl ligneCommandeService;

	private ProduitServiceImpl produitService;

	private CommandeServiceImpl commandeService;

	private Admin admin;

	private HttpSession maSession;

	// Constructeur par defaut
	public PanierManagedBean() {
		this.panier = new Panier();
		this.ligneCommande = new LigneCommande();
		this.produit = new Produit();
		this.commande = new Commande();
	}

	// methode qui s'execute apres l'instanciation du ManagedBean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("adminSession");
	}

	// Getters et setter
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setLigneCommandeService(LigneCommandeServiceImpl ligneCommandeService) {
		this.ligneCommandeService = ligneCommandeService;
	}

	public void setProduitService(ProduitServiceImpl produitService) {
		this.produitService = produitService;
	}

	public void setCommandeService(CommandeServiceImpl commandeService) {
		this.commandeService = commandeService;
	}

	// Methodes metier
	public void addLigneCommande(){
		this.ligneCommande.setPrix(this.ligneCommande.getQuantite()*this.produit.getPrix());
		this.panier.getListeLigneCommande().add(this.ligneCommande);
		this.panier.setTotal(this.panier.getTotal()+this.ligneCommande.getPrix());
		this.ligneCommande=null;
	}
	
	
	
}
