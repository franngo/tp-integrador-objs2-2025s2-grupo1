package ar.edu.unq.po2.circuito_maritimo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.tramo.Tramo;

public class CircuitoMaritimo {
	
	private List<Tramo> tramos;
	
	//recordar realizar chequeo sobre la lista de Tramos recibida (deben tener sentido)
	//puede ser con método privado que se llama al principio del constructor en el que se analiza la lista de Tramos.
	public CircuitoMaritimo(List<Tramo> tramos) {
		this.tramos = tramos;
	}
	
	public Duration tiempoTotal() {
		Duration d = Duration.ZERO;
		this.tramos.stream().forEach((t) -> d.plus(t.getTiempoTotal()));
		return d;
	}
	
	public Duration tiempoHastaTerminal(TerminalPortuaria terminal) {
		if(this.tramos.stream().noneMatch((t) -> t.getTerminalOrigen().equals(terminal))) {
			throw new RuntimeException("Dicha terminal no forma parte de este circuito marítimo");
		}
		
		//Primero consigo la lista de tramos hasta la terminal deseada.
		List<Tramo> ts = new ArrayList<Tramo>();
		int n = 0;
		while(this.tramos.get(n).getTerminalDestino() != terminal) {
			ts.add(this.tramos.get(n));
			n++;
		}
		ts.add(this.tramos.get(n)); //añado también el tramo con la terminal deseada como puerto destino.
		
		//Ahora, ya con esa lista, calculo la duración total de este segmento del Viaje.
		Duration d = Duration.ZERO;
		ts.stream().forEach((t) -> d.plus(t.getTiempoTotal()));
		return d;
	}
	
	public double precioTotal() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return 0.0;
	}
	
	/*
	 * Observación: La razón de que se devuelva la cantidad de tramos sumada en uno es que, por ejemplo, en un circuito con
	 * 2 tramos hay 3 terminales involucradas: la origen y la destino del primer tramo, y la origen y la destino del
	 * segundo tramo, pero la origen del segundo tramo es la misma que la destino del primero, así que el total de terminales
	 * diferentes del circuito es de 3.
	 */
	public int cantidadDeTerminales() {
		return this.tramos.size()+1;
	}
	
	public TerminalPortuaria puertoDestino() {
		return this.tramos.getLast().getTerminalDestino();
	}
	
}	
