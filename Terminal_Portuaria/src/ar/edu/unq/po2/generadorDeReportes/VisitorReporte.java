package ar.edu.unq.po2.generadorDeReportes;

import ar.edu.unq.po2.orden.OrdenDeExportacion;
import ar.edu.unq.po2.orden.OrdenDeImportacion;

public interface VisitorReporte {
	
	abstract public String visitOrdenDeImportacion(OrdenDeImportacion orden);
	
	abstract public String visitOrdenDeExportacion(OrdenDeExportacion orden);

}
