package it.unisalento.jUnit_test;

import static org.junit.Assert.*;

import java.util.Vector;

import it.unisalento.DataAccessObjects.Autore_DAO;
import it.unisalento.DataAccessObjects.CasaEd_DAO;
import it.unisalento.DataAccessObjects.Genere_DAO;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DataAccessObjects.NewUserDAO;
import it.unisalento.DataAccessObjects.RichiestaDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Autore;
import it.unisalento.Model.CasaEd;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Genere;
import it.unisalento.Model.Libro;
import it.unisalento.Model.Richiesta;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RichiestaDAO_test {
	
	Cliente MarioU=new Cliente();
	
	Libro l1=new Libro();
	Libro l2=new Libro();
	Libro l3=new Libro();
	
	Genere g;
	Autore a1;
	Autore a2;
	CasaEd c;
	Vector<Autore> autori=new Vector<Autore>();
	Vector<Libro> genere;
	Vector<Libro> tutti=new Vector<Libro>();
	
	
	@Before
	public void setUp() throws Exception {
	
	//set utente
	MarioU.setNome("Mario");
	MarioU.setCognome("Rossi");
	MarioU.setUsername("mRossi");
	MarioU.setPassword("asd123");
	MarioU.setEmail("mariorossi@yahoo.it");
	MarioU.setData_nascita("1984-10-12");
	MarioU.setSesso(true);
	MarioU.setTelefono("0832454876");
	
	//set l1
	g=new Genere("test");
	a1=new Autore("test", "test");
	a2=new Autore("test2", "test2");
	c=new CasaEd("test");
	
	autori.add(a1);
	autori.add(a2);
	
	l1=new Libro(false, "i pilastri della terra", 15, "1048374012593", 1020, 9, g, autori, c);
	
	Genere_DAO.getInstance().inserisciGenere(g);
	Autore_DAO.getInstance().inserisciAutore(a1);
	Autore_DAO.getInstance().inserisciAutore(a2);
	CasaEd_DAO.getInstance().inserisciCasa(c);
	
	g.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_genere from genere where nome_genere='"+g.getNome()+"'").get(0)[0]));
	a1.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_autore from autore where nome='"+a1.getNome()+"'").get(0)[0]));
	a2.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_autore from autore where nome='"+a2.getNome()+"'").get(0)[0]));
	c.setId(Integer.parseInt(DbConnection.getInstance().eseguiQuery("select ID_casa_editrice from casa_editrice where nome_casa_editrice='"+c.getNome()+"'").get(0)[0]));
	
	//set l2
	l2=new Libro(false, "Choco Moons", 15, "1048374012594", 1020, 9, g, autori, c);
	
	
	//set l3
	l3=new Libro(false, "Crownfield", 15, "1048374012595", 1020, 9, g, autori, c);
	
	
	}

	@After
	public void tearDown() throws Exception {
		
		//delete richieste
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`richiesta` WHERE `cliente_utente_ID`='"+MarioU.getId()+"'");
		
		//delete cliente
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`cliente` WHERE `utente_ID`='"+MarioU.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`utente` WHERE `ID`='"+MarioU.getId()+"'");
		
		//delete libri
		DbConnection.getInstance().eseguiAggiornamento("delete from libro_has_autore where libro_ID_libro='"+l1.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("delete from libro where ID_libro='"+l1.getId()+"'");
		
		DbConnection.getInstance().eseguiAggiornamento("delete from libro_has_autore where libro_ID_libro='"+l2.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("delete from libro where ID_libro='"+l2.getId()+"'");
		
		DbConnection.getInstance().eseguiAggiornamento("delete from libro_has_autore where libro_ID_libro='"+l3.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("delete from libro where ID_libro='"+l3.getId()+"'");
		
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`genere` WHERE `nome_genere`='test'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`autore` WHERE `nome`='test2'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM `librarium`.`casa_editrice` WHERE `nome_casa_editrice`='test'");
		
	}

	@Test
	public void test() {
		 
		NewUserDAO.getInstance().registraCliente(MarioU);
		LibroDAO.getInstance().inserisciLibro(l1);
		LibroDAO.getInstance().inserisciLibro(l2);
		LibroDAO.getInstance().inserisciLibro(l3);
		
		assertEquals(true, RichiestaDAO.getInstance().ordinaLibro(l1, MarioU));
		assertEquals(true, RichiestaDAO.getInstance().ordinaLibro(l2, MarioU));
		assertEquals(true, RichiestaDAO.getInstance().ordinaLibro(l3, MarioU));
		
//		DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta SET flag_inoltro = 1 WHERE libro_ID_libro = '"+l2.getId()+"'");
//		DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta SET flag_inoltro = 1 ,flag_arrivo = 1, data_arrivo =NOW() WHERE libro_ID_libro = '"+l3.getId()+"'");
	
		Vector<Richiesta> r=RichiestaDAO.getInstance().RichiesteStorico();
		RichiestaDAO.getInstance().SetInoltro(r.get(1));
		RichiestaDAO.getInstance().SetInoltro(r.get(2));
		RichiestaDAO.getInstance().SetArrivo(r.get(2));
		
		assertEquals(l1.getId(), RichiestaDAO.getInstance().RichiesteEffettuate().get(0).getID_libro());
		assertEquals(l2.getId(), RichiestaDAO.getInstance().RichiesteInoltrate().get(0).getID_libro());
		assertEquals(l3.getId(), RichiestaDAO.getInstance().RichiesteArrivate().get(0).getID_libro());
		
		
	}

}
