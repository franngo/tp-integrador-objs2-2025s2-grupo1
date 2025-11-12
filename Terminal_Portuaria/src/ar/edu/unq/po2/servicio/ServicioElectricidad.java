package ar.edu.unq.po2.servicio;


import java.time.Clock;
import java.time.LocalDateTime;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.container.Reefer;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioElectricidad extends Servicio {
    
	
	public ServicioElectricidad(Container containerServ, LocalDateTime horaInicioServicio) {
		super(containerServ, horaInicioServicio);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria, LocalDateTime horaCobro) {
		// TODO Auto-generated method stub
		 //pensar
		 return terminalPortuaria.precioServicio(PrecioServicioTerminal.KILOWATTCONSUMIDO)
				* ((Reefer) containerServ).getConsumoPorHora();
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Electricidad";
	}
	
}
