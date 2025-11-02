package ar.edu.unq.po2.terminal_portuaria;



import java.util.Set;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;

public class TerminalPortuaria {
	
	
	public void registrarEmpresaTransportista(EmpresaTransportista empresaTransportista) {
		// TODO Auto-generated method stub
	}

	public void retirarCamion(Camion camion) {
		// TODO Auto-generated method stub
	}

	public void ingresarCamion(Camion camion) {
		// TODO Auto-generated method stub
	}
	/*
	 * 
	 * mensaje para Benja: el constructor de TerminalPortuaria tiene que recibir un Set de 
	 * enumerativos PrecioServicioTerminal(o en su defecto instanciar la lista). Tambien habria que agrega
	 * un setter por si hay que agregar otro PrecioServicioTerminal
	 * */
	
	/*
	 * @Autor: Matias Sanchez
	 * metodos para los cobros de servicios
	 
	 * */
	 private Set<PrecioServicioTerminal> servicios;

	 public double precioServicio(PrecioServicioTerminal servicio) {
		 if (!servicios.contains(servicio)) {
			 throw new IllegalArgumentException("Servicio no disponible en esta terminal");
		 }
		 return servicio.getPrecio();
	 }
	 

}