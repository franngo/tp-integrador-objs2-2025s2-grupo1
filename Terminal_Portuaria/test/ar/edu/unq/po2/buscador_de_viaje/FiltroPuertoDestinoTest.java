package ar.edu.unq.po2.buscador_de_viaje;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

public class FiltroPuertoDestinoTest {
	
	TerminalPortuaria puertoMontevideo = mock(TerminalPortuaria.class);
	FiltroPuertoDestino filtro = new FiltroPuertoDestino(puertoMontevideo);
	Viaje viaje = mock(Viaje.class);
	
	@Test
	public void cumpleFiltro() {
		when(viaje.puertoDestino()).thenReturn(puertoMontevideo);
		assertTrue(filtro.cumpleFiltro(viaje));
	}

}
