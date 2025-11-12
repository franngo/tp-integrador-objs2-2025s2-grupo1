package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Arrived extends EstadoBuque{


	

	public Arrived(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
		return miBuque.terminalAArribar().puedeIniciarWorking(miBuque);
	}

	@Override
	public void modificarEstadoBuque() {
		miBuque.establecerEstado(new Working(miBuque));
		
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
