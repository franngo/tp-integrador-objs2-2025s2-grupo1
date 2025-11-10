package ar.edu.unq.po2.circuito_maritimo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.tramo.Tramo;

public class CircuitoMaritimo {
	
	private List<Tramo> tramos;
	
	//////////////////////////////////////////////CONSTRUCTOR///////////////////////////////////////////////////
	
	public CircuitoMaritimo(List<Tramo> tramos) {
		
		this.validarTramos(tramos);
		this.tramos = tramos;
		
	}
	
	private void validarTramos(List<Tramo> tramos) {
		
		/*
		 * Objetivo: Validar que se de para todos los tramos que el puerto destino sea el puerto orígen en el
		 * próximo tramo de la secuencia.
		 * Observación: No uso stream y allMatch porque ahí no puedo referir tanto al elemento procesado actualmente 
		 * como a su siguiente.
		 */
		for(int i = 0; i < this.tramos.size() - 1; i++) {
			if( ! (this.tramos.get(i).getTerminalDestino() != this.tramos.get(i+1).getTerminalOrigen()) ) {
				throw new RuntimeException("La secuencia de tramos no está bien formada");
			}
		}
		
	}
	
	//////////////////////////////////////////////CONSTRUCTOR///////////////////////////////////////////////////
	
	public Duration tiempoTotal() {
		
		Duration d = Duration.ZERO;
		this.tramos.stream().forEach((t) -> d.plus(t.getTiempoTotal()));
		return d;
		
	}
	
	public Duration tiempoHastaTerminal(TerminalPortuaria terminal) {
		
		this.validarSiEsDestino(terminal);
		
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
	
	private void validarSiEsDestino(TerminalPortuaria terminal) {
		
		if(this.tramos.stream().noneMatch((t) -> t.getTerminalDestino().equals(terminal))) {
			throw new RuntimeException("Dicha terminal no es un destino de este circuito marítimo");
		}
		
	}
	
	public double precioTotal() {
		
		return this.tramos.stream().map((t) -> t.getPrecioTramo()).mapToDouble(Double::doubleValue).sum();
		
	}
	
	/*
	 * Observación: La razón de que se devuelva la cantidad de tramos sumada en uno es que, por ejemplo, en un circuito 
	 * con 2 tramos hay 3 terminales involucradas: la origen y la destino del primer tramo, y la origen y la destino del
	 * segundo tramo, pero la origen del segundo tramo es la misma que la destino del primero, así que el total de 
	 * terminales diferentes del circuito es de 3.
	 */
	public int cantidadDeTerminales() {
		
		return this.tramos.size()+1;
		
	}
	
	public TerminalPortuaria puertoDestino() {
		
		return this.tramos.getLast().getTerminalDestino();
		
	}
	
}	
