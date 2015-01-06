package it.unisalento.view.Frames;

import it.unisalento.view.Models.LibriTableModel;
import it.unisalento.view.Models.SearchTableInside;
import it.unisalento.view.Panels.LibriJPanJTab;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ComboboxTest extends JFrame implements SearchTableInside {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable tab;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboboxTest frame = new ComboboxTest();
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
	public ComboboxTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		LibriJPanJTab tabPanel=new LibriJPanJTab(LibriTableModel.SCAFFALIOPT);
		
		JPanel searchPane=new RicercaJPanel();
		getContentPane().add(searchPane);
		getContentPane().add(tabPanel.getPanel());
		pack();
		
		
	}

	@Override
	public JTable getSearchTable() {
		return ComboboxTest.tab;
	}

	public static void refreshTable(){
		tab.repaint();
	}

	@Override
	public void defineColumnButton() {
		
	}

}
