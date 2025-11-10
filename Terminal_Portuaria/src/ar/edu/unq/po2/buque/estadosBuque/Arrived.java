package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;

public class Arrived extends EstadoBuque{

	
	Buque miBuque;
	
    public Arrived(Buque miBuque) {
    	this.miBuque = miBuque;
    }
	

	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void modificarEstadoBuque() {
		// TODO Auto-generated method stub
		
	}

}
