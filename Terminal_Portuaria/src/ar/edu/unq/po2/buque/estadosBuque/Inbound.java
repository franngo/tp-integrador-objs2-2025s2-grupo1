package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;


public class Inbound extends EstadoBuque{
    
    boolean yaNotifico = false;
	public Inbound(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
					
		return miBuque.posicionActual().enMismaPosicionCon(terminalAArribar.getCoordenada());
	}
	@Override
	public void modificarEstadoBuque() {
		miBuque.establecerEstado(new Arrived(miBuque));
		
	}



	@Override
	public void notificarEstado() {
		if(!yaNotifico) {
	    terminalAArribar.notificarArribo(miBuque);
	    this.notificacionConfirmada();
		}
	}
	
	public void notificacionConfirmada() {
		this.yaNotifico=true;
	}
	
	@Override
	public void avanzar(double latitud, double longitud) {
		miBuque.nuevaPosicion(latitud, longitud);
		
	}

	

}
