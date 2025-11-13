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
		//terminal Mock con posicion 0,0 
		terminalAArribar = mock(TerminalPortuaria.class);
		coordenadaTerminal = spy(new Coordenada(0,0));
		when(terminalAArribar.getCoordenada()).thenReturn(coordenadaTerminal);
		//se crea el buque con su posicion coherente con el de la terminal
		
		coordenadaBuque = new Coordenada(0d,0d);
		buque = new Buque(coordenadaBuque,null,"Matias");
		buque.adscribirObservador(terminalAArribar);

		//se crea el estado que tendra el buque
		estadoBuque= new OutboundFinal(buque);
        buque.establecerEstado(estadoBuque);		
	}

	@Test
	void funcionamientoGeneral() {
		estadoBuque.notificarEstado();
		
		assertFalse(estadoBuque.debeCambiarDeFase());
		
		estadoBuque.modificarEstadoBuque();
		estadoBuque.avanzar(400d, 104d);
	}
}