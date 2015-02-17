package it.unisalento.DataAccessObjects;

import it.unisalento.DbConnection.DbConnection;

public class DatiLibreriaDao {
	
	private static DatiLibreriaDao instance;
	
	public static DatiLibreriaDao getInstance(){
		if (instance==null)
			instance=new DatiLibreriaDao();
		return instance;
	}

	public boolean inserisciDati(String adm_code, String nomeLibreria){
		return DbConnection.getInstance().eseguiAggiornamento("insert into dati_libreria (adm_code, nomeLibreria) values ('"+adm_code+"','"+nomeLibreria+"')");
	}

	public String[] caricaDati(){
		String[] res = {"", ""};
		try{
		res=DbConnection.getInstance().eseguiQuery("select * from dati_libreria").get(0);
		} catch (ArrayIndexOutOfBoundsException e){
			res[0]="";
			res[1]="";
		}
		return res;
	}

}
