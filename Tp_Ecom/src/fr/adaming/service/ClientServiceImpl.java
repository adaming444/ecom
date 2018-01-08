package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientsDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService {

	@EJB
	private IClientsDao clientDao;
	
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	public Client addClient(Client c) {
		return clientDao.addClient(c);
	}

	public Client updateClient(Client c) {
		return clientDao.updateClient(c);
	}

	public int deleteClient(int id) {
		return clientDao.deleteClient(id);
	}

	public Client getClientById(int id) {
		return clientDao.getClientById(id);
	}

}
