package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Produit;
@Stateless
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName = "PU")
	EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Produit> getAllProduit() {
		// Construire la requete JPQL
		String req = "SELECT p from Produit as p";
		// Creer un query
		Query query = em.createQuery(req);
		// envoyer et recuperer la req
		List<Produit> listeP = query.getResultList();
		return listeP;
	}

	@Override
	public Produit addProduit(Produit p) {
		em.persist(p);
		return p;
	}

	@Override
	public Produit updateProduit(Produit p) {
		Produit pOut = em.merge(p);
		return pOut;
	}

	@Override
	public int deleteProduit(int id) {
		// Construire la requete JPQL
		String req = "DELETE FROM Produit p WHERE p.idProduit=:pId";
		// Creer un query
		Query query = em.createQuery(req);
		// Assigner les paramètres
		query.setParameter("pId", id);
		// envoyer et recuperer la req
		int verif = query.executeUpdate();
		return verif;
	}

	@Override
	public Produit getProduitbyId(int id) {
		// creation d'une requete jpql
		String req = "SELECT p FROM Produit as p where p.idProduit=:pId";
		// Creer un query
		Query query = em.createQuery(req);
		// Assigner les paramètres
		query.setParameter("pId", id);
		try {
			// envoyer et recuperer la req
			Produit pFind = (Produit) query.getSingleResult();
			return pFind;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Produit getProduitbyName(String designation) {
		// creation d'une requete jpql
				String req = "SELECT p FROM Produit as p where p.designation=:pdesignation";
				// Creer un query
				Query query = em.createQuery(req);
				// Assigner les paramètres
				query.setParameter("pdesignation", designation);
				try {
					// envoyer et recuperer la req
					Produit pFind = (Produit) query.getSingleResult();
					return pFind;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	}
}
