package ar.edu.unq.po2.buqueEstados;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buque.estadosBuque.EstadoBuque;
import ar.edu.unq.po2.buque.estadosBuque.Inbound;
import ar.edu.unq.po2.buque.estadosBuque.OutBound;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class OutboundTestCase {
     
	Buque buque;
	Coordenada coordenadaBuque;
	Coordenada coordenadaTerminal;
	
	OutBound estadoBuque;
	TerminalPortuaria terminalAArribar;
	@BeforeEach
	void setUp() throws Exception {
		coordenadaTerminal = spy(new Coordenada(0d,0d));
		//terminalAArribar = new TerminalPortuaria(new Coordenada(0d,0d));
		terminalAArribar = mock(TerminalPortuaria.class);
		when(terminalAArribar.coordenadasTerminal()).thenReturn(coordenadaTerminal);
		//se crea el buque con su posicion
		
		coordenadaBuque = new Coordenada(60d,60d);
		buque = new Buque(coordenadaBuque,null, null, "Matias");
		buque.adscribirObservador(terminalAArribar);
		
		//buque.terminalAArribar(terminalAArribar);
		
		//se crea el estado que tendra el buque
		estadoBuque= new OutBound(buque);
        buque.establecerEstado(estadoBuque);		
        
        //terminal Mock con posicion 0,0 
		
		
	
		
	}
	
	
    
	/*
	 * El buque no pasa de estado hasta que se acerque a la terminal
	 * */
	@Test
	void elBuqueSeMueveATravesDelEstadoTest() {
		
		//El estado no cambia porque no esta cerca de la terminal
		estadoBuque.avanzar(55d, 55d);
        assertEquals(buque.posicionActual().getLatitud(),55d);
        assertEquals(buque.posicionActual().getLongitud(),55d);
        assertTrue(buque.obtenerEstado() instanceof OutBound);
   
      }
	
	@Test
	void puedeCambiarEstadoTest() {
		//el buque aun no puede cambiar de fase porque se encuentra lejos de la terminal
		estadoBuque.avanzar(60d,60d);
		assertFalse(estadoBuque.debeCambiarDeFase());
		
		//el buque puede cambiar de fase porque se encuentra a menos de 50km de la terminal
		estadoBuque.avanzar(10d, 10d);
		assertTrue(estadoBuque.debeCambiarDeFase());
		
	}
	@Test
	void elBuqueCambiaDeFase() {
		estadoBuque.modificarEstadoBuque();
		
		assertTrue(buque.obtenerEstado() instanceof Inbound);
	}
	
	@Test
	void testEstadoBuqueGeneral() {
		assertEquals(terminalAArribar,estadoBuque.terminalAArribar());
		assertEquals(buque,estadoBuque.miBuque());
		
		/*
		EstadoBuque estadoGeneral = new OutBound(buque);
		buque.establecerEstado(estadoGeneral);
		estadoGeneral.modificarEstadoBuque();
		assertTrue(buque.obtenerEstado() instanceof EstadoBuque);
		*/
		
	}


}
