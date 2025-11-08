package ar.edu.unq.po2.orden;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.viaje.Viaje;

public abstract class Orden {
	private Camion camion;
	private Chofer chofer;
	private Container container;
	private Viaje viaje;
	
	public Orden(Camion camion, Chofer chofer, Container container, Viaje viaje) {
		this.camion = camion;
		this.chofer = chofer;
		this.container = container;
		this.viaje = viaje;
	}

	public LocalDateTime fechaDeSalida() {
		return viaje.fechaDeSalida();
	}
	
	public LocalDateTime fechaDeLlegada() {
		return viaje.fechaDeLlegada();
	}

	public Camion getCamion() {
		return camion;
	}

	public Chofer getChofer() {
		return chofer;
	}
}