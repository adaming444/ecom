package fr.adaming.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class testMail {

	public static void main (String[] args) throws Exception {
		 
	    String smtpHost = "smtp.gmail.com";
	    String from = "adaming444@gmail.com";
	    String to = "arnaudfardin@gmail.com";
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
	    message.setText("Vous avez bien ajouter le produit :");
	 
	    Transport tr = session.getTransport("smtp");
	    tr.connect(smtpHost, username , password);
	    message.saveChanges();
	 
	    // tr.send(message);
	    /** Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme pour une seule adresse... */
	 
	    tr.sendMessage(message,message.getAllRecipients());
	    tr.close();
	 
	  }
}
