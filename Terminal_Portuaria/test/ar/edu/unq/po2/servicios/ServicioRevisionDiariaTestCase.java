package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

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

	LocalDateTime horaInicioServicio;
	Tanque dumbContainer;
	
	
	@BeforeEach
	void setUp(){
		horaInicioServicio = LocalDateTime.of(2025,10,10,8,30);
		 terminalDumb = mock(TerminalPortuaria.class);
		dumbContainer = mock(Tanque.class);
		
		when(terminalDumb.precioServicio(PrecioServicioTerminal.REVISIONDIARIA)).thenReturn(20d);
		miServRevisioDiaria = new ServicioRevisionDiaria(dumbContainer,horaInicioServicio);
		
	}
	
	@Test
	public void costoDeServicioTest() {
		double costoPor3Dias = 60d;
		double costoPor10Dias = 200d;
		
		LocalDateTime tresDias = LocalDateTime.of(2025,10,13,9,30);
		LocalDateTime diezDias = LocalDateTime.of(2025,10,20,9,30);
		
		//costo por3 dias
		assertEquals(costoPor3Dias,miServRevisioDiaria.costoServicio(terminalDumb,tresDias));
		
		//costo por 10 dias
		assertEquals(costoPor10Dias,miServRevisioDiaria.costoServicio(terminalDumb,diezDias));
	}
	
	@Test
	public void tipoServicioTest() {
		assertEquals("Servicio revision Diaria",miServRevisioDiaria.tipoServicio());
	}


}
