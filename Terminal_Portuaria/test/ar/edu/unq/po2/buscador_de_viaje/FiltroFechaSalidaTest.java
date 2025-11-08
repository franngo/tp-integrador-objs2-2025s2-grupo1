package ar.edu.unq.po2.buscador_de_viaje;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

public class FiltroFechaSalidaTest {
	LocalDateTime fecha = LocalDateTime.now();
	FiltroFechaSalida filtro = new FiltroFechaSalida(fecha);
	Viaje viaje = mock(Viaje.class);
	
	@Test
	public void cumpleFiltro() {
		when(viaje.fechaDeSalida()).thenReturn(fecha);
		assertTrue(filtro.cumpleFiltro(viaje));
	}
}