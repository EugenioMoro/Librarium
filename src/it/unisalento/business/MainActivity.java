package it.unisalento.business;

import it.unisalento.view.RegistraView;


import it.unisalento.view.WelcomeView;

import javax.swing.JFrame;



public class MainActivity {

	static JFrame mainframe;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Variabili di sessione globali
		
		
		Session.currentSession(); //Inizializza la sessione 
		mainframe=new WelcomeView();
		mainframe.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
