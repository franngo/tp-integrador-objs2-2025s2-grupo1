package ar.edu.unq.po2.buscador_de_viaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class CondicionTest {
	
	TerminalPortuaria puerto = mock(TerminalPortuaria.class);
	Condicion cond = new FiltroPuertoDestino(puerto);
	
	@Test
	public void getConector() {
		assertEquals(null, cond.getConector());
	}

}
