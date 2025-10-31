package ar.edu.unq.po2.viaje;

import java.time.LocalDate;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Viaje {
	
	public LocalDate fechaDeSalida() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return LocalDate.now();
	}
	
	public LocalDate fechaDeLlegada() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return LocalDate.now();
	}
	
	public TerminalPortuaria puertoDestino() {
		//TODO
		/*este no lo definimos en el UML, pero se necesita para el buscador de viaje. Sería agarrar el último puerto del
		 * circuito marítimo asociado a este viaje.
		 */
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return new TerminalPortuaria();
	}
	
}
