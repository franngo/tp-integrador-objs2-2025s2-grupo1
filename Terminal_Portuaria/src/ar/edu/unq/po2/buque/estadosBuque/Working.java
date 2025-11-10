package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Working extends EstadoBuque{
    
	
	public Working(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
		return miBuque.terminalAArribar().partidaHabilitada(miBuque);
	}

	@Override
	public void modificarEstadoBuque() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notificarEstado(TerminalPortuaria terminal) {
		// TODO Auto-generated method stub
		
	}

}
