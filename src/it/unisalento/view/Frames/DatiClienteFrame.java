package it.unisalento.view.Frames;

import it.unisalento.DataAccessObjects.TesseraDAO;
import it.unisalento.Model.Cliente;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.CambiaEmailDialog;
import it.unisalento.view.Dialogs.CambiaPasswordDialog;
import it.unisalento.view.Dialogs.CambiaTelefonoDialog;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class DatiClienteFrame extends JFrame {

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
					DatiClienteFrame frame = new DatiClienteFrame();
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
	public DatiClienteFrame() {
		setTitle("I tuoi dati");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		Cliente c=Session.currentSession().getC();
		
		JLabel lblTessera = new JLabel("Tessera: "+TesseraDAO.getInstance().caricaTessera(Session.currentSession().getC()).getCodice());
		contentPane.add(lblTessera, "2, 2");
		
		JLabel lblNome = new JLabel("Nome: "+c.getNome());
		contentPane.add(lblNome, "2, 4");
		
		JLabel lblCognome = new JLabel("Cognome: "+c.getCognome());
		contentPane.add(lblCognome, "2, 6");
		
		JLabel lblDataDiNascita = new JLabel("Data di nascita: "+c.getData_nascita());
		contentPane.add(lblDataDiNascita, "2, 8");
		
		JLabel lblEmail = new JLabel("Email: "+ c.getEmail());
		contentPane.add(lblEmail, "2, 10");

		JLabel lblTelefono = new JLabel("Telefono: "+c.getTelefono());
		contentPane.add(lblTelefono, "2, 12");


		JButton btnCambiaPassword = new JButton("Cambia Password");
		contentPane.add(btnCambiaPassword, "2, 14");
		btnCambiaPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog=new CambiaPasswordDialog();
				dialog.setVisible(true);
			}
		});

		JButton btnCambiaEmail = new JButton("Cambia Email");
		contentPane.add(btnCambiaEmail, "2, 16");
		btnCambiaEmail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog =new CambiaEmailDialog();
				dialog.setVisible(true);
			}
		});

		JButton btnCambiaTelefono = new JButton("Cambia Telefono");
		contentPane.add(btnCambiaTelefono, "2, 18");
		btnCambiaTelefono.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog =new CambiaTelefonoDialog();
				dialog.setVisible(true);
				
			}
		});
	}

}
