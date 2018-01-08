package fr.adaming.dao;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;

@Stateless
public class AdminDaoImpl implements IAdminDao {

	// Ajout d'un entityManager
	@PersistenceContext(unitName = "PU")
	EntityManager em;

	@Override
	public Admin isExist(Admin a) throws Exception {

		// Construire la requete jpql
		String req = "SELECT a FROM Admin a WHERE a.mail=:pMail AND a.mdp=:pMdp";

		// Création d'un query
		Query query = em.createQuery(req);

		// Assignation des paramètres
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());

		Admin aOut = (Admin) query.getSingleResult();

		return aOut;
	}

}
