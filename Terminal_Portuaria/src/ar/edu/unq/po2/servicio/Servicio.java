package ar.edu.unq.po2.servicio;


import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


/*
 * @Autor: Matias Sanchez
 * */
public abstract class Servicio {
	
	private Container containerServ;
	
	public Servicio(Container containerServ) {
		this.containerServ = containerServ;
	}
	
	public Container getContainer() {
		return containerServ;
	}
	
	public abstract double costoServicio(TerminalPortuaria terminalPortuaria); 
		
	
		
    	
    
    

}
