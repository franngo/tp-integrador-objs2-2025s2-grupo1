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
     
	public boolean laTerminalDioVistoBueno() {return puedeIniciarWorking;}
	
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
		// No notifica nada en este estado
	}

	@Override
	public void avanzar(double latitud, double longitud) {
		// El buque no deberia poder moverse en este estado porque esta 
		// en laterminal
		
	}
	
	// IMPLEMENTACIÓN BENJA
	
	@Override
	public void iniciarTrabajos() {
		this.puedeIniciarWorking();
		this.modificarEstadoBuque();
	}
}
