package ar.edu.unq.po2.buqueEstados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buque.estadosBuque.OutBound;
import ar.edu.unq.po2.buque.estadosBuque.OutboundFinal;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class OutboundFinalTestCase {
	Buque buque;
	Coordenada coordenadaBuque;
	Coordenada coordenadaTerminal;
	
	OutboundFinal estadoBuque;
	TerminalPortuaria terminalAArribar;
	@BeforeEach
	void setUp() throws Exception {
		
	//	coordenadaTerminal = spy(new Coordenada(0d,0d));
		//terminalAArribar = new TerminalPortuaria(new Coordenada(0d,0d));
	//	terminalAArribar = mock(TerminalPortuaria.class);
	//	when(terminalAArribar.coordenadasTerminal()).thenReturn(coordenadaTerminal);
		//se crea el buque con su posicion
		
		coordenadaBuque = new Coordenada(60d,60d);
		buque = new Buque(coordenadaBuque,null, null, "Matias");
		//buque.adscribirObservador(terminalAArribar);
		
		//buque.terminalAArribar(terminalAArribar);
		
		//se crea el estado que tendra el buque
		estadoBuque= new OutboundFinal(buque);
        buque.establecerEstado(estadoBuque);	
	}

	@Test
	void elBuqueYaNoCambiaDeFaseTest() {
	assertFalse(estadoBuque.debeCambiarDeFase());
	}
	
	@Test
	void elBuqueSeMueveLibremente() {
		estadoBuque.avanzar(30d, 30d);
		assertEquals(30d,buque.posicionActual().getLatitud());
		assertEquals(30d,buque.posicionActual().getLongitud());
		
	}

}
