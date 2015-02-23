package it.unisalento.view.Frames;

import it.unisalento.listeners.RegistraListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

public class RegCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtEmail;
	private static JTextField txtTelefono;
	private static ButtonGroup bg=new ButtonGroup();
	private static JDatePickerImpl picker;
	private ActionListener listener= new RegistraListener();
	
	
	
			
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegCliente frame = new RegCliente();
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
	public RegCliente() {
		setTitle("Registrazione");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 281, 242);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.GRAY);
		northPanel.setBounds(10, 11, 414, 37);
		contentPane.add(northPanel);
		northPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInserisciITuoi = new JLabel("Ci siamo quasi:");
		lblInserisciITuoi.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblInserisciITuoi.setHorizontalAlignment(SwingConstants.CENTER);
		northPanel.add(lblInserisciITuoi, BorderLayout.CENTER);
		
		JPanel centrePanel = new JPanel();
		centrePanel.setBackground(Color.GRAY);
		centrePanel.setBounds(10, 59, 414, 137);
		contentPane.add(centrePanel);
		centrePanel.setLayout(null);
		
		
		txtEmail = new JTextField();
		txtEmail.setText("Email");
		txtEmail.setBounds(10, 42, 86, 20);
		centrePanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setText("Telefono");
		txtTelefono.setBounds(10, 73, 86, 20);
		centrePanel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblObbligatorio = new JLabel("Data di nascita, obbligatoria\r\n");
		lblObbligatorio.setBounds(148, 17, 134, 14);
		centrePanel.add(lblObbligatorio);
		
		JLabel lblObbligatorio_1 = new JLabel("Obbligatorio, verr\u00E0 usato per recuperare la password");
		lblObbligatorio_1.setBounds(126, 45, 262, 14);
		centrePanel.add(lblObbligatorio_1);
		
		JLabel lblCaratteriSolo = new JLabel("Obbligatorio");
		lblCaratteriSolo.setBounds(126, 76, 262, 14);
		centrePanel.add(lblCaratteriSolo);
		
		JLabel lblCaratteriSolo_1 = new JLabel("Sesso");
		lblCaratteriSolo_1.setBounds(126, 107, 173, 14);
		centrePanel.add(lblCaratteriSolo_1);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(10, 103, 33, 23);
		centrePanel.add(rdbtnM);
		rdbtnM.addActionListener(listener);
		rdbtnM.setActionCommand(RegistraListener.SESSOMOPT);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(63, 103, 33, 23);
		centrePanel.add(rdbtnF);
		rdbtnF.addActionListener(listener);
		rdbtnF.setActionCommand(RegistraListener.SESSOFOPT);
		
		//button group
		bg.add(rdbtnM);
		bg.add(rdbtnF);
		
		
		//JDatePicker codice
		SqlDateModel model=new SqlDateModel();
		JDatePanelImpl panel=new JDatePanelImpl(model);
		picker=new JDatePickerImpl(panel);
		picker.setBounds(10, 11, 114, 20);
		centrePanel.add(picker);
		
			
			
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.GRAY);
		southPanel.setBounds(10, 207, 414, 43);
		contentPane.add(southPanel);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRegistrami = new JButton("Registrami");
		southPanel.add(btnRegistrami);
		btnRegistrami.addActionListener(listener);
		btnRegistrami.setActionCommand(RegistraListener.REGCLIENTEOPT);
	
		pack();
	}

	public static JTextField getTxtEmail() {
		return txtEmail;
	}

	public static JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public static JDatePickerImpl getPicker() {
		return picker;
	}

	public static ButtonGroup getBg() {
		return bg;
	}
	
}
