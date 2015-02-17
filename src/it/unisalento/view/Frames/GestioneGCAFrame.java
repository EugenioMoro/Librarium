package it.unisalento.view.Frames;

import it.unisalento.listeners.GestioneGCAListener;
import it.unisalento.view.Models.AutoriTableModel;
import it.unisalento.view.Models.SearchCaseComboModel;
import it.unisalento.view.Models.SearchGenereComboModel;
import it.unisalento.view.Panels.AutoriJpan;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class GestioneGCAFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JComboBox<String> genereComboBox;
	private JComboBox<String> casaComboBox;
	private JScrollPane scrollPane;
	private static AutoriJpan apan=new AutoriJpan(AutoriTableModel.GESTIONEOPT, false);
	private GestioneGCAListener l;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneGCAFrame frame = new GestioneGCAFrame();
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
	public GestioneGCAFrame() {
		setTitle("Gestisci Generi Autori Case");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][grow][]"));
		l = new GestioneGCAListener(this);
		
		JPanel generePanel = new JPanel();
		generePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(generePanel, "cell 0 0,grow");
		generePanel.setLayout(new MigLayout("", "[][][grow][][]", "[][]"));
		
		JLabel lblGenere = new JLabel("Genere:");
		generePanel.add(lblGenere, "cell 0 0");
		
		genereComboBox = new JComboBox<String>(new SearchGenereComboModel());
		generePanel.add(genereComboBox, "cell 2 0,growx");
		
		JButton btnModificaG = new JButton("Modifica");
		generePanel.add(btnModificaG, "flowx,cell 3 0");
		btnModificaG.setActionCommand(GestioneGCAListener.MODIFICAGOPT);
		btnModificaG.addActionListener(l);
		
		JButton btnNuovoGenere = new JButton("Nuovo Genere");
		generePanel.add(btnNuovoGenere, "cell 4 0,growx");
		btnNuovoGenere.setActionCommand(GestioneGCAListener.NUOVOGENEREOPT);
		btnNuovoGenere.addActionListener(l);
		
		
		JLabel lblCasaEditrice = new JLabel("Casa Editrice:");
		generePanel.add(lblCasaEditrice, "cell 0 1");
		
		casaComboBox = new JComboBox<String>(new SearchCaseComboModel());
		generePanel.add(casaComboBox, "cell 2 1,growx");
		
		JButton btnModificaC = new JButton("Modifica");
		generePanel.add(btnModificaC, "flowx,cell 3 1");
		btnModificaC.setActionCommand(GestioneGCAListener.MODIFICACOPT);
		btnModificaC.addActionListener(l);
		
		JButton btnNuovaCasaEditrice = new JButton("Nuova Casa Editrice");
		generePanel.add(btnNuovaCasaEditrice, "cell 4 1,growx");
		btnNuovaCasaEditrice.setActionCommand(GestioneGCAListener.NUOVACASAOPT);
		btnNuovaCasaEditrice.addActionListener(l);
		
		JButton btnEliminaC = new JButton("Elimina");
		generePanel.add(btnEliminaC, "cell 3 1");
		btnEliminaC.setActionCommand(GestioneGCAListener.ELIMINACASAOPT);
		btnEliminaC.addActionListener(l);
		
		JButton btnEliminaG = new JButton("Elimina");
		generePanel.add(btnEliminaG, "cell 3 0");
		btnEliminaG.setActionCommand(GestioneGCAListener.ELIMINAGENEREOPT);
		btnEliminaG.addActionListener(l);
		
		
		JLabel lblAutori = new JLabel("Autori:");
		contentPane.add(lblAutori, "cell 0 1");
		
		
		scrollPane = apan.getPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(scrollPane, "cell 0 2,grow");
		
		JButton btnNuovoAutore = new JButton("Nuovo Autore");
		contentPane.add(btnNuovoAutore, "cell 0 3");
		btnNuovoAutore.setActionCommand(GestioneGCAListener.NUOVOAUTOREOPT);
		btnNuovoAutore.addActionListener(l);
	}
	
	public void refresh(){
		apan.refresh();
		genereComboBox.setSelectedIndex(-1);
		casaComboBox.setSelectedIndex(-1);
	}

	public JComboBox<String> getGenereComboBox() {
		return genereComboBox;
	}

	public JComboBox<String> getCasaComboBox() {
		return casaComboBox;
	}

}
