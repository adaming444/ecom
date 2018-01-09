package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;


import fr.adaming.model.Produit;

@Local
public interface IProduitDao {

	public List<Produit> getAllProduit();

	public Produit addProduit(Produit p);

	public Produit updateProduit(Produit p);

	public int deleteProduit(int id);
	
	public int deleteProduitByName(String name);

	public Produit getProduitbyId(int id);
	
	public Produit getProduitbyName(String designation);

}
