package it.unisalento.view.Frames;

import it.unisalento.view.Models.AutoriTableModel;
import it.unisalento.view.Panels.AutoriJpan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class AutoriInLibroFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AutoriJpan upperPane=new AutoriJpan(AutoriTableModel.INLIBROOPT);
	private static AutoriJpan lowerPane=new AutoriJpan(AutoriTableModel.AGGIUNGIOPT);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoriInLibroFrame frame = new AutoriInLibroFrame();
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
	public AutoriInLibroFrame() {
		setTitle("Modifica autori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][grow][][grow]"));
		
		JLabel lblAutoriCorrenti = new JLabel("Autori correnti:");
		contentPane.add(lblAutoriCorrenti, "cell 0 0");
		
		JScrollPane inLibroPane = upperPane.getPane();
		inLibroPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(inLibroPane, "cell 0 1,grow");
		
		JLabel lblScegliNuoviAutori = new JLabel("Scegli nuovi autori:");
		contentPane.add(lblScegliNuoviAutori, "cell 0 2");
		
		JScrollPane tuttiAutoriPane = lowerPane.getPane();
		tuttiAutoriPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(tuttiAutoriPane, "cell 0 3,grow");
		
		pack();
	}
	
	public static void refreshPanels(){
		upperPane.refresh();
		lowerPane.refresh();
	}

}
