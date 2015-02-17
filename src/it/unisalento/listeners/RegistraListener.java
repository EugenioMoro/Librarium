package it.unisalento.listeners;

import it.unisalento.DataAccessObjects.NewUserDAO;
import it.unisalento.business.MainActivity;
import it.unisalento.business.Session;
import it.unisalento.business.UserManager;
import it.unisalento.view.Dialogs.AddettoRegDialog;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.RegCliente;
import it.unisalento.view.Frames.RegistraView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class RegistraListener implements ActionListener {

	public final static String CLIENTEOPT="cliente";
	public final static String SCAFFALIOPT="scaffali";
	public final static String VENDITEOPT="vendite";
	public final static String OKDIALOG="OK";
	public final static String CANCELDIALOG="cancel";
	public final static String REGCLIENTEOPT="regcliente";
	public final static String SESSOMOPT="sessom";
	public final static String SESSOFOPT="sessof";
	
	private static JDialog dialog= new AddettoRegDialog();
	private static JFrame regCliente;
	

	
	public RegistraListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command){
		case "cliente": clienteOption();
		break;
		case "scaffali": scaffaliOption();
		break;
		case "vendite": venditeOption();
		break;
		case "OK": OKOption();
		dialog.dispose();
		break;
		case "cancel": dialog.setVisible(false);
		break;
		case REGCLIENTEOPT: regClienteOption();
		break;
		}
		
	}
	
	private void clienteOption(){ //livello superiore
		
		
		if (UserManager.checkCoerenzaUtente(RegistraView.getTxtNome().getText(), RegistraView.getTxtCognome().getText(), RegistraView.getTxtUsername().getText(), new String(RegistraView.getTxtPassword().getPassword()))){

			if (NewUserDAO.getInstance().isNewUsername(RegistraView.getTxtUsername().getText())){
				
				Session.currentSession().getU().setUsername(RegistraView.getTxtUsername().getText());
				Session.currentSession().getU().setPassword(new String(RegistraView.getTxtPassword().getPassword()));
				
				Session.currentSession().getU().setNome(RegistraView.getTxtNome().getText());
				Session.currentSession().getU().setCognome(RegistraView.getTxtCognome().getText());

				regCliente=new RegCliente();
				regCliente.setVisible(true);

				Session.currentSession().setTipo("Cliente"); //RICORDA RICORDA RICORDA

				UserManager.utenteToCliente();

			}
			else MessageBoxes.alert("Attenzione", "Username già esistente");
		}
	}
	
	private boolean isGenderSelected(){
		ButtonModel model = RegCliente.getBg().getSelection();
		if(model==null){
			MessageBoxes.alert("Attenzione", "Selezionare un'opzione");
			return false;
		}
		return true;
	}

	private void scaffaliOption(){
		
		if (UserManager.checkCoerenzaUtente(RegistraView.getTxtNome().getText(), RegistraView.getTxtCognome().getText(), RegistraView.getTxtUsername().getText(), new String(RegistraView.getTxtPassword().getPassword()))){
			
		
		Session.currentSession().getU().setUsername(RegistraView.getTxtUsername().getText());
		Session.currentSession().getU().setPassword(new String(RegistraView.getTxtPassword().getPassword()));
		Session.currentSession().getU().setNome(RegistraView.getTxtNome().getText());
		Session.currentSession().getU().setCognome(RegistraView.getTxtCognome().getText());
		Session.currentSession().setTipo("Scaffali"); //RICORDA RICORDA RICORDA
		
		//dialog= new AddettoRegDialog();
		dialog.setVisible(true);
	
		}//chiude if
		
	}

	private void venditeOption(){
		if (UserManager.checkCoerenzaUtente(RegistraView.getTxtNome().getText(), RegistraView.getTxtCognome().getText(),RegistraView.getTxtUsername().getText() , new String(RegistraView.getTxtPassword().getPassword()))){
			
			
			Session.currentSession().getU().setUsername(RegistraView.getTxtUsername().getText());
			Session.currentSession().getU().setPassword(new String(RegistraView.getTxtPassword().getPassword()));
			Session.currentSession().getU().setNome(RegistraView.getTxtNome().getText());
			Session.currentSession().getU().setCognome(RegistraView.getTxtCognome().getText());
			Session.currentSession().setTipo("Vendite"); //RICORDA RICORDA RICORDA
			
			//dialog= new AddettoRegDialog();
			dialog.setVisible(true);
		
			}//chiude if
			
	}
	
	private void OKOption(){

		
		if(Session.ADMINISTRATIVECODE.equals(AddettoRegDialog.getTextField().getText())){
			if(UserManager.registraUtente()){
				MessageBoxes.alert("Successo", "Utente registrato come "+Session.currentSession().getTipo()+'\n'+"Effettua il login per accedere al sistema");
				MainActivity.openWelcomeView();
			}
		
		}
		else MessageBoxes.alert("Attenzione", "Codice Amministrativo Errato");
	}

	private void regClienteOption(){
		
		if(UserManager.checkCoerenzaCliente(RegCliente.getTxtEmail().getText(), RegCliente.getTxtTelefono().getText()) && isGenderSelected()){
		
		Session.currentSession().getC().setEmail(RegCliente.getTxtEmail().getText());
		Session.currentSession().getC().setTelefono(RegCliente.getTxtTelefono().getText());
		java.sql.Date selectedDate = (java.sql.Date) RegCliente.getPicker().getModel().getValue();
		Session.currentSession().getC().setData_nascita(selectedDate);
		Session.currentSession().getC().setSesso(RegCliente.getBg().getSelection().getActionCommand().equals(SESSOMOPT)); //Giulia, questo è essere efficenti 
		
		if(UserManager.registraCliente()){
			MessageBoxes.alert("Successo", "Registrazione cliente avvenuta\nEffettua il login per accedere al sistema");
			regCliente.dispose();
			MainActivity.openWelcomeView();
		}

		}
	}

}
