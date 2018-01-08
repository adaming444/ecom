package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Admin;
import fr.adaming.model.Client;

@Local
public interface IClientsDao {

	public List<Client> getAllClients();
	
	public Client addClient(Client c);
	
	public Client updateClient(Client c);
	
	public int deleteClient(Long id);

	public Client getClientById(Long id);
}
