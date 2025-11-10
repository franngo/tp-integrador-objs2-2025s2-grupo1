package ar.edu.unq.po2.orden;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.generadorDeReportes.VisitorReporte;
import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.servicio.ServicioExcedente;
import ar.edu.unq.po2.viaje.Viaje;

public abstract class Orden {
	private ConcreteVisitorContainer visitanteContainer;
	private Camion camion;
	private Chofer chofer;
	private Container carga;
	private Viaje viaje;
	private boolean estaEnViaje;
	private List<Servicio> serviciosACobrar;	
	
	
	/**
	 * @param camion es el camion que tiene la orden.
	 * @param chofer es el chofer que tiene la orden.
	 * @param container es la carga que tiene la orden.
	 * @param viaje es el viaje que tiene la orden.
	 */	
	public Orden(Camion camion, Chofer chofer, Container container, Viaje viaje) {
		this.camion = camion;
		this.chofer = chofer;
		this.carga  = container;
		this.viaje  = viaje;
		this.estaEnViaje = false;
		this.serviciosACobrar = new ArrayList<Servicio>();
	}

	/**
	 * Describe la fecha de salida del viaje que tiene la orden.
	 */	
	public LocalDateTime fechaDeSalida() {
		return viaje.fechaDeSalida();
	}
	
	/**
	 * Describe la fecha de llegada del viaje que tiene la orden.
	 */	
	public LocalDateTime fechaDeLlegada() {
		return viaje.fechaDeLlegada();
	}

	/**
	 * Describe el camion que tiene la orden.
	 */	
	public Camion getCamion() {
		return camion;
	}

	/**
	 * Describe el chofer que tiene la orden.
	 */	
	public Chofer getChofer() {
		return chofer;
	}
	
	/**
	 * Describe el container que tiene la orden.
	 */
	public Container getCarga() {
		return this.carga;
	}

	/**
	 * Describe el consignee (el dueño de la carga) que tiene la orden.
	 */	
	public Cliente getConsignee() {
		return carga.getDuenioConsignee();
	}
	
	/**
	 * Describe los servicios que tiene la orden.
	 */
    public List<Servicio> getServiciosOrden(){
    	return new ArrayList<Servicio>(serviciosACobrar);
    }
	
	/**
	 * Crea los servicios a cobrar en la orden en base a la carga que tiene.
	 */
	public void crearServiciosACobrar() {
		this.validarCrearServiciosACobrar();
      	List<Servicio> serviciosACobrar = carga.acceptVisitor(visitanteContainer);
		serviciosACobrar.addAll(serviciosACobrar);
	}
	
	/**
	 * Valida si se pueden crear los servicios a cobrar en la orden.
	 */
	private void validarCrearServiciosACobrar() {
		if(!serviciosACobrar.isEmpty()) {
			throw new RuntimeException("No se pueden crear los servicios a cobrar porque ya fueron creados anteriormente.");
		}
	}
	
	/**
	 * Elimina el servicio de almacenamiento excedente que tiene la orden (si existe en la misma).
	 */
	public void eliminarServicioExcedente() {
	    Iterator<Servicio> iterador = serviciosACobrar.iterator();
	    
	    while(iterador.hasNext()) {
	        Servicio s = iterador.next();
	        
	        if(s instanceof ServicioExcedente) {
	        	iterador.remove();
	            break;
	        }
	    }
	}

	/**
	 * ...
	 */
    abstract public String accept(VisitorReporte visitor);
}