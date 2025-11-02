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
	 * @Autor: Matias Sanchez
	 * metodos para los cobros de servicios
	 * el constructor tiene que recibir una lista de enumerativos
	 * */
	 private Set<PrecioServicioTerminal> servicios;

	 public double precioServicio(PrecioServicioTerminal servicio) {
		 if (!servicios.contains(servicio)) {
			 throw new IllegalArgumentException("Servicio no disponible en esta terminal");
		 }
		 return servicio.getPrecio();
	 }
	 

}