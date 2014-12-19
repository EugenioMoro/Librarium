package it.unisalento.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageBoxes extends JOptionPane {  //forse l'estensione è inutile in quanto si fa riferimento a questa classe da main che richiede metodi statici senza costruttore super

	
	private static JFrame frame=new JFrame();
	
	public static void errore(String titolo, String messaggio){
		JOptionPane.showMessageDialog(frame,messaggio, titolo, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void alert(String titolo, String messaggio){
		JOptionPane.showMessageDialog(frame, messaggio, titolo, JOptionPane.WARNING_MESSAGE);
	}
	
	
	
	/**
	 * 
	 */
	



}
