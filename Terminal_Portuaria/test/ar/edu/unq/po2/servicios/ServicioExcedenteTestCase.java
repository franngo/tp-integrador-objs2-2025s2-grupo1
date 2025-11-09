package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Reefer;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;
import ar.edu.unq.po2.servicio.ServicioExcedente;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioExcedenteTestCase {

	TerminalPortuaria terminalDumb = mock(TerminalPortuaria.class);
	ServicioExcedente miServExcedente;

	
	Reefer dumbContainer;
	
	
	@BeforeEach
	void setUp(){
		dumbContainer = mock(Reefer.class);
		when(dumbContainer.getConsumoPorHora()).thenReturn(10d);
		when(terminalDumb.precioServicio(PrecioServicioTerminal.DIAEXCEDENTE)).thenReturn(20000d);
		miServExcedente = new ServicioExcedente(dumbContainer);
		
	}
	
	@Test
	public void costoDeServicioTest() {
		assertEquals(20000d,miServExcedente.costoServicio(terminalDumb));
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio Excedente",miServExcedente.tipoServicio());
	}
}
