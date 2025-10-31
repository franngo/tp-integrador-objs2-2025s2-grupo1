package ar.edu.unq.po2.buscador_de_viaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.viaje.Viaje;

public class FiltroSimpleTest {
	
	/*
	 * me veo en la obligación de crear este "semi test double" del SUT, el cual invoca realmente a los métodos que son 
	 * concretos, ya que es la única forma de testear el método concreto losQueCumplen(), el cual llama a un método 
	 * abstracto que implementan las subclases.
	 * La idea es implantarle la respuesta únicamente para los mensajes relativos al método abstracto.
	 */

	FiltroSimple filtro = mock(FiltroSimple.class, CALLS_REAL_METHODS);
	List<Viaje> viajes = new ArrayList<Viaje>();
	Viaje viaje1 = mock(Viaje.class);
	Viaje viaje2 = mock(Viaje.class);
	Viaje viaje3 = mock(Viaje.class);
	
	@Test
	public void losQueCumplen() {
		when(filtro.cumpleFiltro(viaje1)).thenReturn(false);
		when(filtro.cumpleFiltro(viaje2)).thenReturn(true);
		when(filtro.cumpleFiltro(viaje3)).thenReturn(true);
		viajes.add(viaje1);
		viajes.add(viaje2);
		viajes.add(viaje3);
		List<Viaje> cumplen = filtro.losQueCumplen(viajes);
		verify(filtro).cumpleFiltro(viaje1);
		verify(filtro).cumpleFiltro(viaje2);
		verify(filtro).cumpleFiltro(viaje3);
		assertEquals(2, cumplen.size());
		assertFalse(cumplen.contains(viaje1));
		assertTrue(cumplen.contains(viaje2));
		assertTrue(cumplen.contains(viaje3));
	}

	
}
