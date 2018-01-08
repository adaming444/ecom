package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

public class CommandeServiceImpl implements ICommandeService {

	@EJB
	private ICommandeDao CommandeDao;
	
	public List<Commande> getAllCommandes() {
		return CommandeDao.getAllCommandes();
	}

	public Commande addCommande(Commande c) {
		return CommandeDao.addCommande(c);
	}

	public Commande updateCommande(Commande c) {
		return CommandeDao.updateCommande(c);
	}

	public int deleteCommande(Long id) {
		return CommandeDao.deleteCommande(id);
	}

	public Commande getCommandeById(Long id) {
		return CommandeDao.getCommandeById(id);
	}
}
