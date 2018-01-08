package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import fr.adaming.model.Admin;
import fr.adaming.service.IAdminService;

@ManagedBean(name = "aMb")
@RequestScoped
public class AgentManagedBean implements Serializable {

	@EJB
	private IAdminService adminService;
	private Admin admin;

	public AgentManagedBean() {
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
			return "success";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'admin n'existe pas"));
		}
		return "failure";
	}

}
