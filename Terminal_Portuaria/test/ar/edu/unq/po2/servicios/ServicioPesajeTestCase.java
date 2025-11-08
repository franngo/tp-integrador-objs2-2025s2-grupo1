package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.container.Tanque;
import ar.edu.unq.po2.servicio.ServicioPesaje;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioPesajeTestCase {
	
	
   Container miContainer;
   ServicioPesaje miServPesaje;
   
   TerminalPortuaria terminalDumb; 
	@BeforeEach
	public void setUp() throws Exception {
		when(terminalDumb.precioServicio(any())).thenReturn(20000d);
		miContainer = mock(Tanque.class);
		miServPesaje = new ServicioPesaje(miContainer);
	}

	@Test
	public void costoPorPesajeTest() {
		assertEquals(20000d, miServPesaje.costoServicio(terminalDumb));
	}
	
	@Test
	public void tipoCargaTest() {
		assertEquals("Servicio Pesaje",miServPesaje.tipoServicio());
	}

}
