package ar.edu.unq.po2.generadorDeReportes;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.Orden;

public class VisitorReporteAduana implements VisitorReporte {
	
	public String visitOrden(Orden orden) {
		Container c = orden.getCarga();
		return "    			<li><p> Tipo: " + c.tipoCarga() + ", ID: " + c.getIdConnteiner() + "</p></li>\n";
	}

}
