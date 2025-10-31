package ar.edu.unq.po2.buscador_de_viaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.viaje.Viaje;

public class FiltroFechaSalida extends Condicion {
	
	private LocalDate fechaSalida;
	
	public List<Viaje> losQueCumplen(List<Viaje> viajes) {
		
		List<Viaje> cumplen = new ArrayList<Viaje>();
		
		for (Viaje viaje : viajes) {
			if(this.fechaSalida.equals(viaje.fechaDeSalida())) {
				cumplen.add(viaje);
			}
		}
		
		return cumplen;
		
	}

}
