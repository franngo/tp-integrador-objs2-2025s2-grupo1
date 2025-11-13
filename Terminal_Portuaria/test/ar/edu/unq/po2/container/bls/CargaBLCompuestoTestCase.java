package ar.edu.unq.po2.container.bls;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.cliente.Cliente;

class CargaBLCompuestoTestCase {
   
	CargaBL carga1;
	CargaBL carga2;
	List<CargaBL> cargasC;
	
	CargaBLCompuesto cargaBLC;
	@BeforeEach
	void setUp() {
		
		
		carga1 = mock(CargaBLHoja.class);
		carga2 = mock(CargaBLHoja.class);
		when(carga1.dueños()).thenReturn("Matias");
		when(carga1.getPeso()).thenReturn(30d);
		when(carga2.dueños()).thenReturn("Pablo");
		when(carga2.getPeso()).thenReturn(40d);
		
		cargasC = List.of(carga1,carga2);
		cargaBLC= new CargaBLCompuesto(cargasC);
		
	}

	@Test
	void testTipoCarga() {
		String respuesta = "Carga compuesta por 2 BL's";
		assertEquals(respuesta,cargaBLC.tipoCargaBL());
		
	}
	
	@Test
	void dueñosCargasTest() {
		String respuesta = "Matias,Pablo";
		assertEquals(respuesta,cargaBLC.dueños());
		
	}
	
	/*
	 * El peso de CargaBLCompuesto es la suma de sus cargas
	 * */
	@Test
	void pesoDeCargaTest() {
		assertEquals(70d,cargaBLC.getPeso());
	}
	
	@Test 
	void clientesEnCarga() {
		assertTrue(cargaBLC.getDuenioConsignee() instanceof Cliente);
	}

}
