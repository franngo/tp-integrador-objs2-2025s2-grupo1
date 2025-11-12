package ar.edu.unq.po2.generadorDeReportes;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.OrdenDeExportacion;
import ar.edu.unq.po2.orden.OrdenDeImportacion;

public class VisitorReporteBuque implements VisitorReporte {
	
	public String visitOrdenDeImportacion(OrdenDeImportacion orden) {
		Container c = orden.getCarga();
		return "			<item>" + c.getIdConnteiner() + "</item>\n";
	}
	
	public String visitOrdenDeExportacion(OrdenDeExportacion orden) {
		Container c = orden.getCarga();
		return "			<item>" + c.getIdConnteiner() + "</item>\n";
	}

}
