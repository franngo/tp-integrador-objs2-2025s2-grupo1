package ar.edu.unq.po2.servicio;




import ar.edu.unq.po2.container.Container;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


/*
 * @Autor Matias Sanchez
 * Este servicio es solo para Dry
 * */
public class ServicioDesconsolidado extends Servicio{
	
	public ServicioDesconsolidado(Container containerServ) {
		super(containerServ);
	}
    /*
     * Se necesita que cuando la terminal cobre el servicio divida 
     * el costo por la cantidad de clientes que tenga el containerServ 
     * */
	@Override
	public double costoServicio(TerminalPortuaria terminalPortuaria) {
		// TODO Auto-generated method stub
		return terminalPortuaria.precioServicio(PrecioServicioTerminal.PRECIODESCONSOLIDADO);
	}

	@Override
	public String tipoServicio() {
		// TODO Auto-generated method stub
		return "Servicio Desconsolidado";
	}
}