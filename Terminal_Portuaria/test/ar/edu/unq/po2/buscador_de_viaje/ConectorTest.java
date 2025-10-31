package ar.edu.unq.po2.buscador_de_viaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ConectorTest {
	
	Condicion cond1 = mock(Condicion.class);
	Condicion cond2 = mock(Condicion.class);
	Conector conector = new Or(cond1, cond2);
	Condicion cond3 = mock(Condicion.class);
	
	@Test
	public void getConector() {
		assertEquals(conector, conector.getConector());
	}
	
	@Test
	public void addCondicion() {
		assertEquals(cond1, conector.getSubcondicionIzquierda());
		assertEquals(cond2, conector.getSubcondicionDerecha());
		conector.removeCondicion(cond1);
		conector.addCondicion(cond3);
		assertEquals(cond3, conector.getSubcondicionIzquierda());
		assertEquals(cond2, conector.getSubcondicionDerecha());
		conector.removeCondicion(cond2);
		conector.addCondicion(cond1);
		assertEquals(cond3, conector.getSubcondicionIzquierda());
		assertEquals(cond1, conector.getSubcondicionDerecha());
		conector.addCondicion(cond2); //que no va a poder realizarse, porque conector ya tiene sus 2 subcondiciones llenas
		assertEquals(cond3, conector.getSubcondicionIzquierda());
		assertEquals(cond1, conector.getSubcondicionDerecha());
	}
	
	@Test
	public void removeCondicion() {
		assertEquals(conector, conector.getConector());
		assertEquals(cond1, conector.getSubcondicionIzquierda());
		assertEquals(cond2, conector.getSubcondicionDerecha());
		conector.removeCondicion(cond1);
		assertEquals(null, conector.getSubcondicionIzquierda());
		assertEquals(cond2, conector.getSubcondicionDerecha());
		conector.removeCondicion(cond2);
		assertEquals(null, conector.getSubcondicionIzquierda());
		assertEquals(null, conector.getSubcondicionDerecha());
		conector.removeCondicion(cond2); //la cual ya fue borrada y no es actualmente una subcondición de conector
		assertEquals(null, conector.getSubcondicionIzquierda());
		assertEquals(null, conector.getSubcondicionDerecha());
	}

}
