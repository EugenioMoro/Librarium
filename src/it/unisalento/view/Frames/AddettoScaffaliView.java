package it.unisalento.view.Frames;

import it.unisalento.view.Models.LibriTableModel;
import it.unisalento.view.Panels.LibriJPanJTab;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class AddettoScaffaliView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddettoScaffaliView frame = new AddettoScaffaliView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	LibriJPanJTab tabPanel= new LibriJPanJTab(LibriTableModel.SCAFFALIOPT);

	/**
	 * Create the frame.
	 */
	public AddettoScaffaliView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		
		JPanel RicercaPanel = new RicercaJPanel();
		contentPane.add(RicercaPanel, "cell 0 3,grow");
		
		JScrollPane LibriScrollPane = tabPanel.getPanel();
		contentPane.add(LibriScrollPane, "cell 0 4,grow");
		
	}

}
