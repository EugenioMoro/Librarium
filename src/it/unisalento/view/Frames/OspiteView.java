package it.unisalento.view.Frames;

import it.unisalento.view.Models.ButtonColumn;
import it.unisalento.view.Models.LibriTableModel;
import it.unisalento.view.Panels.RicercaJPanel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class OspiteView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTable tab=new JTable(new LibriTableModel());
		JPanel RicercaPanel = new RicercaJPanel(tab);
		
		JPanel SearchPanel = new RicercaJPanel(tab);
		SearchPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_SearchPanel = new GridBagConstraints();
		gbc_SearchPanel.anchor = GridBagConstraints.NORTH;
		gbc_SearchPanel.gridwidth = 10;
		gbc_SearchPanel.insets = new Insets(0, 0, 5, 0);
		gbc_SearchPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_SearchPanel.gridx = 0;
		gbc_SearchPanel.gridy = 0;
		contentPane.add(SearchPanel, gbc_SearchPanel);
		

		
		RicercaPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JScrollPane TabPanel = new JScrollPane(tab);
		GridBagConstraints gbc_TabPanel = new GridBagConstraints();
		gbc_TabPanel.gridwidth = 10;
		gbc_TabPanel.gridheight = 3;
		gbc_TabPanel.fill = GridBagConstraints.BOTH;
		gbc_TabPanel.gridx = 0;
		gbc_TabPanel.gridy = 2;
		contentPane.add(TabPanel, gbc_TabPanel);
		
		
		@SuppressWarnings("unused")
		ButtonColumn ordinaButton= new ButtonColumn(tab, LibriTableModel.getAction(), 8); //Definisco il bottone, input: tabella, azione, colonna
		

	}

}
