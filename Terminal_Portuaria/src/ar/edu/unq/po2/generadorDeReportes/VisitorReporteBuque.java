package ar.edu.unq.po2.generadorDeReportes;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.Orden;

public class VisitorReporteBuque implements VisitorReporte {
	
	public String visitOrden(Orden orden) {
		Container c = orden.getCarga();
		return "			<item>" + c.getIdConnteiner() + "</item>\n";
	}

}
