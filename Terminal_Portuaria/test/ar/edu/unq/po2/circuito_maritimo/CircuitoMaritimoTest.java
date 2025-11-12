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
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);
		
		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);
		
		when(tramo3.getTerminalOrigen()).thenReturn(terminal3);
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);
		
		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);
	}
	
	@Test
	public void instanciacionDeCircuitoMaritimo() {
		
		//caso sin error en la validación validarTramos() (se inicia con los when del setUp())		

		assertDoesNotThrow( () -> { circuito = new CircuitoMaritimo(ts); } );
		
		//caso con error en la validación validarTramos() (se sobrescriben los when del setUp())
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);

		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);

		when(tramo3.getTerminalOrigen()).thenReturn(terminal1); //ruptura de la secuencia 
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);

		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);

		assertThrows(RuntimeException.class, () -> { circuito = new CircuitoMaritimo(ts); } );
		
		//caso con error por lista de tramos vacía
		
		List<Tramo> tsVacia = new ArrayList<Tramo>();
		
		assertThrows(RuntimeException.class, () -> { circuito = new CircuitoMaritimo(tsVacia); } );
		
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
		
		circuito = new CircuitoMaritimo(ts);
		
		assertEquals(terminal5, circuito.puertoDestino());
		
	}
	
	@Test
	public void esCircuitoQueUneA() {
		
		circuito = new CircuitoMaritimo(ts);
		
		TerminalPortuaria terminal6 = mock(TerminalPortuaria.class);
		
		assertFalse(circuito.esCircuitoQueUneA(terminal6, terminal2));
		assertFalse(circuito.esCircuitoQueUneA(terminal2, terminal6));
		assertFalse(circuito.esCircuitoQueUneA(terminal4, terminal2));
		assertTrue(circuito.esCircuitoQueUneA(terminal2, terminal4));
		
	}
	
	@Test
	public void tramosDesdeHasta() {
		
		circuito = new CircuitoMaritimo(ts);
		
		List<Tramo> tsEntre = circuito.tramosDesdeHasta(terminal2, terminal4);
		
		assertEquals(2, tsEntre.size());
		
		Tramo t1 = tsEntre.get(0);
		
		assertEquals(terminal2, t1.getTerminalOrigen());
		assertEquals(terminal3, t1.getTerminalDestino());
		
		Tramo t2 = tsEntre.get(1);
		
		assertEquals(terminal3, t2.getTerminalOrigen());
		assertEquals(terminal4, t2.getTerminalDestino());
		
	}
	
	@Test
	public void incluyeA() {
		
		circuito = new CircuitoMaritimo(ts);
		
		assertTrue(circuito.incluyeA(terminal1)); //se cumple this.tieneOrigen(terminal1)
		assertTrue(circuito.incluyeA(terminal5)); //se cumple this.tieneDestino(terminal5)
		
		TerminalPortuaria terminal6 = mock(TerminalPortuaria.class);
		
		assertFalse(circuito.incluyeA(terminal6)); //no se cumple this.tieneOrigen(terminal6) ni this.tieneDestino(terminal6)
		
	}
	
	//nuevos tests
	
	@Test
	public void tiempoEnTramosDesdeHasta() {
		
		circuito = new CircuitoMaritimo(ts);
		
		when(tramo1.getTiempoTotal()).thenReturn(Duration.ofHours(3));
		when(tramo2.getTiempoTotal()).thenReturn(Duration.ofHours(4));
		when(tramo3.getTiempoTotal()).thenReturn(Duration.ofHours(4));
		when(tramo4.getTiempoTotal()).thenReturn(Duration.ofHours(3));
		
		//assertEquals(Duration.ofHours(14), circuito.tiempoEnTramosDesdeHasta());
		
	}
	
	@Test
	public void precioEnTramosDesdeHasta() {
		
		when(tramo1.getPrecioTramo()).thenReturn(70.0);
		when(tramo2.getPrecioTramo()).thenReturn(30.0);
		when(tramo3.getPrecioTramo()).thenReturn(50.0);
		when(tramo4.getPrecioTramo()).thenReturn(10.5);
		
		circuito = new CircuitoMaritimo(ts);
		
		//assertEquals(160.5, circuito.precioEnTramosDesdeHasta());
		
	}
	
	@Test
	public void cantTerminalesEnTramosDesdeHasta() {
		
		circuito = new CircuitoMaritimo(ts);
		
		//assertEquals(3, circuito.cantTerminalesEnTramosDesdeHasta(terminal2, terminal4));
		
	}

}
