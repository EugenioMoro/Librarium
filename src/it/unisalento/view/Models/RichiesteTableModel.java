package it.unisalento.view.Models;

import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DataAccessObjects.LogInDAO;
import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.Model.Richiesta;
import it.unisalento.business.Session;
import it.unisalento.view.Dialogs.MessageBoxes;
import it.unisalento.view.Frames.DettagliClienteFrame;
import it.unisalento.view.Frames.DettagliLibroFrame;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class RichiesteTableModel extends AbstractTableModel {
	
	public final static String CLIENTEOPT="cliente";
	public final static String ADDETTOOPT="addetto";
	
	private final String columnNamesADDETTO[]={"ID", "Cliente", "Libro", "Data richiesta", "Data arrivo", "Inoltrata", "Aggiorna"};
	private final String columnNamesCLIENTE[]={"ID", "Libro", "Data richiesta", "Data arrivo", "Inoltrata",};
	
	private String option;
	private Vector<Integer> id;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RichiesteTableModel(String option) {
		this.option=option;
	}

	@Override
	public String getColumnName(int col) {
	    if(option.equals(ADDETTOOPT)) return columnNamesADDETTO[col];
	    return columnNamesCLIENTE[col];
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        if (option.equals(ADDETTOOPT)){
        	if(!Session.currentSession().getRichieste().get(row).isFlag_arrivo())
        		if (col==6) return true;
        	if (col==1 || col==2) return true;
        } else if (col==1) return true;
        return false;
    }
	
	@Override
    public Class<?> getColumnClass(int col) {  
		switch (col){
		case 0: return Object.class;
		case 1: return ButtonColumn.class;
		case 2: if (option.equals(ADDETTOOPT)) return ButtonColumn.class; return Object.class;
		case 3: return Object.class;
		case 4: if (option.equals(ADDETTOOPT)) return Object.class; return Boolean.class;
		case 5: return Boolean.class;
		case 6: return ButtonColumn.class;
		default: return Object.class;
		}
    }
	
	@Override
	public int getColumnCount() {
		// [0] id richiesta - [1]* CLIENTE (Button) - [2] libro(button) - [3] data richiesta - [4] data arrivo - [5]flag - [6]* Modifier (button)
		
		if (option.equals(ADDETTOOPT))
			return 7;
		
		return 5;
		}

	@Override
	public int getRowCount() {
		
		return Session.currentSession().getRichieste().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		id=new Vector<Integer>();
		id.add(Session.currentSession().getRichieste().get(row).getID_libro());
		
		if (option.equals(ADDETTOOPT)){
			switch (col){
			case 0: return Session.currentSession().getRichieste().get(row).getID_richiesta();
			case 1: return "Id:"+Session.currentSession().getRichieste().get(row).getID_cliente();
			case 2: return LibroDAO.getInstance().caricaPerId(id).get(0).getTitolo();
			case 3: if(Session.currentSession().getRichieste().get(row).getData_richiesta()!=null)
				return Session.currentSession().getRichieste().get(row).getData_richiesta();
			return "n.d.";
			case 4:  if(Session.currentSession().getRichieste().get(row).getData_arrivo()!=null)
				return Session.currentSession().getRichieste().get(row).getData_arrivo();
			return "n.d.";
			case 5: return Session.currentSession().getRichieste().get(row).isFlag_inoltro();
			case 6: if(!Session.currentSession().getRichieste().get(row).isFlag_inoltro())
				return "Inoltra";
			else if (!Session.currentSession().getRichieste().get(row).isFlag_arrivo())
				return "Arrivato";
			return "Arrivato";
			
			}
		} else{
			switch (col){
			case 0: return Session.currentSession().getRichieste().get(row).getID_richiesta();
			case 1: return LibroDAO.getInstance().caricaPerId(id).get(0).getTitolo();
			case 2: if(Session.currentSession().getRichieste().get(row).getData_richiesta()!=null)
				return Session.currentSession().getRichieste().get(row).getData_richiesta();
			return "n.d.";
			case 3:  if(Session.currentSession().getRichieste().get(row).getData_arrivo()!=null)
				return Session.currentSession().getRichieste().get(row).getData_arrivo();
			return "n.d.";
			case 4: return Session.currentSession().getRichieste().get(row).isFlag_inoltro();
			}
		}
		
		return null;
	}
	
	private static Action dettagliClienteAction = new AbstractAction() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			RichiesteTableModel model = (RichiesteTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.dettagliCliente(Session.currentSession().getRichieste().get(row).getID_cliente());
			
		}
	};
	
	private static Action dettagliLibroAction = new AbstractAction() { //definisco l'azione da intraprendere al premere del bottone

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {

			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			RichiesteTableModel model = (RichiesteTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.dettagliLibro(row);
		}
	};
	
	private static Action aggiornaAction=new AbstractAction() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			RichiesteTableModel model = (RichiesteTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.aggiornaRichiesta(row);
			Session.currentSession().aggiornaRichieste();
			model.fireTableDataChanged();
		}
	};
	
	public static Action getDettagliLibroAction(){
		return dettagliLibroAction;
	}
	
	public static Action getDettagliClienteAction(){
		return dettagliClienteAction;
	}

	public static Action getAggiornaAction(){
		return aggiornaAction;
	}
	
	public String getOption() {
		return option;
	}
	
	public void dettagliLibro(int row){
		
		//Maledetta a te giulia, RichiestaDAO è stata progettata male!!! Però non ho voglia di cambiarla
		Vector<Integer> id=new Vector<Integer>();
		id.add(Session.currentSession().getRichieste().get(row).getID_libro());
		
		JFrame dettagli=new DettagliLibroFrame(LibroDAO.getInstance().caricaPerId(id).get(0));
		dettagli.setVisible(true);
	}

	public void dettagliCliente(int id){
		JFrame dettagli=new DettagliClienteFrame(LogInDAO.getInstance().dettagliClientePerID(id));
		dettagli.setVisible(true);
	}

	public void aggiornaRichiesta(int row){
		Richiesta r=Session.currentSession().getRichieste().get(row);
		
		if (!r.isFlag_arrivo()){
			if (r.isFlag_inoltro()){
				RichiestaDAO.getInstance().SetArrivo(r);
				MessageBoxes.alert("Arrivo", "Richiesta modificata");
				
				//email
			} else{
				RichiestaDAO.getInstance().SetInoltro(r);
				MessageBoxes.alert("Inoltro", "Richiesta modificata");
			}
		}
	}

}
