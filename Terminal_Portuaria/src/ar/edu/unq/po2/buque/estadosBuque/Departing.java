package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;



public class Departing extends EstadoBuque{

	
	public Departing(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean debeCambiarDeFase() {
	
		// TODO Auto-generated method stub
		return miBuque.posicionActual().distanciaA(terminalAArribar.coordenadasTerminal()) 
				> this.unKM();
	}
    
	
	double unKM() {return 1;}
	
	@Override
	public void modificarEstadoBuque() {
		terminalAArribar.notificarSalidaTerminal(miBuque);
		miBuque.establecerEstado(new OutboundFinal(miBuque));
		
	}



	@Override
	public void notificarEstado() {
		
		
	}

	@Override
	public void avanzar(double latitud, double longitud) {
		//elBuqueAhoraPuedeMoverse
		miBuque.nuevaPosicion(latitud, longitud);
		
	}

}
