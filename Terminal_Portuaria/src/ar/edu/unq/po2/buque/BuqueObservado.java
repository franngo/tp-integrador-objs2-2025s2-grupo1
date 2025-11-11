package ar.edu.unq.po2.buque;

import ar.edu.unq.po2.buque.estadosBuque.EstadoBuque;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public interface BuqueObservado {
	
	public EstadoBuque obtenerEstado(); 
	public void establecerEstado(EstadoBuque nuevoEstado);
	public void notificarEstado();
	public void adscribirObservador(TerminalPortuaria terminalObservadora);
}
