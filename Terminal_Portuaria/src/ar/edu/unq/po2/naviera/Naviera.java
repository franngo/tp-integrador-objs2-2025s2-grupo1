package ar.edu.unq.po2.naviera;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

public class Naviera {
	
	private List<CircuitoMaritimo> circuitos;
	private List<Buque> buques;
	private List<Viaje> viajes;
	
	public Naviera(List<CircuitoMaritimo> circuitos, List<Buque> buques) {
		this.circuitos = circuitos;
		this.buques = buques;
	}
	
	public List<Viaje> cronograma() {
		return viajes;
	}
	
	public void publicarViaje(LocalDateTime fechaSalida, CircuitoMaritimo circuito, Buque buque) {
		
		this.validarElemsValidos(circuito, buque);
		Viaje viaje = new Viaje(fechaSalida, circuito, buque);
		viajes.add(viaje);
		
	}
	
	private void validarElemsValidos(CircuitoMaritimo circuito, Buque buque) {
		
		if(( !this.circuitos.contains(circuito) ) || ( !this.buques.contains(buque) )) {
			throw new RuntimeException("No se puede publicar un viaje para esta naviera con estos elementos.");
		}
		
	}
	
	public List<CircuitoMaritimo> circuitosQueUnan(TerminalPortuaria t1, TerminalPortuaria t2) {
		
		List<CircuitoMaritimo> cs = circuitos.stream().
				filter((c) -> c.tieneOrigen(t1) && c.tieneOrigen(t2)).
				toList();
		return cs; //faltaría ver cómo validar que t1 se encuentra antes que t2 en el circuito...
		
	}

}
