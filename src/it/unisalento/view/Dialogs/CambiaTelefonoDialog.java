package it.unisalento.view.Dialogs;

import it.unisalento.DataAccessObjects.LogInDAO;
import it.unisalento.business.Session;
import it.unisalento.listeners.ClienteViewListener;

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

public class CambiaTelefonoDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField telefonoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiaTelefonoDialog dialog = new CambiaTelefonoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiaTelefonoDialog() {
		setBounds(100, 100, 450, 114);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[]"));
		{
			JLabel lblNuovoTelefono = new JLabel("Nuovo Telefono: ");
			contentPanel.add(lblNuovoTelefono, "cell 0 0");
		}
		{
			telefonoField = new JTextField();
			contentPanel.add(telefonoField, "cell 2 0,growx");
			telefonoField.setColumns(10);
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
						if (cambia(telefonoField.getText())){
							MessageBoxes.alert("Attenzione", "Telefono modificato");
							ClienteViewListener.refreshDatiFrame();
							dispose();
						}
						
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
			}
		}
	}

	private boolean cambia(String tel){
		String ErrorMessage="";
		
		if (tel.isEmpty()) ErrorMessage=ErrorMessage+'\n'+"Numero di telefono obbligatorio";
		else {
			if (tel.length()==10){
				boolean ok=true;
				for (int i=0; i<tel.length(); i++){
					if (!Character.isDigit(tel.charAt(i))){
						ok=false;
						break;
					}
				}
				if (!ok) ErrorMessage=ErrorMessage+'\n'+"Telefono non valido";
			}
			else ErrorMessage=ErrorMessage+'\n'+"Telefono non valido";
		}
		
		if(!ErrorMessage.equals("")){
			MessageBoxes.alert("Attenzione", ErrorMessage);
			return false;
		}
		LogInDAO.getInstance().cambiaTelefono(Session.currentSession().getC(), tel);
		return true;
	}
	
}
