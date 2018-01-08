package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@PersistenceContext(unitName = "PU") // pour l'injection d'un em
	EntityManager em;

	// setter de entity manager
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc) {
		em.persist(lc);
		return lc;
	}

	@Override
	public LigneCommande updateLigneCommande(LigneCommande lc) {
		LigneCommande ligneOut = this.getLigneCommandeById(lc.getIdLigne());
		
		ligneOut.setQuantite(lc.getQuantite());
		ligneOut.setPrix(lc.getPrix());
		
		em.merge(ligneOut);
		return ligneOut;
	}

	@Override
	public int deleteLigneCommande(Long id) {
		String req = "DELETE FROM LigneCommande l WHERE l.idLigne=:pId";

		Query query = em.createQuery(req);

		query.setParameter("pId", id);

		return query.executeUpdate();
	}

	@Override
	public LigneCommande getLigneCommandeById(Long id) {
		String req = "SELECT l FROM LigneCommande l WHERE l.idLigne=:pId";

		Query query = em.createQuery(req);

		query.setParameter("pId", id);
		
		return (LigneCommande) query.getSingleResult();
	}

	@Override
	public List<LigneCommande> getAllLigneCommande() {
		String req = "SELECT l FROM LigneCommande l";

		Query query = em.createQuery(req);

		return query.getResultList();
	}

}
