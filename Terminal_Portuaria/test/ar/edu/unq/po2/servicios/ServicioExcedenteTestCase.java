package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Reefer;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;
import ar.edu.unq.po2.servicio.ServicioExcedente;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioExcedenteTestCase {

	TerminalPortuaria terminalDumb = mock(TerminalPortuaria.class);
	ServicioExcedente miServExcedente;

	LocalDateTime horaInicioCobro;
	Reefer dumbContainer;
	
	
	@BeforeEach
	void setUp(){
		horaInicioCobro = LocalDateTime.of(2025,11,10,8,30);
		dumbContainer = mock(Reefer.class);
		when(dumbContainer.getConsumoPorHora()).thenReturn(10d);
		when(terminalDumb.precioServicio(PrecioServicioTerminal.DIAEXCEDENTE)).thenReturn(20000d);
		miServExcedente = new ServicioExcedente(dumbContainer,horaInicioCobro);
		
	}
	
	@Test
	public void costoDeServicioTest() {
		//TODO MODIFICAR
		assertEquals(0d,miServExcedente.costoServicio(terminalDumb));
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio Excedente",miServExcedente.tipoServicio());
	}
}
