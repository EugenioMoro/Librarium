package it.unisalento.view.Panels;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import it.unisalento.business.Session;
import it.unisalento.Model.Autore;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;

public class RicercaLibri extends JPanel {
	private JTextField Titolo;

	/**
	 * Create the panel.
	 */
	public RicercaLibri() {
		setLayout(null);
	
	
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 98, 134, 41);
		add(comboBox);
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(140, 98, 119, 41);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(262, 98, 140, 41);
		add(comboBox_2);
		
		JLabel lblAutore = new JLabel("Autore");
		lblAutore.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAutore.setBackground(Color.WHITE);
		lblAutore.setBounds(10, 69, 103, 23);
		add(lblAutore);
		
		JLabel lblGenere = new JLabel("Genere");
		lblGenere.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGenere.setBounds(145, 69, 96, 23);
		add(lblGenere);
		
		JLabel lblCasaEditrice = new JLabel("Casa Editrice");
		lblCasaEditrice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCasaEditrice.setBounds(268, 67, 119, 27);
		add(lblCasaEditrice);
		
		Titolo = new JTextField();
		Titolo.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		Titolo.setBounds(412, 98, 156, 41);
		add(Titolo);
		Titolo.setColumns(10);
		
		JButton Cerca = new JButton("Cerca");
		Cerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Cerca.setBounds(499, 148, 69, 27);
		add(Cerca);
		
		JTextArea intestazione = new JTextArea();
		intestazione.setBackground(SystemColor.menu);
		intestazione.setFont(new Font("Times New Roman", Font.BOLD, 30));
		intestazione.setText("Ricerca");
		intestazione.setBounds(10, 11, 196, 38);
		add(intestazione);
		
		JLabel lblTitolo = new JLabel("Titolo");
		lblTitolo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTitolo.setBounds(426, 69, 96, 21);
		add(lblTitolo);

	}
}