package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.adaming.dao.IProduitDao;
import fr.adaming.dao.ProduitDaoImpl;
import fr.adaming.model.Produit;

@Stateless
public class ProduitServiceImpl implements IProduitService {
	
	//Transformation de l'association UML en java
	@EJB
	private IProduitDao produitDao;

	@Override
	public List<Produit> getAllProduit() {
		return produitDao.getAllProduit();
	}

	@Override
	public Produit addProduit(Produit p) {
		return produitDao.addProduit(p);
	}

	@Override
	public Produit updateProduit(Produit p) {
		return produitDao.updateProduit(p);
	}

	@Override
	public int deleteProduit(int id) {
		return produitDao.deleteProduit(id);
	}

	@Override
	public Produit getProduitbyId(int id) {
		return produitDao.getProduitbyId(id);
	}

	@Override
	public Produit getProduitbyName(String designation) {
		return produitDao.getProduitbyName(designation);
	}

}
