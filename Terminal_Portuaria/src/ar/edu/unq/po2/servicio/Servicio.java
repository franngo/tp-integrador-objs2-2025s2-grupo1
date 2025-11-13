package ar.edu.unq.po2.servicio;




import java.time.LocalDateTime;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


/*
 * @Autor: Matias Sanchez
 * */
public abstract class Servicio {
	
	protected Container containerServ;
	
	protected Cliente clienteConsignee;
	
	private LocalDateTime inicioServicio;
	
	
	
	public Servicio(Container containerServ, LocalDateTime horaInicio) {
		this.clienteConsignee=containerServ.getDuenioConsignee();
		this.containerServ = containerServ;
		this.inicioServicio = horaInicio;
	}
	
	public Cliente getClienteConsignee() {
		return clienteConsignee;
	}
	/*public LocalDateTime getInicioServicio() {
		return inicioServicio;
	}*/
	protected LocalDateTime getInicioServicio() {
		return inicioServicio;
	}
	
	public Container getContainer() {
		return containerServ;
	}
	
	public abstract String tipoServicio();
	
	/*
	 * cuando se invoca este metodo se supone que se descuenta el horario actual del de la instancia
	 * */
	public abstract double costoServicio(TerminalPortuaria terminalPortuaria, LocalDateTime horaCobro);
}