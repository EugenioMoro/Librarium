package it.unisalento.business;

import it.unisalento.view.Dialogs.MessageBoxes;

import javax.mail.Message.RecipientType;

import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;

public class EmailSender {
	private static EmailSender instance;
	
	public static EmailSender getInstance(){
		if (instance==null)
			instance = new EmailSender();
		return instance;
	}

	public void InviaEmail(String oggetto, String destinatario, String mailDestinatario,String testo){
		try{
			final Email email = new Email();
			email.setFromAddress("Librarium", "librariumSoftware@gmail.com");
			email.setSubject(oggetto);
			email.addRecipient(destinatario, mailDestinatario, RecipientType.TO);
			email.setText(testo);

			new Mailer("smtp.gmail.com", 465, "librariumSoftware@gmail.com", "marramoro", TransportStrategy.SMTP_SSL).sendMail(email);
		} catch (Exception e){
			MessageBoxes.errore("Errore", "Impossibile inviare email");
		}
	}

}

