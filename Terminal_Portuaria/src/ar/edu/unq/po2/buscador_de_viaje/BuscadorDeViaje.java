package ar.edu.unq.po2.buscador_de_viaje;

import java.util.List;

import ar.edu.unq.po2.viaje.Viaje;

public class BuscadorDeViaje {

	public List<Viaje> buscarViaje(Condicion cond, List<Viaje> viajes) {
		return cond.losQueCumplen(viajes);
	}
	
}
