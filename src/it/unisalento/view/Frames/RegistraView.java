package it.unisalento.view.Frames;

import it.unisalento.business.Session;
import it.unisalento.listeners.RegistraListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RegistraView extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtNome;
	private static JTextField txtCognome;
	private static JTextField txtUsername;
	private static JPasswordField txtPassword;
	private ActionListener listener=new RegistraListener();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistraView frame = new RegistraView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistraView() {
		setTitle("Registrazione");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(this);
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.GRAY);
		northPanel.setBounds(10, 11, 414, 37);
		contentPane.add(northPanel);
		northPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInserisciITuoi = new JLabel("Inserisci i tuoi dati:");
		lblInserisciITuoi.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblInserisciITuoi.setHorizontalAlignment(SwingConstants.CENTER);
		northPanel.add(lblInserisciITuoi, BorderLayout.CENTER);
		
		JPanel centrePanel = new JPanel();
		centrePanel.setBackground(Color.GRAY);
		centrePanel.setBounds(10, 59, 414, 137);
		contentPane.add(centrePanel);
		centrePanel.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setText("Nome");
		txtNome.setBounds(10, 11, 86, 20);
		centrePanel.add(txtNome);
		txtNome.setColumns(10);
		
		txtCognome = new JTextField();
		txtCognome.setText("Cognome");
		txtCognome.setBounds(10, 42, 86, 20);
		centrePanel.add(txtCognome);
		txtCognome.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(10, 73, 86, 20);
		centrePanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setText("Password");
		txtPassword.setBounds(10, 104, 86, 20);
		centrePanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblObbligatorio = new JLabel("Obbligatorio");
		lblObbligatorio.setBounds(126, 14, 86, 14);
		centrePanel.add(lblObbligatorio);
		
		JLabel lblObbligatorio_1 = new JLabel("Obbligatorio");
		lblObbligatorio_1.setBounds(126, 45, 70, 14);
		centrePanel.add(lblObbligatorio_1);
		
		JLabel lblCaratteriSolo = new JLabel("5-16 Caratteri, solo lettere e numeri");
		lblCaratteriSolo.setBounds(126, 76, 204, 14);
		centrePanel.add(lblCaratteriSolo);
		
		JLabel lblCaratteriSolo_1 = new JLabel("8-16 Caratteri, solo lettere e numeri");
		lblCaratteriSolo_1.setBounds(126, 107, 173, 14);
		centrePanel.add(lblCaratteriSolo_1);
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.GRAY);
		southPanel.setBounds(10, 207, 414, 43);
		contentPane.add(southPanel);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSonoUn = new JLabel("Sono un:");
		lblSonoUn.setHorizontalAlignment(SwingConstants.LEFT);
		southPanel.add(lblSonoUn);
		
		JButton btnCliente = new JButton("Cliente");
		southPanel.add(btnCliente);
		btnCliente.addActionListener(listener);
		btnCliente.setActionCommand(RegistraListener.CLIENTEOPT);
		
		
		JButton btnAddettoVendite = new JButton("Addetto Vendite");
		southPanel.add(btnAddettoVendite);
		btnAddettoVendite.addActionListener(listener);
		btnAddettoVendite.setActionCommand(RegistraListener.VENDITEOPT);
		
		JButton btnAddettoScaffali = new JButton("Addetto Scaffali");
		southPanel.add(btnAddettoScaffali);
		btnAddettoScaffali.addActionListener(listener);
		btnAddettoScaffali.setActionCommand(RegistraListener.SCAFFALIOPT);
		
		
	}

	public static JTextField getTxtNome() {
		return txtNome;
	}

	public static JTextField getTxtCognome() {
		return txtCognome;
	}

	public static JTextField getTxtUsername() {
		return txtUsername;
	}

	public static JPasswordField getTxtPassword() {
		return txtPassword;
	}
	
	private void onCloseOperations(){
		Session.currentSession().DestroyC();
		Session.currentSession().DestroyU();
	}

	@Override
	public void windowActivated(java.awt.event.WindowEvent e) {

		
	}

	@Override
	public void windowClosed(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowClosing(java.awt.event.WindowEvent e) {
		onCloseOperations();
		}

	@Override
	public void windowDeactivated(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowIconified(java.awt.event.WindowEvent e) {

		
	}

	@Override
	public void windowOpened(java.awt.event.WindowEvent e) {

		
	}
}
