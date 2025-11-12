package ar.edu.unq.po2.buqueEstados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buque.estadosBuque.Departing;
import ar.edu.unq.po2.buque.estadosBuque.OutboundFinal;
import ar.edu.unq.po2.buque.estadosBuque.Working;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class DepartingTestCase {

	Buque buque;
	Coordenada coordenadaBuque;
	Coordenada coordenadaTerminal;
	
	Departing estadoBuque;
	TerminalPortuaria terminalAArribar;
	
	@BeforeEach
	void setUp() throws Exception {
		//terminal Mock con posicion 0,0 
		terminalAArribar = mock(TerminalPortuaria.class);
		coordenadaTerminal = spy(new Coordenada(0,0));
		when(terminalAArribar.coordenadasTerminal()).thenReturn(coordenadaTerminal);
		//se crea el buque con su posicion coherente con el de la terminal
		
		coordenadaBuque = new Coordenada(0d,0d);
		buque = new Buque(coordenadaBuque,null, null, "Matias");
		buque.adscribirObservador(terminalAArribar);

		//se crea el estado que tendra el buque
		estadoBuque= new Departing(buque);
        buque.establecerEstado(estadoBuque);		
        
		
		
		
	
	  }

	@Test
	void elBuqueAhoraPuedeMoverse() {
		estadoBuque.avanzar(2, 2);
		assertEquals(2d,buque.posicionActual().getLatitud());
		assertEquals(2d,buque.posicionActual().getLongitud());
	}
	
	/*
	 * 
	 * Cuando el buque se encuentra a mas de un kilometro puede cambiar de estado
	 * */
	@Test
	void elBuquePuedeCambiarDeEstado() {
		
		//
		estadoBuque.avanzar(0.5d,0.5d);
		assertFalse(estadoBuque.debeCambiarDeFase());
		
		//El buque se encuentra a mas de un kilometro y puede partir
		estadoBuque.avanzar(-1.5000d,-1.5000d);
		assertTrue(estadoBuque.debeCambiarDeFase());
		
		
	}
	
	/*
	 * Cuando el buque cambia de fase a Outbound
	 * */
	@Test
	void elBuqueNotificaALaTerminalTest() {
		estadoBuque.avanzar(-1.5000d,-1.5000d);
		estadoBuque.modificarEstadoBuque();
		
		verify(terminalAArribar,times(1)).notificarSalidaTerminal(buque);
		
		
	}
	
	@Test 
	void elBuqueCambiaDeFaseTest() {
		estadoBuque.avanzar(-1.5000d,-1.5000d);
		estadoBuque.modificarEstadoBuque();
		
		assertTrue(buque.obtenerEstado() instanceof OutboundFinal);
	}

}
