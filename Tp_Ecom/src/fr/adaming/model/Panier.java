package fr.adaming.model;

import java.util.List;

public class Panier {

	//Aucun attribut
	private double total;
	
	//Association avec ligne de commande
	private List<LigneCommande> listeLigneCommande;
	
	// Getters et setters
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}

	@Override
	public String toString() {
		return "Panier [total=" + total + ", listeLigneCommande=" + listeLigneCommande + "]";
	}
	
}
