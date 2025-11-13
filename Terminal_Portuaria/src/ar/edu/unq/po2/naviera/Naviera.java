package ar.edu.unq.po2.naviera;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
		this.viajes = new ArrayList<Viaje>();
	}
	
	public List<Viaje> cronograma() {
		return this.viajes;
	}
	
	public void publicarViaje(LocalDateTime fechaSalida, CircuitoMaritimo circuito, Buque buque) {
		
		this.validarElemsValidos(circuito, buque);
		Viaje viaje = new Viaje(fechaSalida, circuito, buque);
		viajes.add(viaje);
		
	}
	
	private void validarElemsValidos(CircuitoMaritimo circuito, Buque buque) {
		
		if( ( !(this.circuitos.contains(circuito)) ) || ( !(this.buques.contains(buque)) ) ) {
			throw new RuntimeException("No se puede publicar un viaje para esta naviera con estos elementos.");
		}
		
	}
	
	public List<CircuitoMaritimo> circuitosQueUnan(TerminalPortuaria t1, TerminalPortuaria t2) {
		
		List<CircuitoMaritimo> cs = circuitos.stream().
				filter((c) -> c.esCircuitoQueUneA(t1, t2)).
				toList();
		return cs;
		
	}
	
	public List<Viaje> viajesQueIncluyenOrigen(TerminalPortuaria t) {
		
		List<Viaje> vs = viajes.stream().
				filter((v) -> v.tieneOrigen(t)).
				toList();
		return vs;
		
	}
	
	public Duration tiempoEntre(TerminalPortuaria t1, TerminalPortuaria t2) {
		
		List<CircuitoMaritimo> cs = this.circuitosQueUnan(t1, t2);
		
		Duration d = cs.stream().min(Comparator.comparing(c -> c.tiempoEnTramosDesdeHasta(t1, t2))).get().
				tiempoEnTramosDesdeHasta(t1, t2);
		
		return d;
		
	}

}
