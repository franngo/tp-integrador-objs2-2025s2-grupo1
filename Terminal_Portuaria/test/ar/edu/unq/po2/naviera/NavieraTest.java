package ar.edu.unq.po2.naviera;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.tramo.Tramo;
import ar.edu.unq.po2.buque.Buque;

public class NavieraTest {
	
	//DOCs
	CircuitoMaritimo circuito1;
	CircuitoMaritimo circuito2;
	CircuitoMaritimo circuito3;
	
	Buque buque1;
	Buque buque2;
	Buque buque3;
	
	//SUT
	Naviera naviera;
	
	//auxiliar
	List<CircuitoMaritimo> cs;
	List<Buque> bs;
	
	@BeforeEach
	public void setUp() {
		
		cs = new ArrayList<CircuitoMaritimo>();
		bs = new ArrayList<Buque>();
		
		circuito1 = mock(CircuitoMaritimo.class);
		circuito2 = mock(CircuitoMaritimo.class);
		circuito3 = mock(CircuitoMaritimo.class);
		
		buque1 = mock(Buque.class);
		buque2 = mock(Buque.class);
		buque3 = mock(Buque.class);
		
		cs.add(circuito1);
		cs.add(circuito2);
		cs.add(circuito3);
		
		bs.add(buque1);
		bs.add(buque2);
		bs.add(buque3);
		
		naviera = new Naviera(cs, bs);
		
	}
	
	@Test
	public void cronograma() {
		
		//innecesario?
		
	}
	
	@Test
	public void publicarViaje() {
		
		/*
		
		when(tramo1.getTerminalOrigen()).thenReturn(terminal1);
		when(tramo1.getTerminalDestino()).thenReturn(terminal2);
		
		when(tramo2.getTerminalOrigen()).thenReturn(terminal2);
		when(tramo2.getTerminalDestino()).thenReturn(terminal3);
		
		when(tramo3.getTerminalOrigen()).thenReturn(terminal3);
		when(tramo3.getTerminalDestino()).thenReturn(terminal4);
		
		when(tramo4.getTerminalOrigen()).thenReturn(terminal4);
		when(tramo4.getTerminalDestino()).thenReturn(terminal5);
		
		circuito = new CircuitoMaritimo(ts);
		
		TerminalPortuaria terminal6 = mock(TerminalPortuaria.class);
		
		assertFalse(circuito.esCircuitoQueUneA(terminal6, terminal2));
		assertFalse(circuito.esCircuitoQueUneA(terminal2, terminal6));
		assertFalse(circuito.esCircuitoQueUneA(terminal4, terminal2));
		assertTrue(circuito.esCircuitoQueUneA(terminal2, terminal4));
		
		*/
		
	}
	
	@Test
	public void circuitosQueUnan() {
		

		
	}

}
