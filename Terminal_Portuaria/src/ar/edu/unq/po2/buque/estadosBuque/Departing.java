package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

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
		miBuque.establecerEstado(new OutBound(miBuque));
		
	}



	@Override
	public void notificarEstado(TerminalPortuaria terminal) {
		
		
	}

}
