package it.unisalento.view.Dialogs;

import it.unisalento.DataAccessObjects.LogInDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.EmptyBorder;



public class PasswordDimenticataDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String Dialogs = null;
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
		textField.setBounds(10, 69, 291, 25);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblInserireLemailCo = new JLabel("Inserire l'indirizzo email con il quale si \u00E8 registrato:");
		lblInserireLemailCo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInserireLemailCo.setBounds(10, 11, 334, 25);
		contentPanel.add(lblInserireLemailCo);
		
		JLabel lblABreveLe = new JLabel("A breve le verr\u00E0 inviata una mail con la sua password.");
		lblABreveLe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblABreveLe.setBounds(10, 35, 324, 31);
		contentPanel.add(lblABreveLe);
		
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!LogInDAO.getInstance().CheckEmail(textField.getText()))
							
						{ 
						MessageBoxes.errore("Attenzione!", "La mail inserita non è registrata.\nRiprova oppure registrati.");
						}
						
						else	
							LogInDAO.getInstance().passDimenticata(textField.getText());
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
