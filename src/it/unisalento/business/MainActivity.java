package it.unisalento.business;



import it.unisalento.view.Frames.ClienteView;
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
	
	public void openVenditeView(){
		//TODO implementare
	}

	public void openScaffaliView(){
		//TODO implementare
	}
}
