package ar.edu.unq.po2.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteCompuestoTestCase {
    
	Cliente cli;
	Cliente cli2;
	Cliente cli3;
	ClienteCompuesto clienteCompuesto;
	@BeforeEach
	void setUp() throws Exception {
		cli= spy(new Cliente("Matias"));
		cli2= spy(new Cliente("Benja"));
		cli3= spy(new Cliente("Franco"));
		
		
		
	   clienteCompuesto = new ClienteCompuesto(null,List.of(cli,cli2,cli3));
		
	}

	@Test
	void testNombreCliente() {
		assertEquals("Matias, Benja, Franco",clienteCompuesto.nombreCliente());
	}
	@Test
    void reciboDeMail() {
		clienteCompuesto.recibirMail("");
		assertTrue(true);
	}	

}
