package it.unisalento.listeners;

import it.unisalento.business.MainActivity;
import it.unisalento.business.Session;
import it.unisalento.business.UserManager;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Dialogs.PasswordDimenticataDialog;
import it.unisalento.view.Frames.OspiteView;
import it.unisalento.view.Frames.WelcomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;



public class WelcomeListener implements ActionListener {
	
	
	public final static String LOGINOPT = "login";
	public final static String REGISTRAOPT = "registra";
	public final static String PASSOPT = "password";
	public final static String CATALOGOOPT = "catalogo";
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command){
		case "login": loginOption();
		break;
		case "registra": registraOption();
		break;
		case "password": passwordOption();
		break;
		case "catalogo": catalogoOption();
		break;
		default: break;
		}
		
	}
	
	private void loginOption(){

		if(!WelcomeView.getTxtUsername().getText().isEmpty() && !new String(WelcomeView.getPwdPassword().getPassword()).isEmpty()){
		
		if(UserManager.logIn(WelcomeView.getTxtUsername().getText(),new String(WelcomeView.getPwdPassword().getPassword()) )){
			
			switch (Session.currentSession().getTipo()){
			case "Cliente":MainActivity.openClienteView();
			break;
			case "Vendite": MainActivity.openVenditeView();
			break;
			case "Scaffali": MainActivity.openScaffaliView();
			}
			
			
		}
		else MessageBoxes.alert("Attenzione", "Username o password errati");
		} else MessageBoxes.alert("Attenzione", "Inserisci Username e Password");
		
		//TODO metodo da terminare
		
	}
	
	private void registraOption(){
		MainActivity.openRegistraView();
	}
	
	private void passwordOption(){
		JDialog passDimenticata = new PasswordDimenticataDialog();
		passDimenticata.setVisible(true);
		
		//jdialog con usrname
	}
	
	private void catalogoOption(){
		JFrame frame=new OspiteView();
		frame.setVisible(true);
	}

}
