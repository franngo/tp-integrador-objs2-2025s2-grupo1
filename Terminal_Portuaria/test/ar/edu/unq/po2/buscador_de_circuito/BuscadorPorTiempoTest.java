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

public class BuscadorPorTiempoTest {
	
	BuscadorDeCircuito buscador = new BuscadorPorTiempo();
	CircuitoMaritimo circuito1 = mock(CircuitoMaritimo.class);
	CircuitoMaritimo circuito2 = mock(CircuitoMaritimo.class);
	CircuitoMaritimo circuito3 = mock(CircuitoMaritimo.class);
	CircuitoMaritimo circuito4 = mock(CircuitoMaritimo.class);
	List<CircuitoMaritimo> circuitos = new ArrayList<CircuitoMaritimo>();
	
	@Test
	public void buscarCircuito() {
		
		when(circuito1.tiempoTotal()).thenReturn(Duration.ofHours(2));
		when(circuito2.tiempoTotal()).thenReturn(Duration.ofHours(4));
		when(circuito3.tiempoTotal()).thenReturn(Duration.ofHours(6));
		when(circuito4.tiempoTotal()).thenReturn(Duration.ofHours(1));
		circuitos.add(circuito1);
		circuitos.add(circuito2);
		circuitos.add(circuito3);
		circuitos.add(circuito4);
		
		assertEquals(circuito4, buscador.buscarMejorCircuito(circuitos));
		verify(circuito1, times(3)).tiempoTotal();
		verify(circuito2, times(1)).tiempoTotal();
		verify(circuito3, times(1)).tiempoTotal();
		verify(circuito4, times(1)).tiempoTotal();
		/*
		 * tener en cuenta que al que es el mínimo en un determinado momento del procesamiento se le manda el mensaje 
		 * nuevamente al momento de comparar con un nuevo elemento procesado del stream.
		 */
		
	}

}
