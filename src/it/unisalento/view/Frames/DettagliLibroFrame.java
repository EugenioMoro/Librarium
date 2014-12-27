package it.unisalento.view.Frames;

import it.unisalento.Model.Libro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DettagliLibroFrame extends JFrame {

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
					DettagliLibroFrame frame = new DettagliLibroFrame(new Libro());
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
	public DettagliLibroFrame(Libro l) {
		setTitle(l.getTitolo());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 209, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel genere = new JLabel("Genere: '"+l.getGenere().getNome()+"'");
		genere.setBounds(10, 36, 173, 14);
		contentPane.add(genere);
		
		JLabel autore = new JLabel();
		if (l.getAutori().size()==1)
			autore.setText("Autore: '"+l.autoriToString()+"'");
		else autore.setText("Autori: '"+l.autoriToString()+"'");
		autore.setBounds(10, 61, 173, 14);
		contentPane.add(autore);
		
		JLabel casa = new JLabel("Casa Editrice: '"+l.getCasaEd().getNome()+"'");
		casa.setBounds(10, 86, 173, 14);
		contentPane.add(casa);
		
		JLabel pagine = new JLabel("Pagine: '"+l.getPagine()+"'");
		pagine.setBounds(10, 111, 173, 14);
		contentPane.add(pagine);
		
		JLabel costo = new JLabel("Costo: '"+l.getCosto()+"'");
		costo.setBounds(10, 136, 173, 14);
		contentPane.add(costo);
		
		JLabel isbn = new JLabel("ISBN: '"+l.getIsbn()+"'");
		isbn.setBounds(10, 161, 173, 14);
		contentPane.add(isbn);
	}
}
