package it.unisalento.business;

import it.unisalento.DataAccessObjects.LogInDAO;
import it.unisalento.DataAccessObjects.NewUserDAO;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Utente;
import it.unisalento.view.Dialogs.MessageBoxes;

public class UserManager {
	

	public static boolean logIn(){ //una volta che questo metodo ritrona true la view può essere caricata con utente o cliente
		
		Utente u=Session.currentSession().getU();
		if(!LogInDAO.getInstance().checkCredenziali(u)) {
			MessageBoxes.alert("Attenzione", "Username o password errate");
			return false;
		}
		
		String tipo=LogInDAO.getInstance().checkTipo(u);
		Session.currentSession().setTipo(tipo);
		
		switch (tipo){
		case "Cliente":
			Session.currentSession().setC(LogInDAO.getInstance().caricaCliente(u));
			break;
		default: Session.currentSession().setU(LogInDAO.getInstance().caricaUtente(u));
		}
		
		
		return true;
	}
	
	
	public static void utenteToCliente(){ //questo metodo copia i dati parziali di utente in cliente, preparandolo alla registrazione
		
		Cliente c=Session.currentSession().getC();
		Utente u=Session.currentSession().getU();
		
		c.setUsername(u.getUsername());
		c.setPassword(c.getPassword());
		c.setNome(u.getNome());
		c.setCognome(u.getCognome());
		
		Session.currentSession().DestroyU();
	}
	
	
	private static boolean checkOnString(String s){
		for (int i=0; i<s.length(); i++){
			if (!Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i))) return false;
		}
		return true;
	}
	
	public static boolean checkCoerenzaUtente(String nome, String cognome, String username, String password){
		String ErrorMessage="";
		
		if (!checkOnString(nome)) ErrorMessage="Nome: caratteri non validi ";
		if (nome.isEmpty()) ErrorMessage="Nome Obbligatorio";
		if (nome.length()>20) ErrorMessage=ErrorMessage+'\n'+"Nome deve essere minore di 20 caratteri";
		
		if (!checkOnString(cognome)) ErrorMessage=ErrorMessage+'\n'+"Cognome: caratteri non validi";
		if (cognome.isEmpty()) ErrorMessage=ErrorMessage+'\n'+"Cognome Obbligatorio";
		if (cognome.length()>20) ErrorMessage=ErrorMessage+'\n'+"Cognome deve essere minore di 20 caratteri";
		
		if (!checkOnString(username)) ErrorMessage=ErrorMessage+'\n'+"Username: caratteri non validi";
		if (username.isEmpty()) ErrorMessage=ErrorMessage+'\n'+"Username Obbligatorio";
		if (!username.isEmpty() && username.length()<5 || username.length()>16) ErrorMessage=ErrorMessage+'\n'+"Username deve essere compreso tra 5 e 16 caratteri";
		
		if (!checkOnString(password)) ErrorMessage=ErrorMessage+'\n'+"Password: caratteri non validi";
		if (password.isEmpty()) ErrorMessage=ErrorMessage+'\n'+"Password Obbligatoria";
		if (!password.isEmpty() && password.length()<8 || password.length()>16) ErrorMessage=ErrorMessage+'\n'+"Password deve essere compresa tra 8 e 16 caratteri";
		
		if(!ErrorMessage.equals("")){
			MessageBoxes.alert("Attenzione", ErrorMessage);
			return false;
		}
		return true;
	}
	
	public static boolean checkCoerenzaCliente(String email, String tel){
		String ErrorMessage="";
		
		int dotCount=0;
		int atCount=0;
		
		//if (!checkOnString(nome)) ErrorMessage="Nome: caratteri non validi ";
		if (email.isEmpty()) ErrorMessage="Email Obbligatoria";
		else {
			for (int i=0; i<email.length(); i++){
				if (!Character.isLetter(email.charAt(i))){
					if (email.charAt(i)=='.')
					{
						dotCount++;
						continue;
					}
					if (email.charAt(i)=='@'){
						atCount++;
						continue;
					} else {
						ErrorMessage="Email non valida";
						break;
					}
				}
				
			}
			if (ErrorMessage.equals("")){
				if (atCount!=1 || dotCount==0) ErrorMessage="Email non valida";
			}
		}
		
		
		
		if (tel.isEmpty()) ErrorMessage=ErrorMessage+'\n'+"Numero di telefono obbligatorio";
		else {
			if (tel.length()==10){
				boolean ok=true;
				for (int i=0; i<tel.length(); i++){
					if (!Character.isDigit(tel.charAt(i))){
						ok=false;
						break;
					}
				}
				if (!ok) ErrorMessage=ErrorMessage+'\n'+"Telefono non valido";
			}
			else ErrorMessage=ErrorMessage+'\n'+"Telefono non valido";
		}
		
		
		if(!ErrorMessage.equals("")){
			MessageBoxes.alert("Attenzione", ErrorMessage);
			return false;
		}
		return true;
	}
	
	public static boolean registraUtente(){
		Utente u=Session.currentSession().getU();
		
		//check del caso
		/*if (!checkCoerenzaUtente(u.getNome(), u.getCognome(), u.getUsername(), u.getPassword())){
			Session.currentSession().DestroyU();
			return false;
		}*/
		if (!NewUserDAO.getInstance().isNewUsername(u.getUsername())){
			MessageBoxes.alert("Attenzione", "Username già esistente");
			Session.currentSession().DestroyU();
			return false;
		}
		
		
		switch (Session.currentSession().getTipo()){
		case "Scaffali": NewUserDAO.getInstance().registraAddScaffali(u);
		break;
		case "Vendite" : NewUserDAO.getInstance().registraAddVendite(u);
		break;
		default: MessageBoxes.errore("Errore", "Errore sconosciuto");
		break;
		}
		
		
		
		return true;
	}
	
	public static boolean registraCliente(){
		
		Cliente c=Session.currentSession().getC();
		
		if(!NewUserDAO.getInstance().isNewEmail(c.getEmail())){
			MessageBoxes.alert("Attenzione", "Email già inserita");
			return false;
		}
		
		if (!NewUserDAO.getInstance().isNewUsername(c.getUsername())){
			MessageBoxes.alert("Attenzione", "Username già esistente");
			Session.currentSession().DestroyU();
			return false;
		}
		
		NewUserDAO.getInstance().registraCliente(c);
		
		return true;
	}
}
