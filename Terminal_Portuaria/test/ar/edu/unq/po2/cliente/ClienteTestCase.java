package ar.edu.unq.po2.cliente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTestCase {

	Cliente cliente;
	@BeforeEach
	void setUp()  {
		
		cliente = new Cliente("Matias");
	}

	@Test
	void testElClienteDiceSuNombre() {
		assertEquals("Matias",cliente.nombreCliente());
	}
	
	/*
	 * El cliente no hace anda de momento con el mail
	 * */
	@Test
    void reciboDeMail() {
		cliente.recibirMail("");
		assertTrue(true);
	}	

}
