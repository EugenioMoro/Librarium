package it.unisalento.DataAccessObjects;

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
		return DbConnection.getInstance().eseguiAggiornamento("insert into casa_editrice (nome_casa_editrice) values ('"+c.getNome()+"')");
	}
	
	public boolean elimina(CasaEd c){
		return DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`casa_editrice` WHERE `ID_casa_editrice`='"+c.getId()+"'");
	}
}
