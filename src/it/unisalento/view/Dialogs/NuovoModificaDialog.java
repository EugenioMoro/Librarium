package it.unisalento.view.Dialogs;

import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Genere;
import it.unisalento.business.Session;
import it.unisalento.view.Frames.GestioneGCAFrame;
import it.unisalento.view.Panels.AutoriJpan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class NuovoModificaDialog extends JDialog {
	
	public static final String GENEREOPT="genere";
	public static final String CASAOPT="casa";
	public static final String AUTOREOPT="autore";
	
	private String option;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblNome;
	private JTextField textField_1;
	private JLabel lblCognome;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//NuovoModificaDialog dialog = new NuovoModificaDialog(GENEREOPT, true);
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public NuovoModificaDialog(Object ob, String option) {
		
		this.option=option;
		setup(ob);
		
	}
	
	public NuovoModificaDialog(String option){
		this.option=option;
		setup(null);
	}
	
	private void setup(Object ob){
		
		
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			
		
		
		switch(option){
		case GENEREOPT:{
			
			{
				JLabel lblNuovoNome = new JLabel("Nuovo nome:");
				contentPanel.add(lblNuovoNome, "cell 0 0");
			}
			{
				textField = new JTextField();
				contentPanel.add(textField, "cell 0 1,growx");
				textField.setColumns(10);
			}
			
			if(ob==null){
				setTitle("Nuovo Genere");
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (textField.getText().isEmpty()){
							MessageBoxes.alert("Attenzione", "Inserisci nome");
						} else {
							Genere g=new Genere(textField.getText());					
							Genere_DAO.getInstance().inserisciGenere(g);
							Session.currentSession().getGeneri().add(g);
							MessageBoxes.alert("", "Genere creato");
							dispose();
						}
					}
				});
			} else {
				setTitle("Modifica Genere");
				textField.setText(((Genere)ob).getNome());
				textField.selectAll();
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (textField.getText().isEmpty()){
							MessageBoxes.alert("Attenzione", "Inserisci nome");
						} else {
							Genere_DAO.getInstance().modifica(((Genere)ob).getId(), textField.getText());
							((Genere)ob).setNome(textField.getText());
							MessageBoxes.alert("", "Genere modificato");
							dispose();
						}
					}
				});	
			}
		}
		break;
		
		case CASAOPT:{

			{
				JLabel lblNuovoNome = new JLabel("Nuovo nome:");
				contentPanel.add(lblNuovoNome, "cell 0 0");
			}
			{
				textField = new JTextField();
				contentPanel.add(textField, "cell 0 1,growx");
				textField.setColumns(10);
			}
			
			if (ob==null){
				setTitle("Nuova Casa Editrice");
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (textField.getText().isEmpty()) MessageBoxes.alert("Attenzione", "Inserisci nome");
						else{
							CasaEd c = new CasaEd(textField.getText());
							CasaEd_DAO.getInstance().inserisciCasa(c);
							Session.currentSession().getCaseEd().add(c);
							MessageBoxes.alert("", "Casa editrice aggiunta");
							dispose();
						}
					}
				});
			} else{
				setTitle("Modifica Casa Editrice");
				textField.setText(((CasaEd)ob).getNome());
				textField.selectAll();
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (textField.getText().isEmpty()) MessageBoxes.alert("Attenzione", "Inserisci nome");
						else{
							CasaEd_DAO.getInstance().modifica(((CasaEd)ob).getId(), textField.getText());
							((CasaEd)ob).setNome(textField.getText());
							MessageBoxes.alert("", "Casa editrice modificata");
							dispose();
						}
					}
				});
			}
		}
		break;
		
		case AUTOREOPT:{
			

			
			setBounds(100, 100, 299, 144);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][]"));
			{
				lblNome = new JLabel("Nome:");
				contentPanel.add(lblNome, "cell 0 0");
			}
			{
				textField_1 = new JTextField();
				contentPanel.add(textField_1, "cell 2 0,growx");
				textField_1.setColumns(10);
			}
			{
				lblCognome = new JLabel("Cognome:");
				contentPanel.add(lblCognome, "cell 0 1");
			}
			{
				textField_2 = new JTextField();
				contentPanel.add(textField_2, "cell 2 1,growx");
				textField_2.setColumns(10);
			}
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			if (ob==null){
				setTitle("Nuovo Autore");
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (textField_1.getText().isEmpty() || textField_2.getText().isEmpty())
							MessageBoxes.alert("Attenzione", "Inserisci tutti i campi");
						else {
							Autore a = new Autore(textField_1.getText(), textField_2.getText());
							Autore_DAO.getInstance().nuovoAutore(a);
							Session.currentSession().getAutori().add(a);
							MessageBoxes.alert("", "Autore aggiunto");
							dispose();
						}
						
					}
				});
			} else{
				setTitle("Modifica Autore");
				textField_1.setText(((Autore)ob).getNome());
				textField_2.setText(((Autore)ob).getCognome());
				textField_1.selectAll();
				textField_2.selectAll();
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (textField_1.getText().isEmpty() || textField_2.getText().isEmpty())
							MessageBoxes.alert("Attenzione", "Inserisci tutti i campi");
						else {
							Autore_DAO.getInstance().modificaNome(((Autore)ob).getId(), textField_1.getText());
							((Autore)ob).setNome(textField_1.getText());
							Autore_DAO.getInstance().modificaCognome(((Autore)ob).getId(), textField_2.getText());
							((Autore)ob).setCognome(textField_2.getText());
							MessageBoxes.alert("", "Autore modificato");
							dispose();
						}
						
					}
				});
			}
			GestioneGCAFrame.refresh();
		}
		break;
		}
	
		
	}

}
