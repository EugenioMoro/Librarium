package it.unisalento.jUnit_test;

import static org.junit.Assert.*;

import java.util.Vector;

import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Autore_DAO_test {

	Autore a;
	Autore b;
	Vector<Autore> autori=new Vector<Autore>();
	
	@Before
	public void setUp() throws Exception {
		a=new Autore("test", "test");
		b=new Autore("test1", "test1");
		autori.setSize(2);
		autori.add(0, a);
		autori.add(1, b);
	}

	@After
	public void tearDown() throws Exception {
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test'");

	}

	@Test
	public void test() {
		assertEquals(true, Autore_DAO.getInstance().inserisciAutore(a));
		assertEquals(true, Autore_DAO.getInstance().inserisciAutore(b));
		assertEquals(autori.get(0).getNome(), Autore_DAO.getInstance().caricaAutori().get(0).getNome());
		assertEquals(autori.get(1).getNome(), Autore_DAO.getInstance().caricaAutori().get(1).getNome());
		assertEquals(autori.get(0).getCognome(), Autore_DAO.getInstance().caricaAutori().get(0).getCognome());
		assertEquals(autori.get(1).getCognome(), Autore_DAO.getInstance().caricaAutori().get(1).getCognome());
	
	}

}
