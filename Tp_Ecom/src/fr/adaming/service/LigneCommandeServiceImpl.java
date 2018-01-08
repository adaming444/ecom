package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService {

	@EJB
	ILigneCommandeDao ligneCommandeDao;
	
	// setter ligne commande dao
	public void setLigneCommandeDao(ILigneCommandeDao ligneCommandeDao) {
		this.ligneCommandeDao = ligneCommandeDao;
	}

	@Override
	public LigneCommande addLigneCommande(LigneCommande lc, Produit p, Commande c) {
		lc.setCommande(c);
		lc.setProduit(p);
		lc.setPrix(lc.getQuantite()*p.getPrix());
		return ligneCommandeDao.addLigneCommande(lc);
	}

	@Override
	public LigneCommande updateLigneCommande(LigneCommande lc, Produit p, Commande c) {
		lc.setCommande(c);
		lc.setProduit(p);
		lc.setPrix(lc.getQuantite()*p.getPrix());
		return ligneCommandeDao.updateLigneCommande(lc);
	}

	@Override
	public int deleteLigneCommande(Long id) {
		return ligneCommandeDao.deleteLigneCommande(id);
	}

	@Override
	public LigneCommande getLigneCommandeById(Long id) {
		// TODO Auto-generated method stub
		return ligneCommandeDao.getLigneCommandeById(id);
	}

	@Override
	public List<LigneCommande> getAllLigneCommande() {
		return ligneCommandeDao.getAllLigneCommande();
	}

}
