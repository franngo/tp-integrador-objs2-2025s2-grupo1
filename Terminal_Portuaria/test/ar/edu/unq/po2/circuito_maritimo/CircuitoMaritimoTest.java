package ar.edu.unq.po2.circuito_maritimo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.tramo.Tramo;

public class CircuitoMaritimoTest {
	
	//DOCs
	Tramo tramo1;
	Tramo tramo2;
	Tramo tramo3;
	Tramo tramo4;
	TerminalPortuaria terminal1;
	TerminalPortuaria terminal2;
	TerminalPortuaria terminal3;
	TerminalPortuaria terminal4;
	TerminalPortuaria terminal5;
	
	//SUT
	CircuitoMaritimo circuito;
	
	@BeforeEach
	public void setUp() {
		
		tramo1 = mock(Tramo.class);
		tramo2 = mock(Tramo.class);
		tramo3 = mock(Tramo.class);
		tramo4 = mock(Tramo.class);
		terminal1 = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		terminal3 = mock(TerminalPortuaria.class);
		terminal4 = mock(TerminalPortuaria.class);
		terminal5 = mock(TerminalPortuaria.class);
		
	}
	
	@Test
	public void instanciacionDeCircuitoMaritimo() {
		
		List<Tramo> ts = new ArrayList<Tramo>();
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);
		ts.add(tramo1);
		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);
		ts.add(tramo2);
		when(tramo3.getTerminalOrigen()).thenReturn(terminal3);
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);
		ts.add(tramo3);
		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);
		ts.add(tramo4);
		assertDoesNotThrow( () -> { circuito = new CircuitoMaritimo(ts); } );
		
		ts.clear();
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);
		ts.add(tramo1);
		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);
		ts.add(tramo2);
		when(tramo3.getTerminalOrigen()).thenReturn(terminal1); //ruptura de la secuencia 
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);
		ts.add(tramo3);
		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);
		ts.add(tramo4);
		assertThrows(RuntimeException.class, () -> { circuito = new CircuitoMaritimo(ts); } );
		
	}
	
	@Test
	public void tiempoTotal() {
		
	}
	
	@Test
	public void tiempoHastaTerminal() {
		
	}
	
	@Test
	public void precioTotal() {
		
	}
	
	@Test
	public void cantidadDeTerminales() {
		
	}
	
	@Test
	public void puertoDestino() {
		
	}

}
