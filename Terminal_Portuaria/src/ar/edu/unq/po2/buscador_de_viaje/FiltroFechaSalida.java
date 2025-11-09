package ar.edu.unq.po2.buscador_de_viaje;

import java.time.LocalDateTime;

import ar.edu.unq.po2.viaje.Viaje;

public class FiltroFechaSalida extends FiltroSimple {
	private LocalDateTime fechaSalida;
	
	public FiltroFechaSalida(LocalDateTime fecha) {
		this.fechaSalida = fecha;
	}
	
	@Override
	public boolean cumpleFiltro(Viaje viaje) {
		return this.fechaSalida.equals(viaje.fechaDeSalida());
	}
}