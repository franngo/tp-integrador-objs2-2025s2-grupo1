package ar.edu.unq.po2.servicio;



import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class ServicioExcedente extends Servicio{
  
	public ServicioExcedente(Container containerServ,LocalDateTime inicioServicio) {
		super(containerServ,inicioServicio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria,LocalDateTime horaCobro) {
		  if(this.excedioTiempoDeRetiro(horaCobro,terminalPortuaria)) {
		         return (double)(terminalPortuaria.precioServicio(PrecioServicioTerminal.DIAEXCEDENTE) * 
				       this.diasACobrar(horaCobro));
	      }
		  else {
			  return this.costoCero();
		  }
	
	}
	
	private boolean excedioTiempoDeRetiro(LocalDateTime horaCobro, TerminalPortuaria terminal) {
		
		double horasAlmacenaje =  ChronoUnit.DAYS.between(horaCobro,this.getInicioServicio());
		return horasAlmacenaje > terminal.limiteHorasAlmacenaje();
	}
	

	public double diasACobrar(LocalDateTime horaCobro) {
	//TODO REIMPLEMENTAR
		  double dias = ChronoUnit.DAYS.between(horaCobro, this.getInicioServicio()) - 1;
		
		return Math.max(1.0,dias);				
	}
	
	private double costoCero() {return 0d;}
    
	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Excedente";
	}
}
