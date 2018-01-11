package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Local
public interface ILigneCommandeService {

	public LigneCommande addLigneCommande(LigneCommande lc, Produit p);

	public LigneCommande updateLigneCommande(LigneCommande lc, Produit p);

	public int deleteLigneCommande(Long id);

	public LigneCommande getLigneCommandeById(Long id);

	public List<LigneCommande> getAllLigneCommande();
	
	public List<LigneCommande> associerCommande(List<LigneCommande> listLC, Commande c);

}
