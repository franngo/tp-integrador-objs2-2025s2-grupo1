package ar.edu.unq.po2.buscador_de_viaje;

import java.time.LocalDateTime;

import ar.edu.unq.po2.viaje.Viaje;

public class FiltroFechaLlegada extends FiltroSimple {
	private LocalDateTime fechaLlegada;
	
	public FiltroFechaLlegada(LocalDateTime fecha) {
		this.fechaLlegada = fecha;
	}
	
	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return this.fechaLlegada.equals(viaje.fechaDeLlegada());
	}

}
