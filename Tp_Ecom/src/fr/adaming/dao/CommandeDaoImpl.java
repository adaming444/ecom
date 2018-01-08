package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao {

	@PersistenceContext(unitName = "PU")
	EntityManager em; // pour l'injection d'un EM

	// setters pour l'injection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<Commande> getAllCommandes() {
		// construire la requete JPQL
		String req = "SELECT c FROM Commande c";

		// créer le query
		Query query = em.createQuery(req);

		// envoyer la requete et recup du resultat
		return query.getResultList();
	}

	public Commande addCommande(Commande c) {

		em.persist(c);

		return c;
	}

	public Commande updateCommande(Commande c) {
		em.merge(c);

		return c;
	}

	public int deleteCommande(int id) {
		Commande c = (Commande) em.find(Commande.class, id);
		if (c != null) {
			em.remove(c);
			return 1;
		}
		return 0;
	}

	public Commande getCommandeById(int id) {
		Commande c = (Commande) em.find(Commande.class, id);
		if (c != null) {
			return c;
		}
		return null;
	}
	

}
