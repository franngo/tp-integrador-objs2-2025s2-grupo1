package ar.edu.unq.po2.servicio;


import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioExcedente extends Servicio{

	public ServicioExcedente(Container containerServ) {
		super(containerServ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria) {
		// TODO Auto-generated method stub
		return terminalPortuaria.precioServicio(PrecioServicioTerminal.DIAEXCEDENTE);
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Excedente";
	}
}
