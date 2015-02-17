package it.unisalento.view.Dialogs;

import it.unisalento.DataAccessObjects.DatiLibreriaDao;
import it.unisalento.business.MainActivity;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class InizializzazioneDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField codeTextField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InizializzazioneDialog dialog = new InizializzazioneDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InizializzazioneDialog() {
		setTitle("Dati Libreria");
		setBounds(100, 100, 450, 405);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][][grow]", "[][][][grow]"));
		{
			JLabel lblCodiceAmministativo = new JLabel("Codice Amministativo:");
			contentPanel.add(lblCodiceAmministativo, "cell 0 0");
		}
		{
			codeTextField = new JTextField();
			contentPanel.add(codeTextField, "cell 2 0,growx");
			codeTextField.setColumns(10);
		}
		{
			JLabel lblNomeLibreria = new JLabel("Nome Libreria:");
			contentPanel.add(lblNomeLibreria, "cell 0 1");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 2 1,growx");
			textField.setColumns(10);
		}
		{
			JTextPane txtpnBenvenutoInLibrarium = new JTextPane();
			txtpnBenvenutoInLibrarium.setText("Benvenuto in Librarium\r\nQuesta \u00E8 la prima volta che il sistema viene eseguito.\r\n\r\nTi chiediamo di inserire il nome della tua libreria ed un codice amministativo.\r\n\r\nIl nome della libreria verr\u00E0 inserito all'interno delle email che i tuoi clienti riceveranno quando un libro da loro richiesto sar\u00E0 disponibile in magazzino o quando dimenticheranno la password per accedere al sistema.\r\n\r\nIl Codice Amministrativo dovr\u00E0 essere inserito ogni volta che viene registrato un nuovo account di tipo Addetto Scaffali oppure Addetto Vendite. Il Codice Amministrativo \u00E8 alfanumerico ed ha una lunghezza minima obbligatoria di 4 caratteri. Ti consigliamo di copiarlo e conservarlo in un luogo sicuro.\r\n\r\nL'inserimento di questi dati \u00E8 una tantum, se vorrai modificarli in seguito contatta l'amministratore del sistema.\r\n\r\nGrazie per aver scelto Librarium");
			contentPanel.add(txtpnBenvenutoInLibrarium, "cell 0 3 3 1,grow");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (codeTextField.getText().isEmpty() || textField.getText().isEmpty())
							MessageBoxes.alert("Attenzione", "Riempi tutti i campi");
						else {
							if (codeTextField.getText().length()<4)
								MessageBoxes.alert("Attenzione", "Il Codice Amministrativo deve essere di almeno 4 caratteri");
							else {
								DatiLibreriaDao.getInstance().inserisciDati(codeTextField.getText(), textField.getText());
								MainActivity.initialize();
								dispose();
							}
						}
						
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MainActivity.abort();		
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
