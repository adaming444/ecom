package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;

@Local
public interface ICommandeService {

	public List<Commande> getAllCommandes();
	
	public Commande addCommande(Commande c);
	
	public Commande updateCommande(Commande c);
	
	public int deleteCommande(Long id);

	public Commande getCommandeById(Long id);
}
