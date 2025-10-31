package ar.edu.unq.po2.buscador_de_viaje;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.viaje.Viaje;

abstract public class FiltroSimple extends Condicion {
	
	public List<Viaje> losQueCumplen(List<Viaje> viajes) {
		
		List<Viaje> cumplen = new ArrayList<Viaje>();
		
		for (Viaje viaje : viajes) {
			if(this.cumpleFiltro(viaje)) {
				cumplen.add(viaje);
			}
		}
		
		return cumplen;
		
	}
	
	abstract public boolean cumpleFiltro(Viaje viaje);

}
