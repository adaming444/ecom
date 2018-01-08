package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientsDao {

	@PersistenceContext(unitName = "PU")
	EntityManager em; // pour l'injection d'un EM

	// setters pour l'injection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Client> getAllClients() {
		// construire la requete JPQL
		String req = "SELECT c FROM Client c";

		// créer le query
		Query query = em.createQuery(req);

		// envoyer la requete et recup du resultat
		return query.getResultList();
	}

	public Client addClient(Client c) {

		em.persist(c);

		return c;
	}

	public Client updateClient(Client c) {
		em.merge(c);

		return c;
	}

	public int deleteClient(Long id) {
		Client c = (Client) em.find(Client.class, id);
		if (c != null) {
			em.remove(c);
			return 1;
		}
		return 0;
	}

	public Client getClientById(Long id) {
		Client c = (Client) em.find(Client.class, id);
		if (c != null) {
			return c;
		}
		return null;
	}

}
