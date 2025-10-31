package ar.edu.unq.po2.buscador_de_viaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.viaje.Viaje;

public class FiltroFechaSalida extends FiltroSimple {
	
	private LocalDate fechaSalida;
	
	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return this.fechaSalida.equals(viaje.fechaDeSalida());
	}

}
