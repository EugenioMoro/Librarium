package it.unisalento.view.Dialogs;

import it.unisalento.Model.Libro;
import it.unisalento.business.Session;
import it.unisalento.view.Models.ModelMethods;
import it.unisalento.view.Models.SearchCaseComboModel;
import it.unisalento.view.Models.SearchGenereComboModel;
import it.unisalento.view.Panels.LibriJPanJTab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ModificaDialog extends JDialog {

	public final static String TITOLOOPT="titolo";
	public final static String GENEREOPT="genere";
	public final static String CASAOPT="casa";
	public final static String COSTOOPT="costo";
	public final static String DISPOPT="disp";
	public final static String ISBNOPT="isbn";
	public final static String PAGINEOPT="pagine";



	private String option;
	private Libro l;

	private ActionListener 	listener;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificaDialog dialog = new ModificaDialog("titolo", 1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificaDialog(String option, int index) {
		this.option=option;
		l=Session.currentSession().getSearchResults().get(index);
		setup();

	}

	private void setup(){

		//setup comune a tutte le opzioni
		setBounds(100, 100, 450, 129);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			buttonPane.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();

				}
			});
		}

		//setup del panel e del listener a seconda di option
		switch (option){
		case TITOLOOPT:{
			setTitle("Nuovo Titolo");
			textField = new JTextField(l.getTitolo());
			textField.selectAll();
			contentPanel.add(textField);
			textField.setColumns(10);

			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (ModelMethods.modificaTitolo(l, textField.getText())){
						MessageBoxes.alert("", "Titolo modificato");
						tableRefresh();
						dispose();
					}

				}
			});
		}
		break;

		case GENEREOPT:{
			setTitle("Cambia Genere");
			comboBox = new JComboBox<String>(new SearchGenereComboModel());
			contentPanel.add(comboBox);
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					ModelMethods.modificaGenere(l, Session.currentSession().getGeneri().get(comboBox.getSelectedIndex()));
					MessageBoxes.alert("", "Genere Aggiornato");
					dispose();
				}
			});
			
			
		}
		break;
		case CASAOPT:{
			setTitle("Cambia Casa Editrice");
			comboBox = new JComboBox<String>(new SearchCaseComboModel());
			contentPanel.add(comboBox);
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					ModelMethods.modificaCasa(l, Session.currentSession().getCaseEd().get(comboBox.getSelectedIndex()));
					MessageBoxes.alert("", "Casa editrice aggiornata");
					dispose();
				}
			});
			
			
		}
		break;
		case COSTOOPT:{
			setTitle("Nuovo Costo");
			textField = new JTextField(Float.toString(l.getCosto()));
			textField.selectAll();
			contentPanel.add(textField);
			textField.setColumns(10);

			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (ModelMethods.modificaCosto(l, textField.getText())){
						MessageBoxes.alert("", "Costo modificato");
						tableRefresh();
						dispose();
					}

				}
			});
		}
		break;
		case DISPOPT:{
			setTitle("Aggiorna disponibilità");
			textField = new JTextField(l.getDisp());
			textField.selectAll();
			contentPanel.add(textField);
			textField.setColumns(10);

			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (ModelMethods.modificaDisp(l, textField.getText())){
						MessageBoxes.alert("", "Disponibilità aggiornata");
						tableRefresh();
						dispose();
					}

				}
			});
		}
		break;
		case ISBNOPT:{
			setTitle("Modifica ISBN");
			textField = new JTextField(l.getIsbn());
			textField.selectAll();
			contentPanel.add(textField);
			textField.setColumns(10);

			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (ModelMethods.modificaISBN(l, textField.getText())){
						MessageBoxes.alert("", "ISBN modificato");
						tableRefresh();
						dispose();
					}

				}
			});
		}
		break;
		case PAGINEOPT:{
			setTitle("Modifica pagine");
			textField = new JTextField(l.getPagine());
			textField.selectAll();
			contentPanel.add(textField);
			textField.setColumns(10);

			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (ModelMethods.modificaPagine(l, textField.getText())){
						MessageBoxes.alert("", "Numero pagine aggiornato");
						tableRefresh();
						dispose();
					}

				}
			});
		}


		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		}


	}
	
	private void tableRefresh(){
		LibriJPanJTab.refresh();
	}
	
}
