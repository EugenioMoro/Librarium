package it.unisalento.jUnit_test;

import static org.junit.Assert.*;
import it.unisalento.Model.Cliente;
import it.unisalento.Model.Libro;

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
		
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
