package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Produit;

public interface IProduitService {

	public List<Produit> getAllProduit();

	public Produit addProduit(Produit p);

	public Produit updateProduit(Produit p);

	public int deleteProduit(int id);

	public Produit getProduitbyId(int id);

}
