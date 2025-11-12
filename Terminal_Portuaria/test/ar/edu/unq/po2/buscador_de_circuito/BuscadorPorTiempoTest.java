package ar.edu.unq.po2.buscador_de_circuito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class BuscadorPorTiempoTest {
	
	//SUT
	BuscadorDeCircuito buscador = new BuscadorPorTiempo();
	
	//DOCs
	CircuitoMaritimo circuito1 = mock(CircuitoMaritimo.class);
	CircuitoMaritimo circuito2 = mock(CircuitoMaritimo.class);
	CircuitoMaritimo circuito3 = mock(CircuitoMaritimo.class);
	CircuitoMaritimo circuito4 = mock(CircuitoMaritimo.class);
	TerminalPortuaria gestionada = mock(TerminalPortuaria.class);
	TerminalPortuaria destino = mock(TerminalPortuaria.class);
	List<CircuitoMaritimo> circuitos = new ArrayList<CircuitoMaritimo>();
	
	@Test
	public void buscarCircuito() {
		
		when(circuito1.tiempoEnTramosDesdeHasta(gestionada, destino)).thenReturn(Duration.ofHours(2));
		when(circuito2.tiempoEnTramosDesdeHasta(gestionada, destino)).thenReturn(Duration.ofHours(4));
		when(circuito3.tiempoEnTramosDesdeHasta(gestionada, destino)).thenReturn(Duration.ofHours(6));
		when(circuito4.tiempoEnTramosDesdeHasta(gestionada, destino)).thenReturn(Duration.ofHours(1));
		circuitos.add(circuito1);
		circuitos.add(circuito2);
		circuitos.add(circuito3);
		circuitos.add(circuito4);
		
		assertEquals(circuito4, buscador.buscarMejorCircuito(circuitos, gestionada, destino));
		verify(circuito1, times(3)).tiempoEnTramosDesdeHasta(gestionada, destino);
		verify(circuito2, times(1)).tiempoEnTramosDesdeHasta(gestionada, destino);
		verify(circuito3, times(1)).tiempoEnTramosDesdeHasta(gestionada, destino);
		verify(circuito4, times(1)).tiempoEnTramosDesdeHasta(gestionada, destino);
		/*
		 * tener en cuenta que al que es el mínimo en un determinado momento del procesamiento se le manda el mensaje 
		 * nuevamente al momento de comparar con un nuevo elemento procesado del stream.
		 */
		
	}

}
