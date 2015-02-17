package it.unisalento.business;



import it.unisalento.DbConnection.DbConnection;
import it.unisalento.view.Dialogs.InizializzazioneDialog;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.ClienteView;
import it.unisalento.view.Frames.RegistraView;
import it.unisalento.view.Frames.ScaffaliView;
import it.unisalento.view.Frames.VenditeView;
import it.unisalento.view.Frames.WelcomeView;

import javax.swing.JFrame;




public class MainActivity {

	static JFrame mainframe;
	
	public static void main(String[] args) {
		
		
		
		
		initialize();
		
		
		
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
	
	public static void openWelcomeView(){
		Session.destroy();
		if (MainActivity.mainframe!=null){
			MainActivity.mainframe.dispose();
		}
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
	
	public static void initialize(){
		Session.destroy();
		Session.currentSession();
		if (!DbConnection.connetti("librarium", "root", "root")){
			MessageBoxes.errore("Errore", "Impossibile connettersi al database");
		}
		if(Session.currentSession().getNomeLibreria().isEmpty()){
			InizializzazioneDialog dialog = new InizializzazioneDialog();
			dialog.setVisible(true);
		} else 
			openWelcomeView();
	}
	
	public static void abort(){
		System.exit(0);
	}
}
