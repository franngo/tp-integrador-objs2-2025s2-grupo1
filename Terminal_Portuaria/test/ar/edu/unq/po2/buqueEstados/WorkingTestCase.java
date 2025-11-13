package ar.edu.unq.po2.buqueEstados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buque.estadosBuque.Working;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class WorkingTestCase {

	Buque buque;
	Coordenada coordenadaBuque;
	Coordenada coordenadaTerminal;
	
	Working estadoBuque;
	TerminalPortuaria terminalAArribar;
	
	@BeforeEach
	void setUp() throws Exception {
		//se crea el buque con su posicion coherente con el de la terminal
		
		coordenadaBuque = new Coordenada(0d,0d);
		buque = new Buque(coordenadaBuque,null, "Matias");
		//se crea el estado que tendra el buque
		estadoBuque= new Working(buque);
        buque.establecerEstado(estadoBuque);		
        
        //terminal Mock con posicion 0,0 
		terminalAArribar = mock(TerminalPortuaria.class);
		coordenadaTerminal = spy(new Coordenada(0,0));
		when(terminalAArribar.getCoordenada()).thenReturn(coordenadaTerminal);
		
		
		
	
	     buque.adscribirObservador(terminalAArribar);
	  }

	@Test
	void elBuqueNocambiaPosicionEnEstadoWorking() {
	      estadoBuque.avanzar(100d, 100d);
		 
		 assertEquals(0d, buque.posicionActual().getLatitud());
		 assertEquals(0d, buque.posicionActual().getLongitud());
	}
	
	@Test
	void elBuqueNoParteSinConfirmacionTest() {
		
		//el buque no puede cambiar de fase hasta que se le confirme la partida
		assertFalse(estadoBuque.debeCambiarDeFase());
		
		estadoBuque.puedePartir();
		
		assertTrue(estadoBuque.debeCambiarDeFase());
	}
	
	void elBuqueCambiaDeEstado() {
          estadoBuque.modificarEstadoBuque();
		 
		 assertTrue(buque.obtenerEstado() instanceof Working);
	}

}
