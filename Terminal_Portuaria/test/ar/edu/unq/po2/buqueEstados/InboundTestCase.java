package ar.edu.unq.po2.buqueEstados;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buque.estadosBuque.Arrived;
import ar.edu.unq.po2.buque.estadosBuque.Inbound;

import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class InboundTestCase {
    
	  
		Buque buque;
		Coordenada coordenadaBuque;
		Coordenada coordenadaTerminal;
		
		Inbound estadoBuque;
		TerminalPortuaria terminalAArribar;
		
		@BeforeEach
		void setUp()  {
			terminalAArribar = mock(TerminalPortuaria.class);
			coordenadaTerminal = spy(new Coordenada(0,0));
			when(terminalAArribar.getCoordenada()).thenReturn(coordenadaTerminal);
			//se crea el buque con su posicion
			coordenadaBuque = new Coordenada(10d,20d);
			buque = new Buque(coordenadaBuque,null,"Matias");
			buque.adscribirObservador(terminalAArribar);
			//se crea el estado que tendra el buque
			estadoBuque= new Inbound(buque);
	        buque.establecerEstado(estadoBuque);		
	        
	        //terminal Mock con posicion 0,0 
			
			
			
		
		}

	
		@Test
	void elBuqueNotificaArriboTest() {
			//el buque notifica exaxtamente una sola vez
		estadoBuque.notificarEstado();
		verify(terminalAArribar,times(1)).notificarArribo(buque);
		
		//la terminal solo recibio 1 sola vez la notificacion
		estadoBuque.notificarEstado();
		estadoBuque.notificarEstado();
		estadoBuque.notificarEstado();
		
		verify(terminalAArribar,times(1)).notificarArribo(buque);

     }
		
	void puedeCambiarDeFaseTest() {
		//el buque llega a las mismas coordenadas que la terminal
		estadoBuque.avanzar(0, 0);
		assertTrue(estadoBuque.debeCambiarDeFase());
	}
	
	void elEstadoCambiaDeFase() {
		estadoBuque.modificarEstadoBuque();
		assertTrue(buque.obtenerEstado() instanceof Arrived);
	}
}