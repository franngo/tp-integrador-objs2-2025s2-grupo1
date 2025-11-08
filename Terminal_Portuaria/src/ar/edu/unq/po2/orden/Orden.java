package ar.edu.unq.po2.orden;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.generadorDeReportes.VisitorReporte;

abstract public class Orden {
	
	private Container carga;
	
	public Container getCarga() {
		return this.carga;
	}
	
	abstract public String accept(VisitorReporte visitor);

}