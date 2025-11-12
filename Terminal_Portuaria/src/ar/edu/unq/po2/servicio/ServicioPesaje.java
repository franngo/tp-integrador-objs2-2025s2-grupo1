package ar.edu.unq.po2.servicio;


import java.time.Clock;
import java.time.LocalDateTime;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioPesaje  extends Servicio{

	public ServicioPesaje(Container containerServ, LocalDateTime horaInicioServicio) {
		super(containerServ, horaInicioServicio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria, LocalDateTime horaInicioServicio) {
		// TODO Auto-generated method stub
		return terminalPortuaria.precioServicio(PrecioServicioTerminal.PESAJE);
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Pesaje";
	}
}
