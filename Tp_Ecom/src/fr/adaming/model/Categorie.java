package fr.adaming.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categorie implements Serializable {
	
	// Attributs du modele
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCategorie;
	
	private String nomCategorie;
	
	@Lob
	private byte[] photo;
	
	private String description;

	
	// Constructeurs
	public Categorie() {
		super();
	}


	public Categorie(String nomCategorie, byte[] photo, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}


	public Categorie(long idCategorie, String nomCategorie, byte[] photo, String description) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	// Getters et setters
	public long getIdCategorie() {
		return idCategorie;
	}


	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}


	public String getNomCategorie() {
		return nomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	// methode tooString
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + "]";
	}
	
	
	

}
