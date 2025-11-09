package ar.edu.unq.po2.orden;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.viaje.Viaje;

public abstract class Orden {
	private ConcreteVisitorContainer visitanteContainer;
	private List<Servicio> serviciosACobrar;	
	private Camion camion;
	private Chofer chofer;
	private Container container;
	private Viaje viaje;
	private boolean estaEnViaje;
	
	public Orden(Camion camion, Chofer chofer, Container container, Viaje viaje) {
		this.serviciosACobrar = new ArrayList<Servicio>();
		this.camion = camion;
		this.chofer = chofer;
		this.container = container;
		this.viaje = viaje;
		this.estaEnViaje = false;
	}

	/**
	 * 
	 */	
	public LocalDateTime fechaDeSalida() {
		return viaje.fechaDeSalida();
	}
	
	/**
	 * 
	 */	
	public LocalDateTime fechaDeLlegada() {
		return viaje.fechaDeLlegada();
	}

	/**
	 * 
	 */	
	public Camion getCamion() {
		return camion;
	}

	/**
	 * 
	 */	
	public Chofer getChofer() {
		return chofer;
	}
	
	/**
	 * 
	 */	
	public Cliente getConsignee() {
		return container.getDuenioConsignee();
	}
	
	/**
	 * 
	 */
    public List<Servicio> getServiciosOrden(){
    	return new ArrayList<Servicio>(serviciosACobrar);
    }
	
	/**
	 * 
	 */
	public void crearServiciosACobrar() {
		this.validarCrearServiciosACobrar();
      	List<Servicio> serviciosACobrar = container.acceptVisitor(visitanteContainer);
		serviciosACobrar.addAll(serviciosACobrar);
	}
	
	/**
	 * 
	 */
	private void validarCrearServiciosACobrar() {
		if(!serviciosACobrar.isEmpty()) {
			throw new RuntimeException("No se pueden crear los servicios a cobrar porque ya fueron creados anteriormente.");
		}
    
    public Container getCarga() {
		return this.carga;
	  }
	
	  abstract public String accept(VisitorReporte visitor);
	}
}