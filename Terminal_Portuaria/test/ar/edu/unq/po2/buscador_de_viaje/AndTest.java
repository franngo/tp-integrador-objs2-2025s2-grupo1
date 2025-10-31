package ar.edu.unq.po2.buscador_de_viaje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.viaje.Viaje;

public class AndTest {
	
	Condicion cond1 = mock(Condicion.class);
	Condicion cond2 = mock(Condicion.class);
	And and = new And(cond1, cond2);
	
	List<Viaje> viajes = new ArrayList<Viaje>();
	Viaje viaje1 = mock(Viaje.class);
	Viaje viaje2 = mock(Viaje.class);
	Viaje viaje3 = mock(Viaje.class);
	
	List<Viaje> cumplenIzq = new ArrayList<Viaje>();
	List<Viaje> cumplenDer = new ArrayList<Viaje>();
	
	@Test
	public void losQueCumplen() {
		viajes.add(viaje1);
		viajes.add(viaje2);
		viajes.add(viaje3);
		
		cumplenIzq.add(viaje2);
		cumplenDer.add(viaje2);
		cumplenDer.add(viaje3);
		
		when(cond1.losQueCumplen(viajes)).thenReturn(cumplenIzq);
		when(cond2.losQueCumplen(viajes)).thenReturn(cumplenDer);
		
		List<Viaje> cumplen = and.losQueCumplen(viajes);
		verify(cond1).losQueCumplen(viajes);
		verify(cond2).losQueCumplen(viajes);
		
		assertEquals(1, cumplen.size());
		assertFalse(cumplen.contains(viaje1));
		assertTrue(cumplen.contains(viaje2));
		assertFalse(cumplen.contains(viaje3));
	}

}
