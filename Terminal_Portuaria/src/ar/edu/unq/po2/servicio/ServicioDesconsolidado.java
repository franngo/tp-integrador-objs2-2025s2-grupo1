package ar.edu.unq.po2.servicio;



import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.Container;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


/*
 * @Autor Matias Sanchez
 * Este servicio es solo para Dry
 * */
public class ServicioDesconsolidado extends Servicio{
    
	private Cliente clienteParticular;
	private double monto;
	
	public ServicioDesconsolidado(Container containerServ,double monto) {
		super(containerServ);
		
		// TODO Auto-generated constructor stub
	}



	
	public Cliente getDuenioParticular() {
		return clienteParticular;
	}

	

	

	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria) {
		// TODO Auto-generated method stub
		return this.monto();
	}

	private double monto() {
		// TODO Auto-generated method stub
		return monto;
	}




	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Desconsolidado";
	}

}
