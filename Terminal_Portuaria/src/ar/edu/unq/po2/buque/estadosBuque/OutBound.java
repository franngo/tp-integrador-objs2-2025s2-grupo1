package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.coordenada.Coordenada;


public class OutBound extends EstadoBuque {
	
	Buque miBuque;
	
	public OutBound(Buque miBuque) {
		this.miBuque = miBuque;
	}

	

	@Override
	public void modificarEstadoBuque() {
		  miBuque.establecerEstado(new Inbound(miBuque));
	}
	
	@Override
	public boolean debeCambiarDeFase() {
		Coordenada ubicacionTerminal = miBuque.terminalAArribar().coordenadasTerminal();
		Coordenada ubicacionBuque = miBuque.posicionActual();
		return this.estaAMenosDe50km(ubicacionBuque,ubicacionTerminal);
	}
	
	public boolean estaAMenosDe50km(Coordenada ubicacionBuque, Coordenada ubicacionTerminal){
		return ubicacionBuque.distanciaA(ubicacionTerminal) < 50;
	}
	

}
