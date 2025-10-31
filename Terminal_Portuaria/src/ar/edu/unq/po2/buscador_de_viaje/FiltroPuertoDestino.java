package ar.edu.unq.po2.buscador_de_viaje;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

public class FiltroPuertoDestino extends Condicion {
	
	private TerminalPortuaria puertoDestino;
	
	public List<Viaje> losQueCumplen(List<Viaje> viajes) {
		
		List<Viaje> cumplen = new ArrayList<Viaje>();
		
		for (Viaje viaje : viajes) {
			if(this.puertoDestino.equals(viaje.puertoDestino())) {
				cumplen.add(viaje);
			}
		}
		
		return cumplen;
		
	}

}
