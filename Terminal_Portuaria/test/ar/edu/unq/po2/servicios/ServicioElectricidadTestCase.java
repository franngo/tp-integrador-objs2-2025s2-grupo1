package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import ar.edu.unq.po2.container.Reefer;

import ar.edu.unq.po2.servicio.ServicioElectricidad;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ServicioElectricidadTestCase {
   
	TerminalPortuaria terminalDumb = mock(TerminalPortuaria.class);
	ServicioElectricidad miServElectricidad;

	
	Reefer dumbContainer;
	LocalDateTime horaInicioServicio;
	
	
	@BeforeEach
	void setUp(){
		horaInicioServicio = LocalDateTime.of(2025, 11, 10, 8, 30);
		dumbContainer = mock(Reefer.class);
		when(dumbContainer.getConsumoPorHora()).thenReturn(10d);
		when(terminalDumb.precioServicio(any())).thenReturn(20d);
		miServElectricidad = new ServicioElectricidad(dumbContainer,horaInicioServicio);
		
	}
	
	
	/*
	 * 
	 * Los costos son coherentes con las horas que estuvo el Container
	 * */
	@Test
	public void costoDeServicioTest() {
		
		
		double precioPor3Horas = dumbContainer.getConsumoPorHora() 
				                 * terminalDumb.precioServicio(any())
				                 * 3; 
		double precioPor10Horas = dumbContainer.getConsumoPorHora() 
                * terminalDumb.precioServicio(any())
                * 10;
		//2 horas despues que el container llega a la terminal 
		LocalDateTime horaCobro3 = LocalDateTime.of(2025,11,10,11,30);
		
		//10 horas despues que el container llega a la terminal 
		LocalDateTime horaCobro10 = LocalDateTime.of(2025,11,10,18,30);
		
		assertEquals(precioPor3Horas,miServElectricidad.costoServicio(terminalDumb,horaCobro3));
		
		assertEquals(precioPor10Horas,miServElectricidad.costoServicio(terminalDumb,horaCobro10));
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio Electricidad",miServElectricidad.tipoServicio());
	}

}
