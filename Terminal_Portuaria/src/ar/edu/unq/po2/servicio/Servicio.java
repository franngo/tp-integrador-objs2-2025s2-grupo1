package ar.edu.unq.po2.servicio;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public abstract class Servicio {
	
	Container containerServ;

    public double costoServicio(TerminalPortuaria terminalPortuaria) {
		return (double) 0;
    	
    }
}
