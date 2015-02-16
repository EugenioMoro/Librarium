package it.unisalento.view.Frames;
import it.unisalento.business.RichiesteManager;
import it.unisalento.business.Session;
import it.unisalento.listeners.ScaffaliViewListener;
import it.unisalento.view.Models.LibriTableModel;
import it.unisalento.view.Panels.LibriJPanJTab;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


public class ScaffaliView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LibriJPanJTab libriSrollPane = new LibriJPanJTab(LibriTableModel.SCAFFALIOPT);
	private RicercaJPanel searchPanel = new RicercaJPanel();
	private ScaffaliViewListener l= new ScaffaliViewListener();
	static JLabel lblNuoveRichieste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScaffaliView frame = new ScaffaliView();
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
	public ScaffaliView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(buttonPane, "cell 0 0,grow");
		buttonPane.setLayout(new MigLayout("", "[][][][][][][]", "[]"));
		
		JLabel lblBenvenuto = new JLabel("Benvenuto:"+Session.currentSession().getU().getNome()+" "+Session.currentSession().getU().getCognome());
		buttonPane.add(lblBenvenuto, "flowy,cell 0 0");
		
		JLabel lblIlTuoUltimo = new JLabel("Il tuo ultimo accesso: "+Session.currentSession().getU().getData_ultimo_accesso());
		buttonPane.add(lblIlTuoUltimo, "cell 0 0");
		
		JButton btnGestisciRichieste = new JButton("Gestisci Richieste");
		buttonPane.add(btnGestisciRichieste, "flowy,cell 3 0,growx");
		btnGestisciRichieste.setActionCommand(ScaffaliViewListener.RICHIESTEOPT);
		btnGestisciRichieste.addActionListener(l);
		
		JButton btnNuovoLibro = new JButton("Nuovo Libro");
		buttonPane.add(btnNuovoLibro, "flowy,cell 4 0");
		btnNuovoLibro.setActionCommand(ScaffaliViewListener.NUOVOLIBROOPT);
		btnNuovoLibro.addActionListener(l);
		
		JButton btnLogout = new JButton("Logout");
		buttonPane.add(btnLogout, "cell 4 0,growx");
		btnLogout.setActionCommand(ScaffaliViewListener.LOGOUTOPT);
		btnLogout.addActionListener(l);
		
		JButton btnGestisciAutoricase = new JButton("Gestisci Autori/Case");
		buttonPane.add(btnGestisciAutoricase, "cell 3 0");
		btnGestisciAutoricase.setActionCommand(ScaffaliViewListener.AUTORICASEOPT);
		
		RichiesteManager.getInstance().setStorico();
		lblNuoveRichieste = new JLabel("Nuove richieste: "+RichiesteManager.getInstance().getNuoveRichesteCount());
		buttonPane.add(lblNuoveRichieste, "cell 6 0");
		btnGestisciAutoricase.addActionListener(l);
		
		
		
		searchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(searchPanel, "cell 0 1,grow");
		
		JScrollPane libriScrollPane = libriSrollPane.getPanel();
		contentPane.add(libriScrollPane, "cell 0 2,grow");
	}
	
	public static void refreshLabel(){
		lblNuoveRichieste.setText("Nuove richieste: "+RichiesteManager.getInstance().getNuoveRichesteCount());
	}

}
