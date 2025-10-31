package ar.edu.unq.po2.buscador_de_viaje;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.viaje.Viaje;

public class FiltroFechaLlegadaTest {
	
	LocalDate fecha = LocalDate.now();
	FiltroFechaLlegada filtro = new FiltroFechaLlegada(fecha);
	Viaje viaje = mock(Viaje.class);
	
	@Test
	public void cumpleFiltro() {
		when(viaje.fechaDeLlegada()).thenReturn(fecha);
		assertTrue(filtro.cumpleFiltro(viaje));
	}

}
