package it.unisalento.view.Frames;

import it.unisalento.Model.Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DettagliClienteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DettagliClienteFrame frame = new DettagliClienteFrame(new Cliente());
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
	public DettagliClienteFrame(Cliente c) {
		setTitle("Dettagli Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 271, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nome = new JLabel("Nome: "+c.getNome());
		nome.setBounds(10, 11, 235, 14);
		contentPane.add(nome);
		
		JLabel cognome = new JLabel("Cognome: "+c.getCognome());
		cognome.setBounds(10, 36, 235, 14);
		contentPane.add(cognome);
		
		JLabel email = new JLabel("Email: "+c.getEmail());
		email.setBounds(10, 61, 235, 14);
		contentPane.add(email);
		
		JLabel lblTel = new JLabel("Tel."+c.getTelefono());
		lblTel.setBounds(10, 86, 235, 14);
		contentPane.add(lblTel);
	}

}
