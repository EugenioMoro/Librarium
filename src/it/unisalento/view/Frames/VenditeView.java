package it.unisalento.view.Frames;

import it.unisalento.business.CarrelloManager;
import it.unisalento.business.Session;
import it.unisalento.listeners.VenditeViewListener;
import it.unisalento.view.Models.LibriTableModel;
import it.unisalento.view.Panels.CarrelloJPanJTab;
import it.unisalento.view.Panels.LibriJPanJTab;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class VenditeView extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private ActionListener listener=new VenditeViewListener();
	
	private LibriJPanJTab libriPanel=new LibriJPanJTab(LibriTableModel.VENDITEOPT);
	private CarrelloJPanJTab carrelloPanel= new CarrelloJPanJTab();
	private static JLabel lblTotale = new JLabel("Totale: "+ CarrelloManager.getInstance().getAcquisto().getIncasso());
	private static JLabel lblLibri = new JLabel("Libri: "+CarrelloManager.getInstance().getAcquisto().getLibri().size());
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VenditeView frame = new VenditeView();
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
	public VenditeView() {
		setTitle("Librarium - Vendite");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{611, 0};
		gbl_contentPane.rowHeights = new int[]{91, 95, 261, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel toolPanel = new JPanel();
		toolPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_toolPanel = new GridBagConstraints();
		gbc_toolPanel.insets = new Insets(0, 0, 5, 0);
		gbc_toolPanel.fill = GridBagConstraints.BOTH;
		gbc_toolPanel.gridx = 0;
		gbc_toolPanel.gridy = 0;
		contentPane.add(toolPanel, gbc_toolPanel);
		toolPanel.setLayout(new MigLayout("", "[][][][][112.00,grow][][][96.00]", "[][][][]"));
		
		JLabel lblBentornato = new JLabel("Bentornato "+Session.currentSession().getU().getNome()+" "+Session.currentSession().getU().getCognome());
		toolPanel.add(lblBentornato, "cell 0 0 1 2");
		
		JButton btnStorico = new JButton("Storico acquisti");
		toolPanel.add(btnStorico, "cell 2 1,growx");
		btnStorico.addActionListener(listener);
		btnStorico.setActionCommand(VenditeViewListener.STORICOOPT);

		JButton btnVendiCarrello = new JButton("Vendi Carrello");
		toolPanel.add(btnVendiCarrello, "cell 4 1,growx");
		btnVendiCarrello.setActionCommand(VenditeViewListener.VENDIOPT);
		btnVendiCarrello.addActionListener(listener);


		toolPanel.add(lblLibri, "cell 7 1");

		JLabel lblIlTuoUltimo = new JLabel("Il tuo ultimo accesso: "+Session.currentSession().getU().getData_ultimo_accesso()+"");
		toolPanel.add(lblIlTuoUltimo, "cell 0 2 1 2");


		JButton btnLogOut = new JButton("Log Out");
		toolPanel.add(btnLogOut, "cell 2 3,growx");
		btnLogOut.setActionCommand(VenditeViewListener.LOGOUTOPT);
		
		JButton btnSvuotaCarrello = new JButton("Svuota Carrello");
		toolPanel.add(btnSvuotaCarrello, "cell 4 3,growx");
		btnSvuotaCarrello.setActionCommand(VenditeViewListener.SVUOTAOPT);
		btnSvuotaCarrello.addActionListener(listener);
		
		toolPanel.add(lblTotale, "cell 7 3");
		btnLogOut.addActionListener(listener);


		JPanel ricercaPanel = new RicercaJPanel();
		ricercaPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_ricercaPanel = new GridBagConstraints();
		gbc_ricercaPanel.insets = new Insets(0, 0, 5, 0);
		gbc_ricercaPanel.fill = GridBagConstraints.BOTH;
		gbc_ricercaPanel.gridx = 0;
		gbc_ricercaPanel.gridy = 1;
		contentPane.add(ricercaPanel, gbc_ricercaPanel);
		
		JScrollPane libriScrollPane = libriPanel.getPanel();
		GridBagConstraints gbc_libriScrollPane = new GridBagConstraints();
		gbc_libriScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_libriScrollPane.fill = GridBagConstraints.BOTH;
		gbc_libriScrollPane.gridx = 0;
		gbc_libriScrollPane.gridy = 2;
		contentPane.add(libriScrollPane, gbc_libriScrollPane);
		
		JLabel lblCarrello = new JLabel("Carrello");
		GridBagConstraints gbc_lblCarrello = new GridBagConstraints();
		gbc_lblCarrello.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarrello.gridx = 0;
		gbc_lblCarrello.gridy = 3;
		contentPane.add(lblCarrello, gbc_lblCarrello);
		
		JScrollPane carrelloScrollPane = carrelloPanel.getPanel();
		GridBagConstraints gbc_carrelloScrollPane = new GridBagConstraints();
		gbc_carrelloScrollPane.fill = GridBagConstraints.BOTH;
		gbc_carrelloScrollPane.gridx = 0;
		gbc_carrelloScrollPane.gridy = 4;
		contentPane.add(carrelloScrollPane, gbc_carrelloScrollPane);
		
		
	}
	
	public static void aggiornaLabels(){
		lblTotale.setText("Totale: "+ CarrelloManager.getInstance().getAcquisto().getIncasso());
		lblLibri.setText("Libri: "+CarrelloManager.getInstance().getAcquisto().getLibri().size());
	}

}
