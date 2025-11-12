package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.container.Tanque;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;
import ar.edu.unq.po2.servicio.ServicioPesaje;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioPesajeTestCase {
	
	
   Container miContainer;
   ServicioPesaje miServPesaje;
   LocalDateTime horaInicioServicio;
   TerminalPortuaria terminalDumb; 
   
	@BeforeEach
	public void setUp() throws Exception {
		horaInicioServicio = LocalDateTime.now();
		terminalDumb= mock(TerminalPortuaria.class);
		when(terminalDumb.precioServicio(PrecioServicioTerminal.PESAJE)).thenReturn(20000d);
		miContainer = mock(Tanque.class);
		miServPesaje = new ServicioPesaje(miContainer,horaInicioServicio);
	}

	@Test
	public void costoPorPesajeTest() {
		assertEquals(20000d, miServPesaje.costoServicio(terminalDumb,LocalDateTime.now()));
	}
	
	@Test
	public void tipoCargaTest() {
		assertEquals("Servicio Pesaje",miServPesaje.tipoServicio());
	}

}
