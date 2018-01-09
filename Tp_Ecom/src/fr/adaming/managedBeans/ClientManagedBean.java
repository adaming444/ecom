package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean {
	
	@EJB
	private IClientService clientService;

	private Client client;

	private Admin admin;
	private List<Client> listeClients;

	private HttpSession maSession;

	public ClientManagedBean() {
		this.client = new Client();
		this.listeClients = new ArrayList<Client>();

	}

	// methode qui s'execute après l'instanciation du managed bean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Admin) maSession.getAttribute("AdminSession");
		this.listeClients = clientService.getAllClients();

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client Client) {
		this.client = Client;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin Admin) {
		this.admin = Admin;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	public void setClientService(IClientService ClientService) {
		this.clientService = ClientService;
	}

	public String addClient() {
		this.client = clientService.addClient(this.client);
		if (this.client.getIdClient() != 0) {
			// reucp de la nouvelle liste de la bd
			this.listeClients = clientService.getAllClients();
			// mettre ajour la liste dans la session
			maSession.setAttribute("clientsList", this.listeClients);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de l'ajout."));
			return "ajout_client";
		}
	}

	public String updateClient() {
		this.client = clientService.updateClient(this.client);
		if (this.client.getIdClient() != 0) {
			// reucp de la nouvelle liste de la bd
			this.listeClients = clientService.getAllClients();
			// mettre ajour la liste dans la session
			maSession.setAttribute("clientsList", this.listeClients);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la modification."));
			return "#";
		}

	}

	public String deleteClient() {
		if (clientService.deleteClient(this.client.getIdClient()) == 1) {
			this.listeClients = clientService.getAllClients();
			maSession.setAttribute("clientsList", this.listeClients);
			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la suppression."));
			return "suppr.client";
		}
	}

	public String getClientById() {
		Client cOut = clientService.getClientById(this.client.getIdClient());
		if (cOut != null) {
			this.client = cOut;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue lors de la recherche."));
		}
		return "#";
	}
	
	public String getAllClients() {
		this.listeClients = clientService.getAllClients();
		if (listeClients.size() > 0) {
			this.listeClients = clientService.getAllClients();
			maSession.setAttribute("clientsList", this.listeClients);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue du chargement de la liste."));
		}
		return "#";
	}
}
