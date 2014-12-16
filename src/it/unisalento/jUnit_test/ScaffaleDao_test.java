package it.unisalento.jUnit_test;

import static org.junit.Assert.*;

import java.util.Vector;

import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DataAccessObjects.ScaffaleDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScaffaleDao_test  {

	Libro l;
	Libro l2;
	Genere g;
	Genere g2;
	Autore a1;
	Autore a2;
	CasaEd c;
	Vector<Autore> autori=new Vector<Autore>();
	Vector<Libro> tutti=new Vector<Libro>();
	Vector<Genere> generi=new Vector<Genere>();
	
	
	@Before
	public void setUp() throws Exception {
		
		g=new Genere("test");
		g2=new Genere("test2");
		a1=new Autore("test", "test");
		a2=new Autore("test2", "test2");
		c=new CasaEd("test");
		
		autori.add(a1);
		autori.add(a2);
		
		
		Genere_DAO.getInstance().inserisciGenere(g);
		Genere_DAO.getInstance().inserisciGenere(g2);
		Autore_DAO.getInstance().inserisciAutore(a1);
		Autore_DAO.getInstance().inserisciAutore(a2);
		CasaEd_DAO.getInstance().inserisciCasa(c);
		
		generi=Genere_DAO.getInstance().caricaGeneri();
		
		l=new Libro(false, "i pilastri della terra", 15, "1048374012593", 1020, 9, g, autori, c);
		l2=new Libro(false, "shining", 15, "1048374012593", 1020, 9, g2, autori, c);
		
		LibroDAO.getInstance().inserisciLibro(l);
		LibroDAO.getInstance().inserisciLibro(l2);
	
		
		
	}

	@After
	public void tearDown() throws Exception {
		//teardown l
		DbConnection.getInstance().eseguiAggiornamento("delete from libro_has_autore where libro_ID_libro='"+l.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("delete from libro where ID_libro='"+l.getId()+"'");
		
		//teardown l2
		DbConnection.getInstance().eseguiAggiornamento("delete from libro_has_autore where libro_ID_libro='"+l2.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("delete from libro where ID_libro='"+l2.getId()+"'");
		
		//teardown generi case e autori
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`genere` WHERE `nome_genere`='test'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`genere` WHERE `nome_genere`='test2'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test2'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`casa_editrice` WHERE `nome_casa_editrice`='test'");
	}

	@Test
	public void test() {
		
		assertEquals(l.getId(), ScaffaleDAO.getInstance().caricaScaffali(generi).get(0).getScaffale().get(0).getId());
		assertEquals(l2.getId(), ScaffaleDAO.getInstance().caricaScaffali(generi).get(1).getScaffale().get(0).getId());
	
	}

}
