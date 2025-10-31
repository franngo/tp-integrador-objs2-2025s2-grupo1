package ar.edu.unq.po2.buscador_de_viaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

public class FiltroFechaLlegada extends FiltroSimple {
	
	private LocalDate fechaLlegada;
	
	public FiltroFechaLlegada(LocalDate fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	
	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return this.fechaLlegada.equals(viaje.fechaDeLlegada());
	}

}
