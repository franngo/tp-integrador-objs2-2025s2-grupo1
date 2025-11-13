package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.cliente.Cliente;

import ar.edu.unq.po2.container.Container;

import ar.edu.unq.po2.servicio.PrecioServicioTerminal;
import ar.edu.unq.po2.servicio.ServicioLavado;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioLavadoTestCase {
	
	   Container miContainer;
	   ServicioLavado miServLavado;
	   Cliente cliente;
	   LocalDateTime horaInicioServicio;
	   
	   TerminalPortuaria terminalDumb; 
		@BeforeEach
		public void setUp() {
			cliente = new Cliente("Matias");
			terminalDumb = mock(TerminalPortuaria.class);
			//miContainer = new Tanque(cliente,100,100,100,10);
			miContainer = mock(Container.class);
			horaInicioServicio = LocalDateTime.now();
			
			miServLavado = new ServicioLavado(miContainer,horaInicioServicio);
			
			when(miContainer.getAncho()).thenReturn(1000d);
			when(miContainer.getAltura()).thenReturn(1000d);
			when(miContainer.getAncho()).thenReturn(1000d);
			when(terminalDumb.precioServicio(PrecioServicioTerminal.LAVADOCOMUN))
			.thenReturn(1000d);
			when(terminalDumb.precioServicio(PrecioServicioTerminal.LAVADOPESADO))
			.thenReturn(5000d);
		}

		
		/*
		 * El lavado es mas caro si supera el peso necesario
		 * */
		@Test
		public void lavadoMasCaroTest() {
		    
			assertEquals(1000d, miServLavado.costoServicio(terminalDumb,LocalDateTime.now()));
		}
		
		/*
		 * El lavado es mas barato si no supera el peso maximo
		 * */
		@Test
		public void lavadoBaratoTest() {
			
			assertNotEquals(5000d, miServLavado.costoServicio(terminalDumb,LocalDateTime.now()));
		}
		
		@Test
		public void tipoCargaTest() {
			assertEquals("Servicio Lavado",miServLavado.tipoServicio());
		}


}
