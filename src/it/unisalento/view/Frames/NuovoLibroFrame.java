package it.unisalento.view.Frames;

import it.unisalento.business.NuovoLibroManager;
import it.unisalento.listeners.NuovoLibroListener;
import it.unisalento.view.Models.SearchCaseComboModel;
import it.unisalento.view.Models.SearchGenereComboModel;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class NuovoLibroFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titoloTxtField;
	private JTextField costoTxtField;
	private JTextField dispTextField;
	private JTextField ISBNTextField;
	private JTextField pagineTextField;
	
	private JComboBox<String> genereComboBox;
	private JComboBox<String> casaComboBox;
	
	private NuovoLibroListener l;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuovoLibroFrame frame = new NuovoLibroFrame();
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
	public NuovoLibroFrame() {
		l=new NuovoLibroListener(this);
		NuovoLibroManager.getInstance().inizializza();
		
		setTitle("Nuovo Libro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][][][][][][][][]"));
		
		JLabel lblTitolo = new JLabel("Titolo: ");
		contentPane.add(lblTitolo, "cell 0 0");
		
		titoloTxtField = new JTextField();
		contentPane.add(titoloTxtField, "cell 2 0,growx");
		titoloTxtField.setColumns(10);
		
		JLabel lblGenere = new JLabel("Genere");
		contentPane.add(lblGenere, "cell 0 1");
		
		genereComboBox = new JComboBox<String>(new SearchGenereComboModel());
		contentPane.add(genereComboBox, "cell 2 1,growx");
		
		JLabel lblCasaEditrice = new JLabel("Casa Editrice:");
		contentPane.add(lblCasaEditrice, "cell 0 2");
		
		casaComboBox = new JComboBox<String>(new SearchCaseComboModel());
		contentPane.add(casaComboBox, "cell 2 2,growx");
		
		JLabel lblCosto = new JLabel("Costo:");
		contentPane.add(lblCosto, "cell 0 3");
		
		costoTxtField = new JTextField();
		contentPane.add(costoTxtField, "cell 2 3,growx");
		costoTxtField.setColumns(10);
		
		JLabel lblDispIniziale = new JLabel("Disp. Iniziale:");
		contentPane.add(lblDispIniziale, "cell 0 4");
		
		dispTextField = new JTextField();
		contentPane.add(dispTextField, "cell 2 4,growx");
		dispTextField.setColumns(10);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		contentPane.add(lblIsbn, "cell 0 5");
		
		ISBNTextField = new JTextField();
		contentPane.add(ISBNTextField, "cell 2 5,growx");
		ISBNTextField.setColumns(10);
		
		JLabel lblPagine = new JLabel("Pagine:");
		contentPane.add(lblPagine, "cell 0 6");
		
		pagineTextField = new JTextField();
		contentPane.add(pagineTextField, "cell 2 6,growx");
		pagineTextField.setColumns(10);
		
		JButton btnAutori = new JButton("Autori");
		contentPane.add(btnAutori, "cell 0 7,growx");
		btnAutori.setActionCommand(NuovoLibroListener.AUTORIOPT);
		btnAutori.addActionListener(l);
		
		JButton btnSalva = new JButton("Salva");
		contentPane.add(btnSalva, "cell 2 8,alignx right");
		btnSalva.setActionCommand(NuovoLibroListener.SALVAOPT);
		btnSalva.addActionListener(l);
	}

	public JTextField getTitoloTxtField() {
		return titoloTxtField;
	}

	public JTextField getCostoTxtField() {
		return costoTxtField;
	}

	public JTextField getDispTextField() {
		return dispTextField;
	}

	public JTextField getISBNTextField() {
		return ISBNTextField;
	}

	public JTextField getPagineTextField() {
		return pagineTextField;
	}

	public JComboBox<String> getGenereComboBox() {
		return genereComboBox;
	}

	public JComboBox<String> getCasaComboBox() {
		return casaComboBox;
	}

}
