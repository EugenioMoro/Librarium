package it.unisalento.jUnit_test;

import static org.junit.Assert.assertEquals;
import it.unisalento.DataAccessObjects.NewUserDAO;
import it.unisalento.DataAccessObjects.TesseraDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Tessera;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesseraDAO_test {
	
	Cliente MarioU=new Cliente();
	Tessera newT=new Tessera();

	@Before
	public void setUp() throws Exception {
		
		MarioU.setNome("Mario");
		MarioU.setCognome("Rossi");
		MarioU.setUsername("mRossi");
		MarioU.setPassword("asd123");
		MarioU.setEmail("mariorossi@yahoo.it");
		MarioU.setData_nascita(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		MarioU.setSesso(true);
		MarioU.setTelefono("0832454876");
		
		NewUserDAO.getInstance().registraCliente(MarioU);
		
		
	}

	@After
	public void tearDown() throws Exception {
		
		DbConnection.getInstance().eseguiAggiornamento("delete from tessera where cliente_utente_ID='"+NewUserDAO.getInstance().getUsernameId(MarioU)+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`cliente` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(MarioU)+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`utente` WHERE `username`='"+MarioU.getUsername()+"'");
		
	}

	@Test
	public void test() {
		
		assertEquals(true, TesseraDAO.getInstance().nuovaTessera(newT, MarioU));
		assertEquals(newT.getCodice(), TesseraDAO.getInstance().caricaTessera(MarioU).getCodice());
		assertEquals(newT.getPunti(),TesseraDAO.getInstance().caricaTessera(MarioU).getPunti());
	}

}
