package ar.edu.unq.po2.generadorDeReportes;

import ar.edu.unq.po2.orden.Orden;

public interface VisitorReporte {
	
	abstract public String visitOrden(Orden orden);
}