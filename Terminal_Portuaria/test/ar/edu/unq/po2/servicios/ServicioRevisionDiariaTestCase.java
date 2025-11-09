package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Reefer;
import ar.edu.unq.po2.container.Tanque;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;
import ar.edu.unq.po2.servicio.ServicioElectricidad;
import ar.edu.unq.po2.servicio.ServicioRevisionDiaria;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioRevisionDiariaTestCase {

	TerminalPortuaria terminalDumb;
	ServicioRevisionDiaria miServRevisioDiaria;

	
	Tanque dumbContainer;
	
	
	@BeforeEach
	void setUp(){
		 terminalDumb = mock(TerminalPortuaria.class);
		dumbContainer = mock(Tanque.class);
		
		when(terminalDumb.precioServicio(PrecioServicioTerminal.REVISIONDIARIA)).thenReturn(20000d);
		miServRevisioDiaria = new ServicioRevisionDiaria(dumbContainer);
		
	}
	
	@Test
	public void costoDeServicioTest() {
		assertEquals(20000d,miServRevisioDiaria.costoServicio(terminalDumb));
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio revision Diaria",miServRevisioDiaria.tipoServicio());
	}


}
