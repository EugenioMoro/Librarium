package it.unisalento.view.Dialogs;

import it.unisalento.DataAccessObjects.AcquistoDAO;
import it.unisalento.DataAccessObjects.TesseraDAO;
import it.unisalento.business.CarrelloManager;
import it.unisalento.view.Frames.VenditeView;
import it.unisalento.view.Panels.CarrelloJPanJTab;

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

public class TesseraDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TesseraDialog dialog = new TesseraDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TesseraDialog() {
		setTitle("Tessera Cliente");
		setBounds(100, 100, 329, 128);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][]"));
		{
			JLabel lblCodiceTessera = new JLabel("Codice tessera:");
			contentPanel.add(lblCodiceTessera, "cell 0 0");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 0 1,growx");
			textField.setColumns(10);
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
						int id = TesseraDAO.getInstance().idClienteTessera(TesseraDialog.getTextField().getText());
						if (id>=0){
							CarrelloManager.getInstance().getAcquisto().setCliente_id(id);
							AcquistoDAO.getInstance().inserisci(CarrelloManager.getInstance().getAcquisto());
							CarrelloManager.getInstance().svuota();
							CarrelloJPanJTab.refresh();
							VenditeView.aggiornaLabels();
							dispose();
							MessageBoxes.alert("Acquisto", "Acquisto Effettuato");
						} else {
							MessageBoxes.alert("Attenzione", "Tessera inesistente");
						}
						
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});			}
		}
	pack();
	}

	public static JTextField getTextField() {
		return textField;
	}

}
