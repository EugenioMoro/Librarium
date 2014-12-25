package it.unisalento.jUnit_test;

import static org.junit.Assert.assertEquals;
import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;
import it.unisalento.business.SearchEngine;
import it.unisalento.business.Session;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchEngineTest {
	
	Libro l;
	Genere g;
	Autore a1;
	Autore a2;
	CasaEd c;
	Vector<Autore> autori=new Vector<Autore>();
	Vector<Libro> tutti=new Vector<Libro>();

	@Before
	public void setUp() throws Exception {
		
		g=new Genere("test");
		a1=new Autore("test", "test");
		a2=new Autore("test2", "test2");
		c=new CasaEd("test");
		
		autori.add(a1);
		autori.add(a2);
		
		l=new Libro(false, "i pilastri della terra", 15, "1048374012593", 1020, 9, g, autori, c);
		
		Genere_DAO.getInstance().inserisciGenere(g);
		Autore_DAO.getInstance().inserisciAutore(a1);
		Autore_DAO.getInstance().inserisciAutore(a2);
		CasaEd_DAO.getInstance().inserisciCasa(c);
		
	}

	@After
	public void tearDown() throws Exception {
		
		DbConnection.getInstance().eseguiAggiornamento("delete from libro_has_autore where libro_ID_libro='"+l.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("delete from libro where ID_libro='"+l.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`genere` WHERE `nome_genere`='test'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test2'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`casa_editrice` WHERE `nome_casa_editrice`='test'");
		
	}

	@Test
	public void test() {
		assertEquals(true, LibroDAO.getInstance().inserisciLibro(l));
		/*SearchEngine.getInstance().perTitolo("pilast");
		assertEquals(1, Session.currentSession().getSearchResults().size());
		assertEquals(false, SearchEngine.getInstance().perAutore(new Autore(52, "John", "Vlissides")));
		Session.currentSession().SetSearchDefault();
		assertEquals(true,Session.currentSession().getTuttiLibri().equals(Session.currentSession().getSearchResults()));*/
		
		assertEquals(false, SearchEngine.getInstance().perGenere(Session.currentSession().getGeneri().get(2)));
		
	}

}
