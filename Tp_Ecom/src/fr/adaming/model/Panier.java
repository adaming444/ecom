package fr.adaming.model;

import java.util.List;

public class Panier {

	//Aucun attribut
	
	//Association avec ligne de commande
	private List<LigneCommande> ligneCommande;

	public List<LigneCommande> getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(List<LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
	}
	
	
	
}
