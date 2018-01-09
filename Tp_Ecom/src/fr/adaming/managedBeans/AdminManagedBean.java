package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.service.CategorieServiceImpl;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "aMb")
@RequestScoped
public class AdminManagedBean implements Serializable {

	@EJB
	private IAdminService adminService;
	
	@EJB
	private ICategorieService categorieService;
	
	private Admin admin;

	public AdminManagedBean() {
		this.admin = new Admin();

	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String seConnecter() {

		try {
			Admin aOut = adminService.isExist(this.admin);

			// Ajouter l'agent dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", aOut);
			
			List<Categorie> listeCategorie = categorieService.getAllCategorie();

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieListe", listeCategorie);
			
			return "success";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'admin n'existe pas"));
		}
		return "failure";
	}

}
