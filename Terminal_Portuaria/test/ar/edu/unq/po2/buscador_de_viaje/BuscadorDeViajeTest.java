package ar.edu.unq.po2.buscador_de_viaje;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.viaje.Viaje;

public class BuscadorDeViajeTest {
	
	BuscadorDeViaje buscador = new BuscadorDeViaje();
	Condicion cond = mock(Condicion.class);
	List<Viaje> viajes = new ArrayList<Viaje>();

	@Test
	public void buscarViaje() {
		buscador.buscarViaje(cond, viajes);
		verify(cond).losQueCumplen(viajes);
	}
	
}
