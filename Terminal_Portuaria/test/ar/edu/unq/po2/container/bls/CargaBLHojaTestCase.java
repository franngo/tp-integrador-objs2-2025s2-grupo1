package ar.edu.unq.po2.container.bls;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.cliente.Cliente;


/*
 * @Autor_ Matias Sanchez
 * */
class CargaBLHojaTestCase {
    
	
	CargaBL  cargaBLHoja;
	Cliente cli;
	
	@BeforeEach
	
	void setUp()  {
		cli=mock(Cliente.class);
		when(cli.nombreCliente()).thenReturn("Matias");
		cargaBLHoja = new CargaBLHoja(cli,1000d);
	}

	@Test
	void dueñoDelBLTest() {
		assertEquals("Matias",cargaBLHoja.dueños());
	}
	
	@Test
	void tipoCargaTest() {
		assertEquals("Carga BL Individual",cargaBLHoja.tipoCargaBL());
	}
	
	@Test
	void pesoContainerTest() {
		assertEquals(1000d,cargaBLHoja.getPeso());
	}

}
