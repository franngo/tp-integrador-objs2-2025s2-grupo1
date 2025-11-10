package ar.edu.unq.po2.terminal_portuaria;



import java.util.Set;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;

public class TerminalPortuaria implements TerminalObservadora{
	
	
	public TerminalPortuaria() {
		
	}
	Coordenada coordenadasTerminal;
	
	public Coordenada coordenadasTerminal() {
		return coordenadasTerminal;
	}
	
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
		 
		 this.validarServicio(servicio);
		 return servicio.getPrecio();
	 }

	 private void validarServicio(PrecioServicioTerminal servicio) {
		// TODO Auto-generated method stub
		 if (!servicios.contains(servicio)) {
			 throw new IllegalArgumentException("Servicio no disponible en esta terminal");
		 }
	 }

	 
	 
	 
	 // METODOS PARA LO QUE  ES EL BUQUE, REFACTORIZAR UNA VEZ ESTEN IMPLEMENTADOS

	 public void actualizar(Buque buque) {
	
		
	 }

	 public boolean puedeIniciarWorking(Buque miBuque) {
		// TODO Auto-generated method stub
		return false;
	 }

	 public boolean partidaHabilitada(Buque miBuque) {
		// TODO Auto-generated method stub
		return false;
	 }

	

	 
	 
	 
	 

}