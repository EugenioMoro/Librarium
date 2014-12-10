package it.unisalento.jUnit_test;

import static org.junit.Assert.*;
import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Autore_DAO_test {

	Autore a;
	
	@Before
	public void setUp() throws Exception {
		a=new Autore("test", "test");
	}

	@After
	public void tearDown() throws Exception {
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test'");

	}

	@Test
	public void test() {
		assertEquals(true, Autore_DAO.getInstance().inserisciAutore(a));
	}

}
