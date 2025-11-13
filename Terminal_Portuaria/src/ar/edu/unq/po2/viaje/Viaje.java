package ar.edu.unq.po2.viaje;

import java.time.LocalDateTime;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Viaje {
	private LocalDateTime fechaDeSalida;
	private CircuitoMaritimo circuito;
	private Buque buque;
	
	//////////////////////////////////////////////CONSTRUCTOR///////////////////////////////////////////////////
	
	public Viaje(LocalDateTime fechaDeSalida, CircuitoMaritimo circuito, Buque buque) {
		
		this.fechaDeSalida = fechaDeSalida;
		this.circuito = circuito;
		this.buque = buque;
		
	}
	
	//////////////////////////////////////////////CONSTRUCTOR///////////////////////////////////////////////////
	
	//??
	public LocalDateTime fechaDeSalida() {
		
		return this.fechaDeSalida;
		
	}
	
	//??
	public LocalDateTime fechaDeLlegada() {

		return this.fechaDeSalida.plus(circuito.tiempoTotal());
		
	}
	
	public LocalDateTime fechaDeLlegadaATerminal(TerminalPortuaria terminal) {
		return this.fechaDeSalida.plus(circuito.tiempoHastaTerminal(terminal));
	}
	
	//??
	public TerminalPortuaria puertoDestino() {
		
		return circuito.puertoDestino();
		
	}

	public TerminalPortuaria proximoDestino() {
		TerminalPortuaria t = this.buque.terminalAArribar();
		return this.circuito.proximoDestino(t);
	}
	
	public boolean tieneOrigen(TerminalPortuaria terminal) {
		return this.circuito.tieneOrigen(terminal);
	}
	
}