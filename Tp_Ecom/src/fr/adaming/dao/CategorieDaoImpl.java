package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "PU") // pour l'injection d'un em
	EntityManager em;

	// Setter de l'em
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		em.persist(c);
		// Retourne la categorie avec un id
		return c;
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		// Recuperation de la categorie dans la bdd
		Categorie catOut = this.getCategorieById(c.getIdCategorie());

		// on sette les nouvelles valeurs
		catOut.setNomCategorie(c.getNomCategorie());
		catOut.setDescription(c.getDescription());
		catOut.setPhoto(c.getPhoto());

		// actualisation de la bdd
		em.merge(catOut);
		return catOut;
	}

	@Override
	public int deleteCategorie(Long id) {
		// ecriture de la requete
		String req = "DELETE FROM Categorie c WHERE c.idCategorie=: pId";

		// creation de la query
		Query query = em.createQuery(req);

		// Assignation des parametres
		query.setParameter("pId", id);

		// execute la requete et retourne l'entier résultat
		return query.executeUpdate();
	}

	@Override
	public Categorie getCategorieById(Long id) {
		// ecriture de la requete
		String req = "SELECT c FROM Categorie c WHERE c.idCategorie=:pId";

		// creation de la query
		Query query = em.createQuery(req);

		// Assignation des parametres
		query.setParameter("pId", id);

		// envoie et recuperation du resultat et retour
		return (Categorie) query.getSingleResult();
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// ecriture de la requete
		String req = "SELECT c FROM Categorie c";

		// creation de la query
		Query query = em.createQuery(req);

		// envoie et recuperation du resultat et retour
		return query.getResultList();
	}

	@Override
	public Categorie getCategorieByName(String Name) {
		// ecriture de la requete
		String req = "SELECT c FROM Categorie c WHERE c.nomCategorie=: pNomC";

		// creation de la query
		Query query = em.createQuery(req);

		// Assignation des parametres
		query.setParameter("pNomC", Name );

		// envoie et recuperation du resultat et retour
		return (Categorie) query.getSingleResult();
		
	}

}
