package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {
	
	@PersistenceContext(unitName="PU") // pour l'injection d'un em
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCategorie(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie getCategorieById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// TODO Auto-generated method stub
		return null;
	}

}
