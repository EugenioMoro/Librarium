package it.unisalento.view.Frames;

import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Models.SearchAutoriComboModel;
import it.unisalento.view.Models.SearchAutoriComboModel;
import it.unisalento.view.Models.SearchCaseComboModel;
import it.unisalento.view.Models.SearchGenereComboModel;
import it.unisalento.view.Panels.RicercaJPanel;
import it.unisalento.Model.Autore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class ComboboxTest extends JFrame {

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
		add(new RicercaJPanel());
	}


}
