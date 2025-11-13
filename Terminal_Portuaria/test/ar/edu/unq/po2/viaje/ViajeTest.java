package ar.edu.unq.po2.viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ViajeTest {
	
	
	//auxiliares
	LocalDateTime fecha;
	TerminalPortuaria terminal5;
	
	//DOCs
	CircuitoMaritimo circuito;
	Buque buque;
	
	//SUT
	Viaje viaje;
	
	@BeforeEach
	public void setUp() {
		
		fecha = LocalDateTime.of(2025, 10, 1, 15, 30); //1/10/2025 a las 15:30hs (no la mockeo porque es clase de la API!)
		circuito = mock(CircuitoMaritimo.class);
		buque = mock(Buque.class);
		
		viaje = new Viaje(fecha, circuito, buque);
		
		terminal5 = mock(TerminalPortuaria.class);
		
	}
	
	@Test
	public void fechaDeSalida() {
		
		assertEquals(fecha, viaje.fechaDeSalida());
		
	}
	
	@Test
	public void fechaDeLlegada() {
		
		when(circuito.tiempoTotal()).thenReturn(Duration.ofDays(2));
		
		assertEquals(LocalDateTime.of(2025, 10, 3, 15, 30), viaje.fechaDeLlegada());
		
	}
	
	@Test
	public void fechaDeLlegadaATerminal() {
		
		when(circuito.tiempoHastaTerminal(terminal5)).thenReturn(Duration.ofHours(8));
		
		assertEquals(LocalDateTime.of(2025, 10, 1, 23, 30), viaje.fechaDeLlegadaATerminal(terminal5));
		
	}
	
	@Test
	public void puertoDestino() {
		
		when(circuito.puertoDestino()).thenReturn(terminal5);
		
		assertEquals(terminal5, viaje.puertoDestino());
		
	}
	
	@Test
	public void proximoDestino() {
		
		TerminalPortuaria terminal4 = mock(TerminalPortuaria.class);
		
		when(buque.terminalAArribar()).thenReturn(terminal4);
		when(circuito.proximoDestino(terminal4)).thenReturn(terminal5);
		
		assertEquals(terminal5, viaje.proximoDestino());
		
	}
	
	@Test
	public void tieneOrigen() {
		
		when(circuito.tieneOrigen(terminal5)).thenReturn(true);
		
		assertTrue(viaje.tieneOrigen(terminal5));
		
	}

}
