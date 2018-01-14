package fr.adaming.service;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.adaming.dao.IProduitDao;
import fr.adaming.dao.ProduitDaoImpl;
import fr.adaming.model.Produit;

@Stateful
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

	@Override
	public int deleteProduitByName(String name) {
		return produitDao.deleteProduitByName(name);
	}

	@Override
	public void confirmAddProd( Produit p) throws Exception {
		
		String smtpHost = "smtp.gmail.com";
	    String from = "adaming444@gmail.com";
	    String to = "adaming444@gmail.com";
	    String username = "adaming444@gmail.com";
	    String password = "adaming44";
	 
	    Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.port", "587");
	 
	    Session session = Session.getDefaultInstance(props);
	    session.setDebug(true);
	 
	    MimeMessage message = new MimeMessage(session);   
	    message.setFrom(new InternetAddress(from));
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	    message.setSubject("Confirmation ajout produit");
//	    message.setText("Vous avez bien ajouter le produit :"+p.getDesignation());
//	    message.setText("La quantit� du produit ajout� est de :  :"+p.getQuantite());
//	    message.setText(" Descriptif du produit :"+p.getDescription());
	    message.setText("Vous avez bien ajouter le produit :"+p.getDesignation()+'\n'+" Descriptif du produit :"+p.getDescription()+'\n'+"Le prix unitaire du produit est :"+p.getPrix()+'\n'+"La quantit� du produit ajout� est de :  :"+p.getQuantite());
	 
	    Transport tr = session.getTransport("smtp");
	    tr.connect(smtpHost, username , password);
	    message.saveChanges();
	 
	    // tr.send(message);
	    /** Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme pour une seule adresse... */
	 
	    tr.sendMessage(message,message.getAllRecipients());
	    tr.close();
		
	}
	
	public List<Produit> getAllProduitByCategorie(long idCat) {
		return produitDao.getAllProduitByCategorie(idCat);
	};

}
