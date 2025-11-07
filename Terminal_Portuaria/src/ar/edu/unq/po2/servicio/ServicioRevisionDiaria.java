package ar.edu.unq.po2.servicio;


import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioRevisionDiaria extends Servicio{

	public ServicioRevisionDiaria(Container containerServ) {
		super(containerServ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria) {
		// TODO Auto-generated method stub
		return terminalPortuaria.precioServicio(PrecioServicioTerminal.REVISIONDIARIA);
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio revision Diaria";
	}

}
