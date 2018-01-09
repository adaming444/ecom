package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieService {

	public Categorie addCategorie(Categorie c);

	public Categorie updateCategorie(Categorie c);

	public int deleteCategorie(Long id);

	public Categorie getCategorieById(Long id);
	
	public Categorie getCategorieByName(String Name);

	public List<Categorie> getAllCategorie();

}
