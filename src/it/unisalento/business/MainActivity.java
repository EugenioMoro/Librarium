package it.unisalento.business;



import it.unisalento.view.Frames.ClienteView;
import it.unisalento.view.Frames.RegistraView;
import it.unisalento.view.Frames.WelcomeView;

import javax.swing.JFrame;




public class MainActivity {

	static JFrame mainframe;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Variabili di sessione globali
		
		
		Session.currentSession(); //Inizializza la sessione 
		mainframe=new WelcomeView();
		mainframe.setVisible(true);
		
		
		
	}
	
	public static void openClienteView(){
	
		
		MainActivity.mainframe.dispose();
		MainActivity.mainframe=new ClienteView();
		MainActivity.mainframe.setVisible(true);
	}
	
	public static void openVenditeView(){
		//TODO implementare
	}

	public static void openScaffaliView(){
		//TODO implementare
	}
	
	public static void backToWelcome(){
		Session.currentSession().destroy();
		MainActivity.mainframe.dispose();
		MainActivity.mainframe=new WelcomeView();
		MainActivity.mainframe.setVisible(true);
	}
	
	public static void openRegistraView(){
		MainActivity.mainframe.dispose();
		MainActivity.mainframe=new RegistraView();
		MainActivity.mainframe.setVisible(true);
	}
}
