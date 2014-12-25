package it.unisalento.view.Frames;

import it.unisalento.business.Session;
import it.unisalento.view.Models.LibriTableModel;
import it.unisalento.view.Models.SearchTableInside;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ClienteView extends JFrame implements SearchTableInside {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteView frame = new ClienteView();
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
	public ClienteView() {
		setTitle("Librarium - "+Session.currentSession().getC().getNome()+" "+Session.currentSession().getC().getCognome()+"");
		defineColumnButton();
		tab=new JTable(new LibriTableModel());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{506, 0};
		gbl_contentPane.rowHeights = new int[]{91, 95, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new MigLayout("", "[][][][][][][][96.00]", "[][][][]"));
		
		JLabel lblBentornato = new JLabel("Bentornato "+Session.currentSession().getC().getNome()+" "+Session.currentSession().getC().getCognome());
		panel.add(lblBentornato, "cell 0 0 1 2");
		
		JButton btnIMieiAcquisti = new JButton("I miei acquisti");
		panel.add(btnIMieiAcquisti, "cell 5 1,growx");
		
		JButton btnIMieiDati = new JButton("I miei dati");
		panel.add(btnIMieiDati, "cell 7 1,growx");
		
		JLabel lblIlTuoUltimo = new JLabel("Il tuo ultimo accesso: "+Session.currentSession().getC().getData_ultimo_accesso()+"");
		panel.add(lblIlTuoUltimo, "cell 0 2 1 2");
		
		JButton btnLeMieRichieste = new JButton("Le mie richieste");
		panel.add(btnLeMieRichieste, "cell 5 3,growx");
		
		JButton btnLogOut = new JButton("Log Out");
		panel.add(btnLogOut, "cell 7 3,growx");
		
		JPanel panel_1 = new RicercaJPanel(tab);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		
		JScrollPane scrollPane = new JScrollPane(tab);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
	}

	@Override
	public JTable getSearchTable() {
		return this.tab;
	}

	@Override
	public void defineColumnButton() {
		
	}

}
