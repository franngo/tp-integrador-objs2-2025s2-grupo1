package ar.edu.unq.po2.circuito_maritimo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
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
	
	//auxiliar
	List<Tramo> ts = new ArrayList<Tramo>();
	
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
		
		ts.add(tramo1);
		ts.add(tramo2);
		ts.add(tramo3);
		ts.add(tramo4);
	}
	
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
	
	@Test
	public void tiempoTotal() {
		
		circuito = new CircuitoMaritimo(ts);
		
		when(tramo1.getTiempoTotal()).thenReturn(Duration.ofHours(3));
		when(tramo2.getTiempoTotal()).thenReturn(Duration.ofHours(4));
		when(tramo3.getTiempoTotal()).thenReturn(Duration.ofHours(4));
		when(tramo4.getTiempoTotal()).thenReturn(Duration.ofHours(3));
		
		assertEquals(Duration.ofHours(14), circuito.tiempoTotal());
		
	}
	
	@Test
	public void tiempoHastaTerminal() {
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);
		
		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);
		
		when(tramo3.getTerminalOrigen()).thenReturn(terminal3);
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);
		
		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);
		
		circuito = new CircuitoMaritimo(ts);
		
		//caso con error en la validación validarSiEsDestino()
		
		TerminalPortuaria terminal6 = mock(TerminalPortuaria.class);
		
		assertThrows(RuntimeException.class, () -> { circuito.tiempoHastaTerminal(terminal6); } );
		
		//caso sin error en la validación validarSiEsDestino()
		
		when(tramo1.getTiempoTotal()).thenReturn(Duration.ofHours(3));
		when(tramo2.getTiempoTotal()).thenReturn(Duration.ofHours(4));
		when(tramo3.getTiempoTotal()).thenReturn(Duration.ofHours(4));
		when(tramo4.getTiempoTotal()).thenReturn(Duration.ofHours(3));
		
		assertEquals(Duration.ofHours(3), circuito.tiempoHastaTerminal(terminal2));
		assertEquals(Duration.ofHours(7), circuito.tiempoHastaTerminal(terminal3));
		assertEquals(Duration.ofHours(11), circuito.tiempoHastaTerminal(terminal4));
		assertEquals(Duration.ofHours(14), circuito.tiempoHastaTerminal(terminal5));
		
	}
	
	@Test
	public void precioTotal() {
		
		when(tramo1.getPrecioTramo()).thenReturn(70.0);
		when(tramo2.getPrecioTramo()).thenReturn(30.0);
		when(tramo3.getPrecioTramo()).thenReturn(50.0);
		when(tramo4.getPrecioTramo()).thenReturn(10.5);
		
		circuito = new CircuitoMaritimo(ts);
		
		assertEquals(160.5, circuito.precioTotal());
		
	}
	
	@Test
	public void cantidadDeTerminales() {
		
		circuito = new CircuitoMaritimo(ts);
		
		assertEquals(5, circuito.cantidadDeTerminales());
		
	}
	
	@Test
	public void puertoDestino() {
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);
		
		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);
		
		when(tramo3.getTerminalOrigen()).thenReturn(terminal3);
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);
		
		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);
		
		circuito = new CircuitoMaritimo(ts);
		
		assertEquals(terminal5, circuito.puertoDestino());
		
	}

}
