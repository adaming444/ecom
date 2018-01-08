package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

public class CommandeManagedBean implements Serializable {

	@EJB
	private ICommandeService commandeService;

	private Commande commande;

	private Admin admin;
	private List<Commande> listeCommandes;

	private HttpSession maSession;

	public CommandeManagedBean() {
		this.commande = new Commande();
	}

	// methode qui s'execute après l'instanciation du managed bean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("AdminSession");
		this.listeCommandes = commandeService.getAllCommandes();

	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande Commande) {
		this.commande = Commande;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin Admin) {
		this.admin = Admin;
	}

	public List<Commande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	public void setCommandeService(ICommandeService CommandeService) {
		this.commandeService = CommandeService;
	}

	public String addCommande() {
		this.commande = commandeService.addCommande(this.commande);
		if (this.commande.getIdCommande() != 0) {
			// reucp de la nouvelle liste de la bd
			this.listeCommandes = commandeService.getAllCommandes();
			// mettre ajour la liste dans la session
			maSession.setAttribute("commandesList", this.listeCommandes);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "#";
		}
	}

	public String updateCommande() {
		this.commande = commandeService.updateCommande(this.commande);
		if (this.commande.getIdCommande() != 0) {
			// reucp de la nouvelle liste de la bd
			this.listeCommandes = commandeService.getAllCommandes();
			// mettre ajour la liste dans la session
			maSession.setAttribute("commandesList", this.listeCommandes);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la modification."));
			return "#";
		}

	}

	public String deleteCommande() {
		if (commandeService.deleteCommande(this.commande.getIdCommande()) == 1) {
			this.listeCommandes = commandeService.getAllCommandes();
			maSession.setAttribute("commandesList", this.listeCommandes);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la suppression."));
			return "#";
		}
	}

	public String getCommandeById() {
		Commande cOut = commandeService.getCommandeById(this.commande.getIdCommande());
		if (cOut != null) {
			this.commande = cOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "#";
	}
	
	public String getAllCommandes() {
		this.listeCommandes = commandeService.getAllCommandes();
		if (listeCommandes.size() > 0) {
			this.listeCommandes = commandeService.getAllCommandes();
			maSession.setAttribute("commandesList", this.listeCommandes);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "#";
	}

}
