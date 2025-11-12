package ar.edu.unq.po2.buqueEstados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buque.estadosBuque.Arrived;

import ar.edu.unq.po2.buque.estadosBuque.Working;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ArrivedTestCase {

	Buque buque;
	Coordenada coordenadaBuque;
	Coordenada coordenadaTerminal;
	
	Arrived estadoBuque;
	TerminalPortuaria terminalAArribar;
	@BeforeEach
	void setUp() throws Exception {
		//se crea el buque con su posicion coherente con el de la terminal
		
		coordenadaBuque = new Coordenada(0d,0d);
		buque = new Buque(coordenadaBuque,null,"Matias");
		//se crea el estado que tendra el buque
		estadoBuque= new Arrived(buque);
        buque.establecerEstado(estadoBuque);		
        
        //terminal Mock con posicion 0,0 
		terminalAArribar = mock(TerminalPortuaria.class);
		coordenadaTerminal = spy(new Coordenada(0,0));
		when(terminalAArribar.coordenadasTerminal()).thenReturn(coordenadaTerminal);
		
		
		
	
	     buque.adscribirObservador(terminalAArribar);
        
		
	}
	
	/*
	 * El buque no peude cambiar de fase hasta que la terminal de visto bueno
	 * */
	@Test
	void elBuqueNopuedeCambiarDeFaseTest() {
		
		assertFalse(estadoBuque.debeCambiarDeFase());
		
		// este metodo lo llama la terminal
		estadoBuque.puedeIniciarWorking();
		
		assertTrue(estadoBuque.debeCambiarDeFase());
		
		
	}
	
	/*
	 * el buque no puede cambiar sus coordenadas 
	 * */
	 @Test
     void elBuqueNoCambiaCoordenadasTest() {
		 estadoBuque.avanzar(100d, 100d);
		 
		 assertEquals(0d, buque.posicionActual().getLatitud());
		 assertEquals(0d, buque.posicionActual().getLongitud());
		 
		 
	 }
	 
	 @Test
	 void elBuquePasaAEstadoWorking() {
		 estadoBuque.modificarEstadoBuque();
		 
		 assertTrue(buque.obtenerEstado() instanceof Working);
	 }

}
