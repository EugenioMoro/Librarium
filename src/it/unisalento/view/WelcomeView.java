package it.unisalento.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.GridLayout;

public class WelcomeView extends JFrame {
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	public WelcomeView() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setForeground(new Color(0, 206, 209));
		getContentPane().setForeground(Color.LIGHT_GRAY);
		getContentPane().setLayout(new MigLayout("", "[123px,grow][grow][][][][grow]", "[36px,grow][][grow][grow][][][][][grow]"));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.GRAY);
		getContentPane().add(upperPanel, "cell 0 0 6 1,grow");
		upperPanel.setLayout(new MigLayout("", "[][][][][][]", "[][][][][][]"));
		
		JLabel lblLibrarium = new JLabel("Librarium");
		lblLibrarium.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		upperPanel.add(lblLibrarium, "cell 0 0");
		
		JLabel lblBenvenuto = new JLabel("Benvenuto");
		lblBenvenuto.setFont(new Font("Times New Roman", Font.BOLD, 50));
		upperPanel.add(lblBenvenuto, "cell 3 0 3 6");
		
		JLabel lblBookStoreManager = new JLabel("Book Store Manager");
		lblBookStoreManager.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		upperPanel.add(lblBookStoreManager, "cell 0 4");
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.GRAY);
		getContentPane().add(leftPanel, "cell 0 1 1 3,grow");
		leftPanel.setLayout(new MigLayout("", "[86px,grow]", "[20px][][][][]"));
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setToolTipText("Username");
		leftPanel.add(txtUsername, "cell 0 0,growx");
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("Password");
		leftPanel.add(pwdPassword, "cell 0 1,growx");
		
		JButton btnLogIn = new JButton("Log In");
		leftPanel.add(btnLogIn, "cell 0 3");
		
		JButton btnPasswordDimenticata = new JButton("Password Dimenticata");
		leftPanel.add(btnPasswordDimenticata, "cell 0 4");
		btnPasswordDimenticata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.GRAY);
		getContentPane().add(rightPanel, "cell 1 1 5 3,grow");
		rightPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][]"));
		
		JLabel lblNuovoUtente = new JLabel("Nuovo Utente?");
		rightPanel.add(lblNuovoUtente, "cell 0 0 1 2");
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSfogliaCatalogo = new JButton("Sfoglia Catalogo");
		rightPanel.add(btnSfogliaCatalogo, "cell 0 3 4 1,grow");
		rightPanel.add(btnRegistrati, "cell 0 5 4 1,grow");
		
		JPanel lowPanel = new JPanel();
		lowPanel.setBackground(Color.GRAY);
		getContentPane().add(lowPanel, "cell 0 4 6 5,grow");
		lowPanel.setLayout(null);
		
		JLabel lblEugenioMoro = new JLabel("Eugenio Moro");
		lblEugenioMoro.setBounds(10, 0, 65, 25);
		lowPanel.add(lblEugenioMoro);
		
		JLabel lblGiuliaMarra = new JLabel("Giulia Marra");
		lblGiuliaMarra.setBounds(92, 0, 65, 25);
		lowPanel.add(lblGiuliaMarra);
		
		JLabel lblUnisalento = new JLabel("Unisalento, 2014");
		lblUnisalento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnisalento.setBounds(215, 0, 191, 25);
		lowPanel.add(lblUnisalento);
	}
}
