package fr.adaming.service;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService {

	@EJB
	private ICommandeDao CommandeDao;

	public List<Commande> getAllCommandes() {
		return CommandeDao.getAllCommandes();
	}

	public Commande addCommande(Commande c) {
		return CommandeDao.addCommande(c);
	}

	public Commande updateCommande(Commande c) {
		return CommandeDao.updateCommande(c);
	}

	public int deleteCommande(Long id) {
		return CommandeDao.deleteCommande(id);
	}

	public Commande getCommandeById(Long id) {
		return CommandeDao.getCommandeById(id);
	}

	@Override
	public void confirmCommandeMail(Commande c) throws Exception {
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
		message.setSubject("Confirmation de la commande");
		message.setText("Voici le récapitulatif de votre commande :" + '\n' + "Commande passée le :"
				+ c.getDateCommande() + '\n' + c.getListeLigneCommande());

		Transport tr = session.getTransport("smtp");
		tr.connect(smtpHost, username, password);
		message.saveChanges();

		// tr.send(message);
		/**
		 * Genere l'erreur. Avec l authentification, oblige d utiliser
		 * sendMessage meme pour une seule adresse...
		 */

		tr.sendMessage(message, message.getAllRecipients());
		tr.close();

	}
}
