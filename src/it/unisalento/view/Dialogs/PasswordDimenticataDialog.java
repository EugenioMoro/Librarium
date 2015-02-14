package it.unisalento.view.Dialogs;

import it.unisalento.DataAccessObjects.LogInDAO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PasswordDimenticataDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PasswordDimenticataDialog dialog = new PasswordDimenticataDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PasswordDimenticataDialog() {
		setBounds(100, 100, 378, 212);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(20, 89, 184, 25);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					/*	if (Integer.parseInt(DbConnection.getInstance().eseguiQuery("SELECT count(*) FROM cliente WHERE email='"+textField.getText()+"' ").get(0)[0])==1)
							MessageBoxes.alert("Attenzione!", "L'email non è registrata."); */
						try {
							LogInDAO.getInstance().passDimenticata(textField.getText());
						} catch (AddressException e1) {
							MessageBoxes.alert("Attenzione!", "L'email non è registrata.");
							e1.printStackTrace();
						} catch (MessagingException e1) {
							MessageBoxes.alert("Attenzione!", "Impossibile inviare messaggio.");
							e1.printStackTrace();
						}
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();			
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
