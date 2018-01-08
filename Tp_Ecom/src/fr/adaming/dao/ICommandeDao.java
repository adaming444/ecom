package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Admin;
import fr.adaming.model.Commande;

@Local
public interface ICommandeDao {

	public List<Commande> getAllCommandes();
	
	public Commande addCommande(Commande c);
	
	public Commande updateCommande(Commande c);
	
	public int deleteCommande(int id);

	public Commande getCommandeById(int id);
}
