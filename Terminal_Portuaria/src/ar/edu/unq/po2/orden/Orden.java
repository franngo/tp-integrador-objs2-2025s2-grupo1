package ar.edu.unq.po2.orden;

import java.time.LocalDate;

public abstract class Orden {
	private LocalDate fechaDeSalida;
	
	public Orden(LocalDate fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}
	
	public LocalDate getFechaDeSalida() {
		return fechaDeSalida;
	}
}