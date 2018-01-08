package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;
import fr.adaming.service.ProduitServiceImpl;

@ManagedBean(name = "pMb")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	@EJB
	private IProduitService pService;
	private Produit produit;
	private Admin admin;
	private List<Produit> listeProduits;
	private HttpSession maSession;

	public ProduitManagedBean() {

		// Instancier un produit
		this.produit = new Produit();
	}

	// Methode qui s'exectute apres l'instanciation du ManagedBean
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("adminSession");
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public void setpService(IProduitService pService) {
		this.pService = pService;
	}

	// Les methodes metiers

	public String addProduit() {
		// //Appel de la methode service
		this.produit = pService.addProduit(this.produit);
		if (this.produit.getIdProduit() != 0) {
			// Recuperer la nouvelle liste de la bd
			List<Produit> listeP = pService.getAllProduit();
			// Mettre a jour la liste des voitures dans la session
			maSession.setAttribute("produitList", this.listeProduits);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "ajoutP";
		}
	}

	public String updateProduit() {
		Produit pModif = pService.updateProduit(this.produit);
		if (pModif != null) {
			// Recuperer la nouvelle liste de la bd
			List<Produit> listeP = pService.getAllProduit();
			// Mettre a jour la liste des voitures dans la session
			maSession.setAttribute("produitList", this.listeProduits);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la modification."));
			return "modifierP";
		}
	}

	public String getProduitbyId() {
		Produit pFind = pService.getProduitbyId(this.produit.getIdProduit());
		if (pFind != null) {
			this.produit = pFind;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "rechercherP";
	}

	public String deleteProduit() {
		pService.deleteProduit(this.produit.getIdProduit());
		// Recuperer la nouvelle liste de la bd
		List<Produit> listeP = pService.getAllProduit();
		// Mettre a jour la liste des voitures dans la session
		maSession.setAttribute("produitList", this.listeProduits);
		return "accueilAdmin";
	}
}
