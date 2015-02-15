package it.unisalento.jUnit_test;

import static org.junit.Assert.*;

import java.util.Vector;

import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;
import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DataAccessObjects.LibroDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Libro_DAO_test {

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
		Autore_DAO.getInstance().nuovoAutore(a1);
		Autore_DAO.getInstance().nuovoAutore(a2);
		CasaEd_DAO.getInstance().inserisciCasa(c);
		
		/* Codice non più utile
		 * g.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_genere from genere where nome_genere='"+g.getNome()+"'").get(0)[0]));
		a1.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_autore from autore where nome='"+a1.getNome()+"'").get(0)[0]));
		a2.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_autore from autore where nome='"+a2.getNome()+"'").get(0)[0]));
		c.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_casa_editrice from casa_editrice where nome_casa_editrice='"+c.getNome()+"'").get(0)[0]));*/
		
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
		assertEquals(l.getTitolo(), LibroDAO.getInstance().caricaTutti().get(0).getTitolo());
		assertEquals(l.getTitolo(), LibroDAO.getInstance().caricaPerGenere(g).get(0).getTitolo());
		assertEquals(1,LibroDAO.getInstance().caricaTutti().size());
		assertEquals(l.getId(), LibroDAO.getInstance().ricerca("pil").get(0).getId());
		assertEquals(true, LibroDAO.getInstance().ricerca("dumb").isEmpty());
	}

}
