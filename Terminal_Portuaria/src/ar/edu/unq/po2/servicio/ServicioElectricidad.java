package ar.edu.unq.po2.servicio;



import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
		 return this.precioPorHoraConsumo(terminalPortuaria) * this.horasServicio(horaCobro);
	}
	
	private double precioPorHoraConsumo(TerminalPortuaria terminal) {
		return terminal.precioServicio(PrecioServicioTerminal.KILOWATTCONSUMIDO)
				* ((Reefer) containerServ).getConsumoPorHora();
	}
	
	
	/*
	 * Son las horas que el container paso en la terminal
	 * */
	private double horasServicio(LocalDateTime horaCobro) {
		
		double horas =  ChronoUnit.HOURS.between(this.getInicioServicio(),horaCobro);
		return Math.max(1.0, horas);
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Electricidad";
	}
	
}
