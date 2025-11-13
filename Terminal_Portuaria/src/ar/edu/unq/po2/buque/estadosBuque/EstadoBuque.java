package ar.edu.unq.po2.buque.estadosBuque;

import java.util.List;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public abstract class EstadoBuque {
	protected Buque miBuque;
    protected TerminalPortuaria terminalAArribar;
    
    public EstadoBuque(Buque miBuque) {
		this.miBuque = miBuque;
		this.terminalAArribar=miBuque.terminalAArribar();
	}
    
	public abstract void notificarEstado();

     // TEMPLATE METHOD 	
	public void actualizarSiSeRequiere() {
	      if(this.debeCambiarDeFase()) {
	    	  	this.modificarEstadoBuque();
	      }
		
	}
	
	public abstract boolean debeCambiarDeFase();
	public abstract void modificarEstadoBuque();
	
	protected Buque miBuque() {return miBuque;}
	protected TerminalPortuaria terminalAArribar() {return terminalAArribar;}

	public abstract void avanzar(double latitud, double longitud);
		// TODO Auto-generated method stub

	public void puedeIniciarWorking() {
		// TODO Auto-generated method stub
		
	}

	public void puedePartir() {
		// TODO Auto-generated method stub
		
	}

	// IMPLEMENTACIÓN BENJA
	
	public void iniciarTrabajos() {
		throw new RuntimeException("El buque no se encuentra en la terminal");
	}
	
	public void cargarOrdenes(List<Orden> ordenes) {
		throw new RuntimeException("No puede realizar esta acción en este estado.");
	}

	public List<Orden> getOrdenesADescargar(TerminalPortuaria terminalPortuaria) {
		throw new RuntimeException("No puede realizar esta acción en este estado.");
	}

	public void finalizarDescargaDeOrdenes(List<Orden> ordenes) {
		throw new RuntimeException("No puede realizar esta acción en este estado.");
	}

	public void finalizarTrabajos() {
		throw new RuntimeException("El buque aun no se encuentra en condiciones de partir");
	}
}