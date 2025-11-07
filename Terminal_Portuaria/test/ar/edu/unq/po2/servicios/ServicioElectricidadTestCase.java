package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import ar.edu.unq.po2.container.Reefer;

import ar.edu.unq.po2.servicio.ServicioElectricidad;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioElectricidadTestCase {

	TerminalPortuaria terminalDumb = mock(TerminalPortuaria.class);
	ServicioElectricidad miServElectricidad;

	
	Reefer dumbContainer;
	
	
	@BeforeEach
	void setUp(){
		dumbContainer = mock(Reefer.class);
		when(dumbContainer.getConsumoPorHora()).thenReturn(10d);
		when(terminalDumb.precioServicio(any())).thenReturn(20000d);
		miServElectricidad = new ServicioElectricidad(dumbContainer);
		
	}
	
	@Test
	public void costoDeServicioTest() {
		assertEquals(200000d,miServElectricidad.costoServicio(terminalDumb));
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio Electricidad",miServElectricidad.tipoServicio());
	}

}
