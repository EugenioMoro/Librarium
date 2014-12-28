package it.unisalento.view.Dialogs;

import it.unisalento.DataAccessObjects.LogInDAO;
import it.unisalento.business.Session;
import it.unisalento.business.UserManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class CambiaPasswordDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField vecchiaPasswordField;
	private JPasswordField nuovaPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiaPasswordDialog dialog = new CambiaPasswordDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiaPasswordDialog() {
		setBounds(100, 100, 450, 141);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][][][]"));
		{
			JLabel lblVecchiaPassword = new JLabel("Vecchia Password:");
			contentPanel.add(lblVecchiaPassword, "cell 0 1");
		}
		{
			vecchiaPasswordField = new JPasswordField();
			contentPanel.add(vecchiaPasswordField, "cell 2 1,growx");
		}
		{
			JLabel lblNuovaPassword = new JLabel("Nuova Password:");
			contentPanel.add(lblNuovaPassword, "cell 0 2");
		}
		{
			nuovaPasswordField = new JPasswordField();
			contentPanel.add(nuovaPasswordField, "cell 2 2,growx");
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
						if (cambia(new String(nuovaPasswordField.getPassword()), new String(vecchiaPasswordField.getPassword()))){
							MessageBoxes.alert("Attenzione", "Password Aggiornata");
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
	private boolean cambia(String password, String old){
		String ErrorMessage="";
		
		if (!Session.currentSession().getC().getPassword().equals(old)) ErrorMessage=ErrorMessage+"La vecchia password non è corretta\n";
		if (!UserManager.checkOnString(password)) ErrorMessage=ErrorMessage+"Password: caratteri non validi";
		if (password.isEmpty()) ErrorMessage=ErrorMessage+"Password Obbligatoria";
		if (!password.isEmpty() && password.length()<8 || password.length()>16) ErrorMessage=ErrorMessage+"Password deve essere compresa tra 8 e 16 caratteri";
		
		if(!ErrorMessage.equals("")){
			MessageBoxes.alert("Attenzione", ErrorMessage);
			return false;
		} else{
			LogInDAO.getInstance().cambiaPassword(Session.currentSession().getC(), password);
			return true;
		}
	
	}
}
