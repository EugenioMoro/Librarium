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

	public void test(){
		try{
			final Email email = new Email();
			email.setFromAddress("Librarium", "librariumSoftware@gmail.com");
			email.setSubject("test");
			email.addRecipient("Test", "moro.eugenio@yahoo.it", RecipientType.TO);
			email.setText("test test test");

			new Mailer("smtp.gmail.com", 465, "librariumSoftware@gmail.com", "marramoro", TransportStrategy.SMTP_SSL).sendMail(email);
		} catch (Exception e){
			MessageBoxes.errore("Errore", "Impossibile inviare email");
		}
	}

}

