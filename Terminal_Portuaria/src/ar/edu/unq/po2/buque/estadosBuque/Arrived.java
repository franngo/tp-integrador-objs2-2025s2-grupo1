package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;


public class Arrived extends EstadoBuque{

   boolean puedeIniciarWorking = false;
	

	public Arrived(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
		return this.laTerminalDioVistoBueno();
	}
     
	
	public boolean laTerminalDioVistoBueno() {return true;}
	
	@Override
	public void modificarEstadoBuque() {
		//solo la terminal llama a este metodo
		miBuque.establecerEstado(new Working(miBuque));
		
	}
    
	public void puedeIniciarWorking() {
		this.puedeIniciarWorking = true;
	}

	@Override
	public void notificarEstado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avanzar(double latitud, double longitud) {
		// TODO Auto-generated method stub
		
	}

}
