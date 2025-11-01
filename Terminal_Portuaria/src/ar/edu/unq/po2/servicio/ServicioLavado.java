package ar.edu.unq.po2.servicio;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioLavado extends Servicio{
	public ServicioLavado(Container containerServ) {
		super(containerServ);
		// TODO Auto-generated constructor stub
	}

	Container containerServ;
	

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
