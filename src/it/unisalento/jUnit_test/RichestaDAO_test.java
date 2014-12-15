package it.unisalento.jUnit_test;

import static org.junit.Assert.*;
import it.unisalento.DataAccessObjects.LibroDAO;
import it.unisalento.DbConnection.DbConnection;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Libro;
import it.unisalento.DataAccessObjects.RichiestaDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RichestaDAO_test {
		
	Cliente MarioU=new Cliente();
	Libro l1=new Libro();
	Libro l2=new Libro();
	Libro l3=new Libro();
	
	@Before
	public void setUp() throws Exception {
		
		MarioU.setId(30);
		MarioU.setNome("Mario");
		MarioU.setCognome("Rossi");
		MarioU.setUsername("mRossi");
		MarioU.setPassword("asd123");
		MarioU.setEmail("mariorossi@yahoo.it");
		MarioU.setData_nascita("1984-10-12");
		MarioU.setSesso(true);
		MarioU.setTelefono("0832454876");
		
		l3.setId(12);
		l3.setTitolo("Battaglie stellari");
		l3.setIsbn("1234567890987654");
		l3.setCosto(15);
		l3.setAutori("Eugenio Moro");
		l3.setCasaEd("Bona Editore");
		l3.setPagine(150);
		l3.setDisp(0);
		l3.setGenere("Fantascienza");
		l3.setRichiesto(true);
		l3.setFlag_inoltro(false);
		
		
		l2.setId(13);
		l2.setTitolo("Un sentimento che trascende il tempo");
		l2.setIsbn("1234567890987655");
		l2.setCosto(15,00);
		l2.setAutori("Eugenio Moro");
		l2.setCasaEd("Mamo Editrice");
		l2.setPagine(160);
		l2.setDisp(0);
		l2.setGenere("Romantico");
		l2.setRichiesto(true);
		l2.setFlag_inoltro(true);
		l2.setFlag_arrivo(false);
		
		l1.setId(14);
		l1.setTitolo("Solomon Solomon");
		l1.setIsbn("1234567890987656");
		l1.setCosto(15,00);
		l1.setAutori("Giulia Marra");
		l1.setCasaEd("Tiggiano Editori");
		l1.setPagine(170);
		l1.setDisp(0);
		l1.setGenere("Guerra");
		l1.setRichiesto(true);
		l1.setFlag_inoltro(true);
		l1.setFlag_arrivo(true);
		
		
	}

	@After
	public void tearDown() throws Exception {
		
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM libro WHERE libro_ID_libro='"+l1.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM libro WHERE libro_ID_libro='"+l2.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM libro WHERE libro_ID_libro='"+l3.getId()+"'");
		DbConnection.getInstance().eseguiAggiornamento("DELETE FROM utente WHERE utente_ID='"+MarioU.getId()+"'");
		
	}

	@Test
	public void test() {
		assertEquals(true, RichiestaDAO.getInstance().RichiesteEffettuate());
		assertEquals(true, RichiestaDAO.getInstance().RichiesteInoltrate());
		assertEquals(true, RichiestaDAO.getInstance().RichiesteArrivate());
		assertEquals(true, RichiestaDAO.getInstance().RichiesteStorico());
	}

}
