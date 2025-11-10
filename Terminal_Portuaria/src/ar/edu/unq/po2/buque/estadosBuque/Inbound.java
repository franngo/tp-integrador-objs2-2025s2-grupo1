package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;

public class Inbound extends EstadoBuque{
    
	Buque miBuque;
	
	public Inbound(Buque nuevoBuque) {
		this.miBuque=nuevoBuque;
	}
	@Override
	public void notificarAccionesTerminal() {
		// TODO Auto-generated method stub
		
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
