package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import fr.adaming.model.Admin;
import fr.adaming.model.Produit;
import fr.adaming.service.CategorieServiceImpl;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;
import fr.adaming.service.ProduitServiceImpl;

@ManagedBean(name = "pMb")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	@EJB
	private IProduitService pService;

	private ICategorieService cService = new CategorieServiceImpl();
	private Produit produit;
	private Admin admin;
	private List<Produit> listeProduits;
	private HttpSession maSession;
	private UploadedFile file;

	private String image;

	public ProduitManagedBean() {

		// Instancier un produit
		this.produit = new Produit();
		this.listeProduits = new ArrayList<Produit>();
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
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// Les methodes metiers

	public String addProduit() {
		// //Appel de la methode service

		this.produit = pService.addProduit(this.produit);
		if (this.produit.getIdProduit() != 0) {
			// Recuperer la nouvelle liste de la bd
			List<Produit> listeP = pService.getAllProduit();
			// Mettre a jour la liste des voitures dans la session
			maSession.setAttribute("produitList", listeP);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "ajout_produit";
		}
	}
	
	public void upload(FileUploadEvent event){
		UploadedFile ufile = event.getFile();
		byte[] contents = ufile.getContents();
		this.produit.setPhoto(contents);
		this.image = "data:image/png;base64,"+Base64.encodeBase64String(contents);
	}
	
	public String updateProduit() {
		Produit pModif = pService.updateProduit(this.produit);
		if (pModif != null) {
			// Recuperer la nouvelle liste de la bd
			List<Produit> listeP = pService.getAllProduit();
			// Mettre a jour la liste des voitures dans la session
			maSession.setAttribute("produitList", listeP);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la modification."));
			return "modif_produit";
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
		return "recherche_produit";
	}
	
	public String getProduitbyName(){
		Produit pFind = pService.getProduitbyName(this.produit.getDesignation());
		if (pFind != null) {
			this.produit = pFind;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "recherche_produit";
	}

	public String deleteProduit() {
		pService.deleteProduit(this.produit.getIdProduit());
		// Recuperer la nouvelle liste de la bd
		List<Produit> listeP = pService.getAllProduit();
		// Mettre a jour la liste des voitures dans la session
		maSession.setAttribute("produitList", listeP);
		return "accueilAdmin";
	}

	public String getAllProduits() {
		this.listeProduits = pService.getAllProduit();
		if (listeProduits.size() > 0) {
			this.listeProduits = pService.getAllProduit();
			maSession.setAttribute("produitsList", this.listeProduits);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "affiche_produits";
	}
}
