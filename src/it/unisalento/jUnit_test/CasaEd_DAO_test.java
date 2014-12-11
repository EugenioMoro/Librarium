package it.unisalento.jUnit_test;

import static org.junit.Assert.*;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.CasaEd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CasaEd_DAO_test {

	CasaEd c;
	
	
	@Before
	public void setUp() throws Exception {
		
		c=new CasaEd("test");
		
	}

	@After
	public void tearDown() throws Exception {
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`casa_editrice` WHERE `nome_casa_editrice`='test'");
	}

	@Test
	public void test() {
		assertEquals(true, CasaEd_DAO.getInstance().inserisciCasa(c));
		assertEquals(c.getNome(), CasaEd_DAO.getInstance().caricaCase().get(0).getNome());
	}

}
