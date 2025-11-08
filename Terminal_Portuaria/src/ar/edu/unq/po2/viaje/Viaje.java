package ar.edu.unq.po2.viaje;

import java.time.LocalDateTime;

import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Viaje {
	
	public LocalDateTime fechaDeSalida() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return LocalDateTime.now(); // Esto lo agregué porque necesito tener algo como "2025/04/10T08:30" en la Terminal. Es decir, la fecha y el horario.
	}
	
	public LocalDateTime fechaDeLlegada() {
		//TODO
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return LocalDateTime.now(); // Esto lo agregué porque necesito tener algo como "2025/04/10T08:30" en la Terminal. Es decir, la fecha y el horario.
	}
	
	public TerminalPortuaria puertoDestino() {
		//TODO
		/*este no lo definimos en el UML, pero se necesita para el buscador de viaje. Sería agarrar el último puerto del
		 * circuito marítimo asociado a este viaje.
		 */
		//devuelvo cualquier cosa porque necesito la interfaz definida para poder mockear la clase en los tests
		return new TerminalPortuaria(new Coordenada(40.1004, 40.49181));
	}
	
}
