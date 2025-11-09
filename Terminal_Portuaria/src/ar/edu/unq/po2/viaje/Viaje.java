package ar.edu.unq.po2.viaje;

import java.time.LocalDateTime;

import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Viaje {
	
	private LocalDate fechaDeSalida;
	private CircuitoMaritimo circuito;
	private Buque buque;
	
	public Viaje(LocalDate fechaDeSalida, CircuitoMaritimo circuito, Buque buque) {
		this.fechaDeSalida = fechaDeSalida;
		this.circuito = circuito;
		this.buque = buque;
	}
	
	public LocalDate fechaDeSalida() {
		return this.fechaDeSalida;
	}
	
	public LocalDateTime fechaDeLlegada() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		//Se calcularía en base a la fecha de salida y a los tramos del circuito marítimo asociado.
		return LocalDate.now();
	}
	
	public TerminalPortuaria puertoDestino() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		/*Sería agarrar el último puerto del circuito marítimo asociado a este viaje.
		 */
		return new TerminalPortuaria();
	}
	
}
