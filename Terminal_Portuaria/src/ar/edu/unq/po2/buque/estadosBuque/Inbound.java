package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Inbound extends EstadoBuque{
    

	public Inbound(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
					
		return miBuque.posicionActual().enMismaPosicionCon(terminalAArribar.coordenadasTerminal());
	}
	@Override
	public void modificarEstadoBuque() {
		miBuque.establecerEstado(new Arrived(miBuque));
		
	}



	@Override
	public void notificarEstado(TerminalPortuaria terminal) {
		// TODO Auto-generated method stub
		
	}

	

}
