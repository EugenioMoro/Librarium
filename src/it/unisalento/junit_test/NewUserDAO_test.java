package it.unisalento.junit_test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisalento.DataAccessObjects.NewUserDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Utente;

public class NewUserDAO_test {

	Cliente MarioU=new Cliente();
	Utente MarcoU=new Utente();
	Utente StefanoU=new Utente();

	
	@Before
	public void setUp() throws Exception {
		
		
		MarioU.setNome("Mario");
		MarioU.setCognome("Rossi");
		MarioU.setUsername("mRossi");
		MarioU.setPassword("asd123");
		MarioU.setEmail("mariorossi@yahoo.it");
		MarioU.setData_nascita("1984-10-12");
		MarioU.setSesso(true);
		MarioU.setTelefono("0832454876");
		
		
		MarcoU.setNome("Marco");
		MarcoU.setCognome("Cervellera");
		MarcoU.setUsername("mCerv");
		MarcoU.setPassword("passw");
		
		
		StefanoU.setNome("Stefano");
		StefanoU.setCognome("Palma");
		StefanoU.setUsername("sPalma");
		StefanoU.setPassword("word");
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`cliente` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(MarioU)+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`addetto_vendite` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(MarcoU)+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`addetto_scaffali` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(StefanoU)+"'");	

	}

	@Test
	public void testCliente() {
		assertEquals(true, NewUserDAO.getInstance().registraCliente(MarioU));
		assertEquals(true, NewUserDAO.getInstance().registraAddVendite(MarcoU));
		assertEquals(true, NewUserDAO.getInstance().registraAddScaffali(StefanoU));
	}

}
