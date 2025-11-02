package ar.edu.unq.po2.servicio;


import ar.edu.unq.po2.container.Dry;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


/*
 * @Autor Matias Sanchez
 * Este servicio es solo para Dry
 * */
public class ServicioDesconsolidado extends Servicio{

	public ServicioDesconsolidado(Dry containerServ) {
		super(containerServ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Desconsolidad";
	}

}
