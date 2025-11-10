package ar.edu.unq.po2.viaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ViajeTest {
	
	
	//auxiliares
	LocalDateTime fecha;
	
	//DOCs
	CircuitoMaritimo circuito;
	Buque buque;
	
	//SUT
	Viaje viaje;
	
	@BeforeEach
	public void setUp() {
		
		fecha = LocalDateTime.of(2025, 10, 1, 15, 30); //1/10/2025 a las 15:30hs
		circuito = mock(CircuitoMaritimo.class);
		buque = mock(Buque.class);
		
		//terminal5 = mock(TerminalPortuaria.class);
		
		viaje = new Viaje(fecha, circuito, buque);
		
	}
	
	/*
	esto no!
	
	@Test
	public void instanciacionDeCircuitoMaritimo() {
		
		//caso sin error en la validación validarTramos()
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);
		
		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);
		
		when(tramo3.getTerminalOrigen()).thenReturn(terminal3);
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);
		
		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);
		
		assertDoesNotThrow( () -> { circuito = new CircuitoMaritimo(ts); } );
		
		//caso con error en la validación validarTramos()
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);

		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);

		when(tramo3.getTerminalOrigen()).thenReturn(terminal1); //ruptura de la secuencia 
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);

		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);

		assertThrows(RuntimeException.class, () -> { circuito = new CircuitoMaritimo(ts); } );
		
	}
	
	*/
	
	@Test
	public void fechaDeLlegada() {
		
	}
	
	@Test
	public void fechaDeLlegadaATerminal() {
		
	}
	
	@Test
	public void puertoDestino() {
		
	}

}
