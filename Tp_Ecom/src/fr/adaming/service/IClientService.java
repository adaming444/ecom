package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientService {

	public List<Client> getAllClients();

	public Client addClient(Client c);

	public Client updateClient(Client c);

	public int deleteClient(int id);

	public Client getClientById(int id);
}