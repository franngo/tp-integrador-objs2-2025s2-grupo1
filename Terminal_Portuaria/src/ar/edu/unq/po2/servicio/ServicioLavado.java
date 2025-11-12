package ar.edu.unq.po2.servicio;



import java.time.LocalDateTime;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


/*
 * @Autor: Matias Sanchez
 * */
public class ServicioLavado extends Servicio{
	
	public ServicioLavado(Container containerServ, LocalDateTime horaInicioServicio) {
		super(containerServ, horaInicioServicio);
		// TODO Auto-generated constructor stub
	}


	
	

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria, LocalDateTime horaCobro) {
		// TODO Auto-generated method stub
		if(this.superaCapacidad()) {
			return terminalPortuaria.precioServicio(PrecioServicioTerminal.LAVADOPESADO);
		}
		else {
			return terminalPortuaria.precioServicio(PrecioServicioTerminal.LAVADOCOMUN);
		}
	}
	
	public double volumenContainer() {
		return containerServ.getAltura() 
			   * containerServ.getAncho() 
			   * containerServ.getLargo();
	}
	
	public double volumenMaximo() {
		return 70.0d;
	}
	
	public boolean superaCapacidad() {
		return this.volumenContainer()>this.volumenMaximo();
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Lavado";
	}
}
