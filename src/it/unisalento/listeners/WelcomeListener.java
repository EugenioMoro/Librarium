package it.unisalento.listeners;

import it.unisalento.business.Session;
import it.unisalento.business.UserManager;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.RegistraView;
import it.unisalento.view.Frames.WelcomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



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

		if(!WelcomeView.getTxtUsername().getText().isEmpty() && !WelcomeView.getPwdPassword().getText().isEmpty()){
		Session.currentSession().getU().setUsername(WelcomeView.getTxtUsername().getText());
		Session.currentSession().getU().setPassword(new String(WelcomeView.getPwdPassword().getPassword()));
		System.out.println(Session.currentSession().getU().getUsername()+" "+Session.currentSession().getU().getPassword());
		
		if(UserManager.logIn()) MessageBoxes.alert("Successo", "Loggato come"+Session.currentSession().getTipo());
		else MessageBoxes.alert("Login Fallito", "Login fallito");
		} else MessageBoxes.alert("Attenzione", "Inserisci Username e Password");
		
		//TODO metodo da terminare
		
	}
	
	private void registraOption(){
		RegistraView frame = new RegistraView();
		frame.setVisible(true);
	}
	
	private void passwordOption(){
		MessageBoxes.alert("TODO", "Metodo da implementare");
	}
	
	private void catalogoOption(){
		MessageBoxes.alert("TODO", "Metodo da implementare");
	}

}
