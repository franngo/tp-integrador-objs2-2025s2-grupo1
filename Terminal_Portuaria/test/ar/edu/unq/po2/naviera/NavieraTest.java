package ar.edu.unq.po2.naviera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;
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
	
	//DOCs (que no son test doubles)
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
	public void publicarViaje() {
		
		//caso con error donde no se puede publicar el viaje
		
		CircuitoMaritimo circuito4 = mock(CircuitoMaritimo.class); //circuito que NO está en la lista de circuitos de naviera
		
		assertThrows(RuntimeException.class, () -> { naviera.publicarViaje(LocalDateTime.now(), circuito4, buque1); } );
		
		Buque buque4 = mock(Buque.class); //buque que NO está en la lista de buques de naviera
		
		assertThrows(RuntimeException.class, () -> { naviera.publicarViaje(LocalDateTime.now(), circuito1, buque4); } );
		
		assertThrows(RuntimeException.class, () -> { naviera.publicarViaje(LocalDateTime.now(), circuito4, buque4); } );
		
		//caso sin error
		
		assertEquals(0, naviera.cronograma().size());
		
		naviera.publicarViaje(LocalDateTime.now(), circuito2, buque3);
		
		assertEquals(1, naviera.cronograma().size());
		
	}
	
	@Test
	public void circuitosQueUnan() {
		
		TerminalPortuaria t1 = mock(TerminalPortuaria.class);
		TerminalPortuaria t2 = mock(TerminalPortuaria.class);
		
		when(circuito1.esCircuitoQueUneA(t1, t2)).thenReturn(true);
		when(circuito2.esCircuitoQueUneA(t1, t2)).thenReturn(false);
		when(circuito3.esCircuitoQueUneA(t1, t2)).thenReturn(true);
		
		List<CircuitoMaritimo> csCumplen = naviera.circuitosQueUnan(t1, t2);
		
		assertEquals(2, csCumplen.size());
		
		assertTrue(csCumplen.contains(circuito1));
		assertFalse(csCumplen.contains(circuito2));
		assertTrue(csCumplen.contains(circuito3));
		
	}
	
	@Test
	public void viajesQueIncluyenOrigen() {
		
		naviera.publicarViaje(LocalDateTime.now(), circuito1, buque3);
		naviera.publicarViaje(LocalDateTime.now(), circuito2, buque2);
		naviera.publicarViaje(LocalDateTime.now(), circuito3, buque1);
		
		List<Viaje> vs = naviera.cronograma();
		
		Viaje v1 = vs.get(0);
		Viaje v2 = vs.get(1);
		Viaje v3 = vs.get(2);
		
		TerminalPortuaria t1 = mock(TerminalPortuaria.class);
		
		when(v1.tieneOrigen(t1)).thenReturn(true);
		when(v2.tieneOrigen(t1)).thenReturn(false);
		when(v3.tieneOrigen(t1)).thenReturn(true);
		
		List<Viaje> vsCumplen = naviera.viajesQueIncluyenOrigen(t1);
		
		assertEquals(2, vsCumplen.size());
		
		assertTrue(vsCumplen.contains(v1));
		assertFalse(vsCumplen.contains(v2));
		assertTrue(vsCumplen.contains(v3));
		
	}
	
	@Test
	public void tiempoEntre() {
		
		TerminalPortuaria t1 = mock(TerminalPortuaria.class);
		TerminalPortuaria t2 = mock(TerminalPortuaria.class);
		
		when(circuito1.esCircuitoQueUneA(t1, t2)).thenReturn(true);
		when(circuito2.esCircuitoQueUneA(t1, t2)).thenReturn(false);
		when(circuito3.esCircuitoQueUneA(t1, t2)).thenReturn(true);
		
		when(circuito1.tiempoEnTramosDesdeHasta(t1, t2)).thenReturn(Duration.ofHours(10));
		when(circuito3.tiempoEnTramosDesdeHasta(t1, t2)).thenReturn(Duration.ofHours(8));
		
		assertEquals(Duration.ofHours(8), naviera.tiempoEntre(t1, t2));
		
	}
	
	@Test
	public void viajesQueUnanConBuque() {
		
		naviera.publicarViaje(LocalDateTime.now(), circuito1, buque3);
		naviera.publicarViaje(LocalDateTime.now(), circuito2, buque2);
		naviera.publicarViaje(LocalDateTime.now(), circuito3, buque1);
		
		List<Viaje> vs = naviera.cronograma();
		
		Viaje v1 = vs.get(0);
		Viaje v2 = vs.get(1);
		Viaje v3 = vs.get(2);
		
		TerminalPortuaria t1 = mock(TerminalPortuaria.class);
		
		when(v1.tieneOrigen(t1)).thenReturn(true);
		when(v2.tieneOrigen(t1)).thenReturn(false);
		when(v3.tieneOrigen(t1)).thenReturn(true);
		
		List<Viaje> vsCumplen = naviera.viajesQueIncluyenOrigen(t1);
		
		assertEquals(2, vsCumplen.size());
		
		assertTrue(vsCumplen.contains(v1));
		assertFalse(vsCumplen.contains(v2));
		assertTrue(vsCumplen.contains(v3));
		
	}

}
