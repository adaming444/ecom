package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService {

	@EJB
	ICategorieDao categorieDao;
	
	// Setter de categorie Dao
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		return categorieDao.addCategorie(c);
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		return categorieDao.updateCategorie(c);
	}

	@Override
	public int deleteCategorie(Long id) {
		return categorieDao.deleteCategorie(id);
	}

	@Override
	public Categorie getCategorieById(Long id) {
		return categorieDao.getCategorieById(id);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		return categorieDao.getAllCategorie();
	}

}
