package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao {

	@PersistenceContext(unitName = "PU")
	EntityManager em; // pour l'injection d'un EM

	// setters pour l'injection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Commande> getAllCommandes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande addCommande(Commande c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande updateCommande(Commande c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCommande(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Commande getCommandeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
