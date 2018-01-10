package fr.adaming.model;

import java.util.List;

public class Panier {

	//Aucun attribut
	
	//Association avec ligne de commande
	private List<LigneCommande> listeLigneCommande;

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}


	
}
