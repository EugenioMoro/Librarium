package it.unisalento.view.Frames;

import it.unisalento.view.Models.RichiesteTableModel;
import it.unisalento.view.Panels.RichiesteScrollPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TableTest extends JFrame {

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
					TableTest frame = new TableTest();
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
	public TableTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
//		Action ordina = new AbstractAction() { //definisco l'azione da intraprendere al premere del bottone
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				//i commenti da ora in poi sono interpretazioni mie
//				JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
//				int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
//				LibriTableModel model = (LibriTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
//				model.OrdinaLibro(row, 0); //Qui è chiaro, invoca il metodo definito nella model
//			}
//		};
		
		
		
		//creo la tabella usando il model
		//JTable tab=new JTable(new LibriTableModel());
		
		//aggiungo al frame uno scollPane con la tabella all'interno
		//this.add(new JScrollPane(tab))
		RichiesteScrollPane panel= new RichiesteScrollPane(RichiesteTableModel.CLIENTEOPT);
		getContentPane().add(panel.getScrollPane());
		
		
	}

}
