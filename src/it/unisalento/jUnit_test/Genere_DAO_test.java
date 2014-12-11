package it.unisalento.jUnit_test;

import static org.junit.Assert.*;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Genere;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Genere_DAO_test {
	
	Genere g;

	@Before
	public void setUp() throws Exception {
		g=new Genere("test");
	}

	@After
	public void tearDown() throws Exception {
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`genere` WHERE `nome_genere`='test'");

	}

	@Test
	public void test() {
		assertEquals(true, Genere_DAO.getInstance().inserisciGenere(g));
		assertEquals(g.getNome(), Genere_DAO.getInstance().caricaGeneri().get(0).getNome());
	}

}
