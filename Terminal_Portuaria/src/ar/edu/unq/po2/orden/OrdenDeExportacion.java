package ar.edu.unq.po2.orden;

import ar.edu.unq.po2.generadorDeReportes.VisitorReporte;

public class OrdenDeExportacion extends Orden {
	
	public String accept(VisitorReporte visitor) {
		return visitor.visitOrdenDeExportacion(this);
	}

}
