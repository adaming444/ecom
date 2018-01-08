package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	@EJB
	private ICategorieService categorieService;
	
	private Categorie categorie;
	
	private List<Categorie> listeCategorie;
	
	private Admin admin;
	
	private HttpSession maSession;

	
	// Constructeur par defaut
	public CategorieManagedBean() {
		this.categorie = new Categorie();
		this.listeCategorie = new ArrayList<Categorie>();
	}

	// methode qui s'execute apres l'instanciation du ManagedBean
		@PostConstruct
		public void init() {
			this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			this.admin = (Admin) maSession.getAttribute("agentSession");
		}
	
	// Getters et setters
	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}


	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}
	
	// methodes metier
	public String addCategorie(){
		this.categorie = categorieService.addCategorie(this.categorie);
		
		if(this.categorie.getIdCategorie() != 0){
			this.listeCategorie = categorieService.getAllCategorie();
			
			maSession.setAttribute("categorieListe", this.listeCategorie);
			
			return "accueilAdmin";
		} else {
			return "ajout_categorie";
		}
	}
	
	
}
