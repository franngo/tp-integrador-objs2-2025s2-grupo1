package ar.edu.unq.po2.orden;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.viaje.Viaje;

public class OrdenDeExportacion extends Orden {

	public OrdenDeExportacion(Camion camion, Chofer chofer, Container container, Viaje viaje) {
		super(camion, chofer, container, viaje);
	}
  
  public String accept(VisitorReporte visitor) {
		return visitor.visitOrdenDeExportacion(this);
	}
}
