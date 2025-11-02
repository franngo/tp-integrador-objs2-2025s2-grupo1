package ar.edu.unq.po2.servicio;


import java.time.LocalDate;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


/*
 * @Autor: Matias Sanchez
 * */
public abstract class Servicio {
	
	protected Container containerServ;
	
	private LocalDate inicioServicio;
	
	
	public Servicio(Container containerServ) {
		this.containerServ = containerServ;
		this.inicioServicio = LocalDate.now();
	}
	
	public LocalDate getInicioServicio() {
		return inicioServicio;
	}
	public Container getContainer() {
		return containerServ;
	}
	
	public abstract String tipoServicio();
		
	
	
	public abstract double costoServicio(TerminalPortuaria terminalPortuaria); 
		
	
		
    	
    
    

}
