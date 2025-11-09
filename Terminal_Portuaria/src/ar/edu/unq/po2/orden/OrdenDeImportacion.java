package ar.edu.unq.po2.orden;

import ar.edu.unq.po2.generadorDeReportes.VisitorReporte;

public class OrdenDeImportacion extends Orden {
	
	public String accept(VisitorReporte visitor) {
		return visitor.visitOrdenDeImportacion(this);
	}

}
