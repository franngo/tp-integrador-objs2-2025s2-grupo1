package ar.edu.unq.po2.circuito_maritimo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.tramo.Tramo;

public class CircuitoMaritimo {
	
	private List<Tramo> tramos;
	
	//////////////////////////////////////////////CONSTRUCTOR///////////////////////////////////////////////////
	
	/*
	 * PRECONDICIÓN: La lista de tramos no puede ser vacía.
	 */
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
		
		if(tramos.isEmpty()) {
			throw new RuntimeException("La secuencia de tramos no puede ser vacía. Debe tener al menos un tramo.");
		}
		
		for(int i = 0; i < (tramos.size() - 1); i++) {
			if( tramos.get(i).getTerminalDestino() != tramos.get(i+1).getTerminalOrigen() ) {
				throw new RuntimeException("La secuencia de tramos no está bien formada.");
			}
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean esCircuitoQueUneA(TerminalPortuaria t1, TerminalPortuaria t2) {
		return this.tieneOrigen(t1) && this.tieneDestino(t2) && this.tienePrimeroA(t1, t2);
	}
	
	private boolean tieneOrigen(TerminalPortuaria terminal) {
		return this.tramos.stream().anyMatch((t) -> t.getTerminalOrigen().equals(terminal));
	}
	
	private boolean tieneDestino(TerminalPortuaria terminal) {
		return this.tramos.stream().anyMatch((t) -> t.getTerminalDestino().equals(terminal));
	}
	
	private boolean tienePrimeroA(TerminalPortuaria t1, TerminalPortuaria t2) {
		
		if(this.tieneOrigen(t1) && this.tieneDestino(t2)) {
		//si no se cumplen alguna de esas dos, no se va a cumplir esta tercera

			List<Tramo> ts = new ArrayList<Tramo>();
			int n = 0;
			
			while(this.tramos.get(n).getTerminalOrigen() != t1) {
				n++;
			}
			//obtenemos el índice en donde se encuentra t1 como orígen en la lista de tramos
			
			while(n < this.tramos.size()) {
				ts.add(this.tramos.get(n));
				n++;
			}
			//obtenemos la lista de tramos desde que encontramos a t1 como orígen
			
			return ts.stream().anyMatch((t) -> t.getTerminalDestino().equals(t2));
			//si está como destino t2 en alguno de los tramos, significa que se cumple la condición
			
		} else { return false; }
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean incluyeA(TerminalPortuaria t) {
		
		return this.tieneOrigen(t) || this.tieneDestino(t);
		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * PRECONDICIÓN: Se debe cumplir this.esCircuitoQueUneA(t1, t2)
	 */
	public List<Tramo> tramosDesdeHasta(TerminalPortuaria t1, TerminalPortuaria t2) {
		
		List<Tramo> ts = new ArrayList<Tramo>();
		int n = 0;
		
		while(this.tramos.get(n).getTerminalOrigen() != t1) {
			n++;
		}
		//obtenemos el índice en donde se encuentra t1 como orígen en la lista de tramos
		
		while(this.tramos.get(n).getTerminalDestino() != t2) {
			ts.add(this.tramos.get(n));
			n++;
		}
		ts.add(this.tramos.get(n));
		//añadimos tramos a la lista de tramos hasta encontrarnos con t2 como terminal destino. Por caso borde, también
		//añadimos el tramo del índice que nos queda.
		
		return ts;
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * PRECONDICIÓN: Se debe cumplir this.esCircuitoQueUneA(origen, destino)
	 */
	public double precioEnTramosDesdeHasta(TerminalPortuaria origen, TerminalPortuaria destino) {
		
		List<Tramo> ts = this.tramosDesdeHasta(origen, destino);
		
		return ts.stream().map((t) -> t.getPrecioTramo()).mapToDouble(Double::doubleValue).sum();
		
	}
	
	/*
	 * PRECONDICIÓN: Se debe cumplir this.esCircuitoQueUneA(origen, destino)
	 */
	public Duration tiempoEnTramosDesdeHasta(TerminalPortuaria origen, TerminalPortuaria destino) {
		
		List<Tramo> ts = this.tramosDesdeHasta(origen, destino);
		
		Duration d = Duration.ZERO;
		for(Tramo t : ts) {
			d = d.plus(t.getTiempoTotal());
		}
		return d;
		
	}
	
	/*
	 * PRECONDICIÓN: Se debe cumplir this.esCircuitoQueUneA(origen, destino)
	 * 
	 * Observación: La razón de que se devuelva la cantidad de tramos sumada en uno es que, por ejemplo, en un circuito 
	 * con 2 tramos hay 4 terminales involucradas: la origen y la destino del primer tramo, y la origen y la destino del
	 * segundo tramo, pero la origen del segundo tramo es la misma que la destino del primero, así que el total de 
	 * terminales diferentes del circuito es de 3.
	 */
	public int cantTerminalesEnTramosDesdeHasta(TerminalPortuaria origen, TerminalPortuaria destino) {
		
		List<Tramo> ts = this.tramosDesdeHasta(origen, destino);
		
		return ts.size()+1;
		
	}
	
	//////////////////////////////métodos necesarios para Viaje//////////////////////////////////////////////////////////
	
	//Usado para que el Viaje asociado pueda decir la fecha de llegada (necesario para las Condiciones)
	public Duration tiempoTotal() {
		
		Duration d = Duration.ZERO;
		for(Tramo t : this.tramos) {
			d = d.plus(t.getTiempoTotal());
		}
		return d;
		
	}
	
	//Usado para que el Viaje asociado pueda decir la terminal destino (necesario para las Condiciones)
	public TerminalPortuaria puertoDestino() {
		
		return this.tramos.getLast().getTerminalDestino();
		
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
		for(Tramo t : ts) {
			d = d.plus(t.getTiempoTotal());
		}
		return d;
		
	}
	
	private void validarSiEsDestino(TerminalPortuaria terminal) {
		
		if(this.tramos.stream().noneMatch((t) -> t.getTerminalDestino().equals(terminal))) {
			throw new RuntimeException("Dicha terminal no es un destino de este circuito marítimo");
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//??
	public double precioTotal() {
		return this.tramos.stream().map((t) -> t.getPrecioTramo()).mapToDouble(Double::doubleValue).sum();
	}
	

	//??
	public int cantidadDeTerminales() {
		return this.tramos.size()+1;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public TerminalPortuaria proximoDestino(TerminalPortuaria terminal) {
        List<Tramo> tr = this.tramos.stream().filter((t) -> t.getTerminalOrigen().equals(terminal)).toList();

        if(tr.isEmpty()) {
            return null; //esto en el caso de que no haya un siguiente

        } else {
            return tr.getFirst().getTerminalDestino();
        }
    }
}	