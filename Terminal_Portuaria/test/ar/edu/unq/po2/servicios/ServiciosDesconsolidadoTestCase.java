package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Dry;

import ar.edu.unq.po2.servicio.ServicioDesconsolidado;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/*
 * 
 * Autor: Matias Sanchez 
 * */
class ServiciosDesconsolidadoTestCase {
     
	TerminalPortuaria terminalDumb = mock(TerminalPortuaria.class);
	ServicioDesconsolidado miServConDry;

	
	Dry dumbDry;
	
	
	
	@BeforeEach
	void setUp(){
		dumbDry = mock(Dry.class);
		when(terminalDumb.precioServicio(any())).thenReturn(20000d);
		miServConDry= new ServicioDesconsolidado(dumbDry);
		
	}
	
	@Test
	public void costoDeServicioTest() {
		assertEquals(20000d,miServConDry.costoServicio(terminalDumb));
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio Desconsolidado",miServConDry.tipoServicio());
	}

	

}
