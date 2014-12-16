package it.unisalento.DataAccessObjects;

import java.util.Vector;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.CasaEd;

public class CasaEd_DAO {

	private static CasaEd_DAO instance;
	
	public static CasaEd_DAO getInstance(){
		if(instance == null)
			instance=new CasaEd_DAO();
		return instance;
	}
	
	public boolean inserisciCasa(CasaEd c){
		boolean flag=true;
		
		//inserisce in db
		if(!DbConnection.getInstance().eseguiAggiornamento("insert into casa_editrice (nome_casa_editrice) values ('"+c.getNome()+"')")) flag=false;
	
		//recupera id e riempie c
		c.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_casa_editrice from casa_editrice").lastElement()[0]));
		
		return flag;
	}
	
	public boolean elimina(CasaEd c){
		return DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`casa_editrice` WHERE `ID_casa_editrice`='"+c.getId()+"'");
	}
	
	public Vector<CasaEd> caricaCase(){
		
		Vector<String[]> res=DbConnection.getInstance().eseguiQuery("select * from casa_editrice");
		Vector<CasaEd> caseEd=new Vector<CasaEd>();
		caseEd.setSize(res.size());
		
		for(int i=0; i<res.size(); i++){
			
			//riempie vettore case
			caseEd.set(i, new CasaEd(Integer.parseInt(res.get(i)[0]), res.get(i)[1]));
			
		}
		
		return caseEd;
		
	}
}
