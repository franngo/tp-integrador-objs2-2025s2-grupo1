package ar.edu.unq.po2.viaje;

import java.time.LocalDateTime;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Viaje {
	private LocalDateTime fechaDeSalida;
	private CircuitoMaritimo circuito;
	private Buque buque;
	
	public Viaje(LocalDateTime fechaDeSalida, CircuitoMaritimo circuito, Buque buque) {
		this.fechaDeSalida = fechaDeSalida;
		this.circuito = circuito;
		this.buque = buque;
	}
	
	public LocalDateTime fechaDeSalida() {
		return this.fechaDeSalida;
	}
	
	public LocalDateTime fechaDeLlegada() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		//Se calcularía en base a la fecha de salida y a los tramos del circuito marítimo asociado.
		return LocalDateTime.now();
	}
	
	public TerminalPortuaria puertoDestino() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		/*Sería agarrar el último puerto del circuito marítimo asociado a este viaje.
		 */
		return new TerminalPortuaria(new Coordenada(40, 40)); // Tiene un valor aleatorio para que no tire error, es necesario que una subtarea defina el destino.
	}
}