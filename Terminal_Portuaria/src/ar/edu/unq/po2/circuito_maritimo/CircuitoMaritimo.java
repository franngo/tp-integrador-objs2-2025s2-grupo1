package ar.edu.unq.po2.circuito_maritimo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.tramo.Tramo;

public class CircuitoMaritimo {
	
	private List<Tramo> tramos;
	
	public CircuitoMaritimo(List<Tramo> tramos) {
		this.tramos = tramos;
	}
	
	public Duration tiempoTotal() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return Duration.ofHours(2);
	}
	
	public Duration tiempoHastaTerminal(TerminalPortuaria terminal) {
		return Duration.ofHours(2);
	}
	
	public double precioTotal() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return 0.0;
	}
	
	public int cantidadDeTerminales() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return 0;
	}
	
	public TerminalPortuaria puertoDestino() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		/*Sería agarrar el último puerto del circuito marítimo asociado a este viaje.
		 */
		return new TerminalPortuaria(new Coordenada(40, 40)); // Tiene un valor aleatorio para que no tire error, es necesario que una subtarea defina el destino.
	}
	
}	
