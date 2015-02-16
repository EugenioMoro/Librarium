package it.unisalento.business;



import it.unisalento.view.Frames.ClienteView;
import it.unisalento.view.Frames.RegistraView;
import it.unisalento.view.Frames.ScaffaliView;
import it.unisalento.view.Frames.VenditeView;
import it.unisalento.view.Frames.WelcomeView;

import javax.swing.JFrame;




public class MainActivity {

	static JFrame mainframe;
	
	public static void main(String[] args) {
		
		
		
		Session.currentSession(); //Inizializza la sessione 
		mainframe=new WelcomeView();
		mainframe.pack();
		mainframe.setVisible(true);
		
		
		
	}
	
	public static void openClienteView(){
	
		
		MainActivity.mainframe.dispose();
		MainActivity.mainframe=new ClienteView();
		MainActivity.mainframe.setVisible(true);
	}
	

	public static void openScaffaliView(){
		MainActivity.mainframe.dispose();
		MainActivity.mainframe=new ScaffaliView();
		mainframe.setVisible(true);
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
	
	public static void openVenditeView(){
		MainActivity.mainframe.dispose();
		MainActivity.mainframe=new VenditeView();
		MainActivity.mainframe.setVisible(true);
	}
}
