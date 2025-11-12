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
		when(terminalDumb.limiteHorasAlmacenaje()).thenReturn(24d);
		miServExcedente = new ServicioExcedente(dumbContainer,horaInicioCobro);
		
	}
	/*
	
	@Test
	public void seCompruebaSiSeCobraServicio() {
		
		LocalDateTime ceroDiasDeAlmacenaje = LocalDateTime.of(2025,11,10,10,30);
		LocalDateTime dosDiasDeAlmacenaje  = LocalDateTime.of(2025,11,14,11,30);
		
		//Si no se pasa de 24 horas no se le cobra servicio
		//assertFalse(miServExcedente.excedioTiempoDeRetiro(ceroDiasDeAlmacenaje,terminalDumb));
		
		
		//Si se pasa si se le debe cobrar servicio
		assertTrue(miServExcedente.excedioTiempoDeRetiro(dosDiasDeAlmacenaje,terminalDumb));
	}*/
	
	@Test
	public void costoDeServicioTest() {
		LocalDateTime ceroDiasDeAlmacenaje = LocalDateTime.of(2025,11,10,10,30);
		LocalDateTime dosDiasDeAlmacenaje  = LocalDateTime.of(2025,11,12,11,30);
		
		//No se cobra nada porque no se paso de 24 horas
		assertEquals(0d,miServExcedente.costoServicio(terminalDumb, ceroDiasDeAlmacenaje));
		//se cobra por 2 dias de almacenaje
		assertEquals(40000d,miServExcedente.costoServicio(terminalDumb, dosDiasDeAlmacenaje));
		
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio Excedente",miServExcedente.tipoServicio());
	}
}
