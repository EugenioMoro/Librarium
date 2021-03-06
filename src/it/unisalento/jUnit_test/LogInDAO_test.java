package it.unisalento.jUnit_test;

import static org.junit.Assert.*;

import java.util.Calendar;

import it.unisalento.DataAccessObjects.LogInDAO;
import it.unisalento.DataAccessObjects.NewUserDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Utente;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogInDAO_test {
	
	Cliente MarioU=new Cliente();
	Utente MarcoU=new Utente();
	Utente StefanoU=new Utente();
	Utente dumb=new Utente("dumb", "dumb");

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
		
		
		MarcoU.setNome("Marco");
		MarcoU.setCognome("Cervellera");
		MarcoU.setUsername("mCerv");
		MarcoU.setPassword("passw");
		
		
		NewUserDAO.getInstance().registraCliente(MarioU);
		NewUserDAO.getInstance().registraAddVendite(MarcoU);
		NewUserDAO.getInstance().registraAddScaffali(StefanoU);
		
		
	}

	@After
	public void tearDown() throws Exception {
		

		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`cliente` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(MarioU)+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`addetto_vendite` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(MarcoU)+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`addetto_scaffali` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(StefanoU)+"'");	
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`utente` WHERE `username`='"+MarioU.getUsername()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`utente` WHERE `username`='"+MarcoU.getUsername()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`utente` WHERE `username`='"+StefanoU.getUsername()+"'");
	
		
	}

	@Test
	public void test() {
		
		assertEquals(true,LogInDAO.getInstance().checkCredenziali(MarcoU.getUsername(), MarcoU.getPassword()));
		assertEquals(false,LogInDAO.getInstance().checkCredenziali(dumb.getUsername(), dumb.getPassword()));
		
		assertEquals("Cliente", LogInDAO.getInstance().checkTipo(MarioU.getUsername()));
		assertEquals("Vendite", LogInDAO.getInstance().checkTipo(MarcoU.getUsername()));
		assertEquals("Scaffali", LogInDAO.getInstance().checkTipo(StefanoU.getUsername()));
		
		assertEquals(MarcoU.getUsername(), LogInDAO.getInstance().caricaUtente(MarcoU.getUsername(), MarcoU.getPassword()));
		//assertEquals(MarcoU, LogInDAO.getInstance().caricaUtente(MarcoU));
		
		
		
	}

}
