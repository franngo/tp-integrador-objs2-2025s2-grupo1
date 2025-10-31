package ar.edu.unq.po2.buscador_de_viaje;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

public class FiltroPuertoDestino extends FiltroSimple {
	
	private TerminalPortuaria puertoDestino;
	
	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return this.puertoDestino.equals(viaje.puertoDestino());
	}

}
