package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;
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
		
	
	

	
	


	
}
