package it.unisalento.view.Frames;

import it.unisalento.view.Models.LibriTableModel;
import it.unisalento.view.Panels.LibriJPanJTab;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class OspiteView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OspiteView frame = new OspiteView();
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
	public OspiteView() {
		setTitle("Catalogo Libri - Ospite");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 300);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[63.00][11.00][grow]"));
		
		LibriJPanJTab tabPanel=new LibriJPanJTab(LibriTableModel.CLIENTEOPT);
		
		JPanel SearchPanel = new RicercaJPanel();
		getContentPane().add(SearchPanel, "cell 0 0,grow");
		
		JScrollPane scrollPane = tabPanel.getPanel();
		getContentPane().add(scrollPane, "cell 0 2,grow");
		
		
		
		
		
		

	}

}
