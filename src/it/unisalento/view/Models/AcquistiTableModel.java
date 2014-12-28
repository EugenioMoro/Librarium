package it.unisalento.view.Models;

import it.unisalento.DataAccessObjects.LogInDAO;
import it.unisalento.business.Session;
import it.unisalento.view.Frames.DettagliClienteFrame;
import it.unisalento.view.Panels.LibriJPanJTab;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class AcquistiTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String CLIENTEOPT="cliente";
	public final static String ADDETTOOPT="addetto";
	private String option;
	
	private String columnNamesCLIENTE[]={"ID acquisto", "Data", "Elenco Libri", "Incasso"};
	private String columNamesADDETTO[]={"ID acquisto", "Data", "Elenco Libri", "Incasso", "ID Cliente"};
	
	
	
	public AcquistiTableModel(String option) {
		this.option=option;
	}
	
	
	@Override
	public String getColumnName(int col) {
	    if (option.equals(ADDETTOOPT))
	    	return columNamesADDETTO[col];
	    return columnNamesCLIENTE[col];
	}
	
	@Override
	public int getColumnCount() {
		if (option.equals(ADDETTOOPT)) return 5;
		return 4;
	}

	@Override
	public int getRowCount() {
		return Session.currentSession().getAcquisti().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col){
		case 0: return Session.currentSession().getAcquisti().get(row).getId();
		case 1: return Session.currentSession().getAcquisti().get(row).getData();
		case 2: return "Elenco Libri";
		case 3: return Session.currentSession().getAcquisti().get(row).getIncasso();
		case 4: return "Id: "+Session.currentSession().getAcquisti().get(row).getCliente_id();
		}
		return null;
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
        if (col==2 || col==4) return true;
        return false;
    }
	
	@Override
    public Class<?> getColumnClass(int col) {  
		if (col==2 || col==4) return ButtonColumn.class;
		return getValueAt(0, col).getClass();
    }
	
	public void elencoLibri(int row){
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		LibriJPanJTab panel = new LibriJPanJTab(LibriTableModel.NEUTRALOPT);
		frame.add(panel.getPanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	public void dettagliCliente(int row){
		int id=Session.currentSession().getAcquisti().get(row).getCliente_id();
		JFrame dettagli=new DettagliClienteFrame(LogInDAO.getInstance().dettagliClientePerID(id));
		dettagli.setVisible(true);
	}


	private static Action elencoAction= new AbstractAction() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			AcquistiTableModel model = (AcquistiTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.elencoLibri(row);
			
		}
	};
	
	private static Action dettagliAction = new AbstractAction() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			//i commenti da ora in poi sono interpretazioni mie
			JTable table = (JTable) e.getSource(); //collega la tabella che ha generato l'evento in modo da poterci lavorare in questo metodo
			int row = Integer.valueOf(e.getActionCommand()); //getActionCommand dipende dal component della view generante, suppongo che con una jTable restituisca in string la row dove è stato generato l'evento
			AcquistiTableModel model = (AcquistiTableModel) table.getModel(); //Ok, questo è meno chiaro di tutti, non capisco perchè non usare il costruttore del model anzichè fare il cast dal getModel della tabella, per poter riutilizzare il codice?
			model.dettagliCliente(row);
			
		}
	};
	
	public static Action getElencoAction(){
		return elencoAction;
	}
	
	public static Action getDettagliAction(){
		return dettagliAction;
	}
	
}
