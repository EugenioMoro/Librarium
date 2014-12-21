package it.unisalento.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;






import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import sun.util.calendar.CalendarDate;

public class RegCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCognome;
	private JTextField txtUsername;
	private ButtonGroup bg=new ButtonGroup();
	
	
	
			
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		
		txtCognome = new JTextField();
		txtCognome.setText("Email");
		txtCognome.setBounds(10, 42, 86, 20);
		centrePanel.add(txtCognome);
		txtCognome.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(10, 73, 86, 20);
		centrePanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblObbligatorio = new JLabel("Data di nascita, obbligatoria\r\n");
		lblObbligatorio.setBounds(148, 17, 134, 14);
		centrePanel.add(lblObbligatorio);
		
		JLabel lblObbligatorio_1 = new JLabel("Obbligatorio, verr\u00E0 usato per recuperare la password");
		lblObbligatorio_1.setBounds(126, 45, 262, 14);
		centrePanel.add(lblObbligatorio_1);
		
		JLabel lblCaratteriSolo = new JLabel("obbligatorio");
		lblCaratteriSolo.setBounds(126, 76, 262, 14);
		centrePanel.add(lblCaratteriSolo);
		
		JLabel lblCaratteriSolo_1 = new JLabel("Sesso");
		lblCaratteriSolo_1.setBounds(126, 107, 173, 14);
		centrePanel.add(lblCaratteriSolo_1);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(10, 103, 33, 23);
		centrePanel.add(rdbtnM);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(63, 103, 33, 23);
		centrePanel.add(rdbtnF);
		
		//button group
		bg.add(rdbtnM);
		bg.add(rdbtnF);
		
		
		//JDatePicker codice
		UtilDateModel model=new UtilDateModel();
		JDatePanelImpl panel=new JDatePanelImpl(model);
		JDatePickerImpl picker=new JDatePickerImpl(panel);
		picker.setBounds(10, 11, 114, 20);
		centrePanel.add(picker);
				
//		JComboBox comboBox = new JComboBox();
//		comboBox.setBounds(10, 11, 86, 20);
//		centrePanel.add(comboBox);
		
		
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.GRAY);
		southPanel.setBounds(10, 207, 414, 43);
		contentPane.add(southPanel);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRegistrami = new JButton("Registrami");
		southPanel.add(btnRegistrami);
	}
	private SpinnerDateModel yearSpinnerModel(){
		SpinnerDateModel model;
		Calendar calInstance=Calendar.getInstance();
		
		Date now=calInstance.getTime();
		
		calInstance.add(Calendar.YEAR, -100);
		Date startDate=calInstance.getTime();
		
		calInstance.add(Calendar.YEAR, 200);
		Date endDate=calInstance.getTime();
		
		model=new SpinnerDateModel(now, startDate, endDate, Calendar.YEAR);
		
		return model;
	}
	
	
	private SpinnerDateModel monthSpinnerModel(){
		SpinnerDateModel model;
		
		model=new SpinnerDateModel(Calendar.getInstance().getTime(), null, null, Calendar.MONTH);
		
		return model;
	}
	
	private SpinnerDateModel daySpinnerModel(){
		SpinnerDateModel model;
		
		model=new SpinnerDateModel(Calendar.getInstance().getTime(), null, null, Calendar.DAY_OF_MONTH);
		
		return model;
	}
	}
