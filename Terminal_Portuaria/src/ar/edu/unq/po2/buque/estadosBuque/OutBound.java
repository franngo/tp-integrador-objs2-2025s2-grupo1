package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.coordenada.Coordenada;



public class OutBound extends EstadoBuque {
	
	
	public OutBound(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void modificarEstadoBuque() {
		  
		  miBuque.establecerEstado(new Inbound(miBuque));
	}
	
	@Override
	public boolean debeCambiarDeFase() {
		
		return this.estaAMenosDe50km(miBuque.posicionActual(),terminalAArribar.getCoordenada());
	}
	
	public boolean estaAMenosDe50km(Coordenada ubicacionBuque, Coordenada ubicacionTerminal){
		return ubicacionBuque.distanciaA(ubicacionTerminal) < 50;
	}



	@Override
	public void notificarEstado() {
		
		
	}

	@Override
	public void avanzar(double latitud, double longitud) {
		miBuque.posicionActual().nuevaPosicion(latitud, longitud);
		
	}



	
	

}
