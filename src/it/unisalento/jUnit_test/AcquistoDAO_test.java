package it.unisalento.jUnit_test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Vector;

import it.unisalento.DataAccessObjects.AcquistoDAO;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DataAccessObjects.NewUserDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Acquisto;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Libro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AcquistoDAO_test  extends Libro_DAO_test {
	
	Cliente MarioU=new Cliente();
	Libro l2=new Libro();
	Acquisto a=new Acquisto();

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		MarioU.setNome("Mario");
		MarioU.setCognome("Rossi");
		MarioU.setUsername("mRossi");
		MarioU.setPassword("asd123");
		MarioU.setEmail("mariorossi@yahoo.it");
		MarioU.setData_nascita(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		MarioU.setSesso(true);
		MarioU.setTelefono("0832454876");
		
		l2=new Libro(false, "Shining", 15, "1048374012593", 1020, 9, g, autori, c);
		
		LibroDAO.getInstance().inserisciLibro(l);
		LibroDAO.getInstance().inserisciLibro(l2);
		NewUserDAO.getInstance().registraCliente(MarioU);
		
		Vector<Libro> libri=new Vector<Libro>();
		libri.setSize(2);
		libri.set(0,l);
		libri.set(1, l2);
		
		a.setCliente_id(MarioU.getId());
		a.setLibri(libri);
		a.setIncasso(l.getCosto()+l2.getCosto());
		
	}

	@After
	public void tearDown() throws Exception {
		
		//teardown acquisto
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`acquisto_has_libro` WHERE `acquisto_ID_acquisto`='"+a.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`acquisto` WHERE `ID_acquisto`='"+a.getId()+"'");
		
		//teardown cliente
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`cliente` WHERE `utente_ID`='"+NewUserDAO.getInstance().getUsernameId(MarioU)+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`utente` WHERE `username`='"+MarioU.getUsername()+"'");
		
		//teardown l2
		DbConnection.getInstance().eseguiAggiornamento("delete from libro_has_autore where libro_ID_libro='"+l2.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("delete from libro where ID_libro='"+l2.getId()+"'");
		
		super.tearDown();
	}

	@Test
	public void test() {
		
		assertEquals(true,AcquistoDAO.getInstance().inserisci(a));
		assertEquals(a.getId(), AcquistoDAO.getInstance().storicoCliente(MarioU).get(0).getId());
		assertEquals(a.getLibri().get(1).getId(), AcquistoDAO.getInstance().storicoCliente(MarioU).get(0).getLibri().get(1).getId());
		assertEquals(a.getId(), AcquistoDAO.getInstance().caricaStorico().get(0).getId());
		assertEquals(a.getLibri().get(1).getId(), AcquistoDAO.getInstance().caricaStorico().get(0).getLibri().get(1).getId());
		
	
	}

}
