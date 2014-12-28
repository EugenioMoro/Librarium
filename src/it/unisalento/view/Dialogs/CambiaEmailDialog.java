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

public class CambiaEmailDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiaEmailDialog dialog = new CambiaEmailDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiaEmailDialog() {
		setBounds(100, 100, 450, 117);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[]"));
		{
			JLabel lblNuovaEmail = new JLabel("Nuova Email: ");
			contentPanel.add(lblNuovaEmail, "cell 0 0");
		}
		{
			emailField = new JTextField();
			contentPanel.add(emailField, "cell 2 0,growx");
			emailField.setColumns(10);
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
						if (cambia(emailField.getText())){
							MessageBoxes.alert("Attenzione", "Email modificata");
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

	private boolean cambia(String email){
		String ErrorMessage="";

		int dotCount=0;
		int atCount=0;

		//if (!checkOnString(nome)) ErrorMessage="Nome: caratteri non validi ";
		if (email.isEmpty()) ErrorMessage="Inserisci l'email";
		else {
			for (int i=0; i<email.length(); i++){
				if (!Character.isLetter(email.charAt(i))){
					if (!Character.isDigit(email.charAt(i))){
						if (email.charAt(i)=='.')
						{
							dotCount++;
							continue;
						}
						if (email.charAt(i)=='@'){
							atCount++;
							continue;
						} else {
							ErrorMessage="Email non valida";
							break;
						}
					}
				}

			}
			if (ErrorMessage.equals("")){
				if (atCount!=1 || dotCount==0) ErrorMessage="Email non valida";
			}
		}
		if(!ErrorMessage.equals("")){
			MessageBoxes.alert("Attenzione", ErrorMessage);
			return false;
		}
		LogInDAO.getInstance().cambiaEmail(Session.currentSession().getC(), email);
		return true;

	}
	
}
