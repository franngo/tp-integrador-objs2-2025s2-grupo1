package ar.edu.unq.po2.buqueEstados;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
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
		//se crea el buque con su posicion
		coordenadaBuque = new Coordenada(10d,20d);
		buque = new Buque(coordenadaBuque,null,"Matias");
		//se crea el estado que tendra el buque
		estadoBuque= new OutBound(buque);
        buque.establecerEstado(estadoBuque);		
        
        //terminal Mock con posicion 0,0 
		terminalAArribar = mock(TerminalPortuaria.class);
		coordenadaTerminal = spy(new Coordenada(0,0));
		when(terminalAArribar.getCoordenada()).thenReturn(coordenadaTerminal);
		
		
		
	
	     buque.adscribirObservador(terminalAArribar);
        
		
	}
	
	
    
	/*
	 * El buque no pasa de estado hasta que se acerque a la terminal
	 * */
	@Test
	void elBuqueSeMueveATravesDelEstadoTest() {
		
		//El estado no cambia porque no esta cerca de la terminal
		estadoBuque.avanzar(20d, 20d);
        assertEquals(buque.posicionActual().getLatitud(),20d);
        assertEquals(buque.posicionActual().getLongitud(),20d);
      //  Assert.assertTrue(buque.obtenerEstado() instanceof OutBound);
        
      }
	
	void puedeCambiarEstadoTest() {
		//el buque aun no puede cambiar de fase porque se encuentra lejos de la terminal
		estadoBuque.avanzar(51d,51d);
		assertFalse(estadoBuque.debeCambiarDeFase());
		
		//el buque puede cambiar de fase porque se encuentra a menos de 50km de la terminal
		estadoBuque.avanzar(49d, 49d);
		assertTrue(estadoBuque.debeCambiarDeFase());
		
	}
	
	void elBuqueCambiaDeFase() {
		estadoBuque.modificarEstadoBuque();
		
		assertTrue(buque.obtenerEstado() instanceof Inbound);
	}
	
	void notificarEstado() {
		//el buque no hace nada TODO implementar 
	assertTrue(true);
		
	}

}
