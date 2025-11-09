package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.cliente.Consignee;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.container.Tanque;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;
import ar.edu.unq.po2.servicio.ServicioLavado;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioLavadoTestCase {
	
	   Container miContainer;
	   ServicioLavado miServLavado;
	   Cliente cliente;
	   
	   TerminalPortuaria terminalDumb; 
		@BeforeEach
		public void setUp() {
			cliente = new Consignee("Matias");
			terminalDumb = mock(TerminalPortuaria.class);
			//miContainer = new Tanque(cliente,100,100,100,10);
			miContainer = mock(Container.class);
			
			miServLavado = new ServicioLavado(miContainer);
			
			when(miContainer.getAncho()).thenReturn(1000d);
			when(miContainer.getAltura()).thenReturn(1000d);
			when(miContainer.getAncho()).thenReturn(1000d);
			when(terminalDumb.precioServicio(PrecioServicioTerminal.LAVADOCOMUN))
			.thenReturn(1000d);
			when(terminalDumb.precioServicio(PrecioServicioTerminal.LAVADOPESADO))
			.thenReturn(5000d);
		}

		@Test
		public void lavadoMasCaroTest() {
		
			assertEquals(1000d, miServLavado.costoServicio(terminalDumb));
		}
		
		@Test
		public void lavadoBaratoTest() {
			
			assertNotEquals(5000d, miServLavado.costoServicio(terminalDumb));
		}
		
		@Test
		public void tipoCargaTest() {
			assertEquals("Servicio Lavado",miServLavado.tipoServicio());
		}


}
