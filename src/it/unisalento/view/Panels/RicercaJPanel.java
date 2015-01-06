package it.unisalento.view.Panels;

import it.unisalento.listeners.RicercaJPanelListener;
import it.unisalento.view.Models.SearchAutoriComboModel;
import it.unisalento.view.Models.SearchCaseComboModel;
import it.unisalento.view.Models.SearchGenereComboModel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class RicercaJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JComboBox<String> autoriCombo;
	private static JComboBox<String> generiCombo;
	private static JComboBox<String> caseCombo;
	private JButton btnRicerca;
	private JButton btnReset;
	private JLabel lblAutori;
	private JLabel lblGenere;
	private JLabel lblCasaEditrice;
	private JLabel lblTitolo;
	private static JTextField textField;
	private ActionListener listener;
	
	public RicercaJPanel() {
		
		listener=new RicercaJPanelListener();
		
		setLayout(new MigLayout("", "[85px][176.00px,grow][110px][][][][67.00]", "[20px][][24.00:18.00]"));
		
		lblTitolo = new JLabel("Titolo");
		add(lblTitolo, "cell 0 0,alignx left");
		
		textField = new JTextField();
		add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		lblAutori = new JLabel("Autore");
		add(lblAutori, "cell 0 1");
		
		lblGenere = new JLabel("Genere");
		add(lblGenere, "cell 1 1");
		
		lblCasaEditrice = new JLabel("Casa Editrice");
		add(lblCasaEditrice, "cell 2 1");
		
		autoriCombo = new JComboBox<String>(new SearchAutoriComboModel());
		add(autoriCombo, "cell 0 2,alignx left,growy");
		
		generiCombo = new JComboBox<String>(new SearchGenereComboModel());
		add(generiCombo, "cell 1 2,alignx left,growy");
		
		caseCombo = new JComboBox<String>(new SearchCaseComboModel());
		add(caseCombo, "cell 2 2,alignx left,growy");
		
		btnRicerca = new JButton("Ricerca");
		add(btnRicerca, "cell 4 2,alignx left,growy");
		btnRicerca.addActionListener(listener);
		
		btnReset = new JButton("Reset");
		add(btnReset, "cell 6 2,alignx left,growy");
		btnReset.addActionListener(listener);
		
		

	}
	public static JComboBox<String> getAutoriCombo() {
		return autoriCombo;
	}
	public static JComboBox<String> getGeneriCombo() {
		return generiCombo;
	}
	public static JComboBox<String> getCaseCombo() {
		return caseCombo;
	}
	public static JTextField getTextField() {
		return textField;
	}
	
	public static void resetFields(){
		autoriCombo.setSelectedIndex(-1);
		generiCombo.setSelectedIndex(-1);
		caseCombo.setSelectedIndex(-1);
		textField.setText(null);
	}
}
