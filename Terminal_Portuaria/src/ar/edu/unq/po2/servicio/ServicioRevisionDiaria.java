package ar.edu.unq.po2.servicio;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioRevisionDiaria extends Servicio{

	public ServicioRevisionDiaria(Container containerServ, LocalDateTime horaInicioServicio) {
		super(containerServ,horaInicioServicio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria,LocalDateTime horaCobro) {
		// TODO Auto-generated method stub
		return terminalPortuaria.precioServicio(PrecioServicioTerminal.REVISIONDIARIA) 
				* this.diasRevisionDiaria(horaCobro) ;
	}
	
	/*
	 * Los dias que el tanque paso en la terminal
	 * */
	private double diasRevisionDiaria(LocalDateTime horaCobro) {
		long dias = ChronoUnit.DAYS.between(horaCobro, this.getInicioServicio());
	    return Math.max(1, dias); 
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio revision Diaria";
	}

}
