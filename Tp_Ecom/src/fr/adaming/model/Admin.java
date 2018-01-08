package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {

	// Déclarations des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mail;
	private String mdp;

	// Déclarations des trois constructeurs

	public Admin() {
		super();

	}

	public Admin(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}

	public Admin(int id, String mail, String mdp) {
		super();
		this.id = id;
		this.mail = mail;
		this.mdp = mdp;
	}

	// Déclarations des get et set

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", mail=" + mail + ", mdp=" + mdp + "]";
	}

}
