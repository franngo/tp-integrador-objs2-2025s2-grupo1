package ar.edu.unq.po2.servicio;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioExcedente extends Servicio{
  
	public ServicioExcedente(Container containerServ) {
		super(containerServ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria) {
		
		return (double)(terminalPortuaria.precioServicio(PrecioServicioTerminal.DIAEXCEDENTE) * 
				this.diasACobrar());
	
	}
	
	public int diasACobrar() {
	
		return (int) ChronoUnit.DAYS.between(LocalDateTime.now(), this.getInicioServicio());
							
	}
    
	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Excedente";
	}
}
