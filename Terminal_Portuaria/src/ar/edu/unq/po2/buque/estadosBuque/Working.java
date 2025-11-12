package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;


public class Working extends EstadoBuque{
    
	boolean puedePartir = false;
	public Working(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
		return puedePartir;
	}
	
	public void puedePartir() {
		this.puedePartir=true;
	}

	@Override
	public void modificarEstadoBuque() {
		miBuque.establecerEstado(new Departing(miBuque));
		
	}
	@Override
	public void notificarEstado() {
		// no notifica nada a la terminal
		
	}

	@Override
	public void avanzar(double latitud, double longitud) {
		//No puede moverse tampoco en estado Working
	}

}
