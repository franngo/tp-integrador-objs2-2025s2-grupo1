package ar.edu.unq.po2.orden;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.generadorDeReportes.VisitorReporte;
import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

/**
* Describe una orden.
* @author Benjamin Maldonado & Matias Sanchez.
*/

public class Orden {
	private ConcreteVisitorContainer visitanteContainer;
	private Camion camion;
	private Chofer chofer;
	private Container carga;
	private Viaje viaje;
	private Cliente shipper;
	private boolean estaEnViaje;
	private List<Servicio> serviciosACobrar;	
	
	/**
	 * @param camion es el camion encargado de transportar la orden.
	 * @param chofer es el chofer encargado de transportar la orden.
	 * @param container es la carga que tiene asociada la orden.
	 * @param viaje es el viaje que tiene la orden.
	 * @param shipper el encargado de exportar la orden.
	 */	
	public Orden(Camion camion, Chofer chofer, Container container, Viaje viaje, Cliente shipper) {
		this.camion = camion;
		this.chofer = chofer;
		this.carga  = container;
		this.viaje  = viaje;
		this.shipper = shipper;
		this.estaEnViaje = false;
		this.serviciosACobrar = new ArrayList<Servicio>();
		this.visitanteContainer = new ConcreteVisitorContainer();
	}
     
	public ConcreteVisitorContainer visitanteContainer() {
		return visitanteContainer;
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
	 * Describe el shipper (el encargado de exportar la carga) que tiene la orden.
	 */	
	public Cliente getShipper() {
		return shipper;
	}
	
	public Viaje getViaje() {
		return viaje;
	}

	/**
	 * Indica si la orden se encuentra en viaje o no.
	 */	
	public boolean getEstaEnViaje() {
		return estaEnViaje;
	}
	
	/**
	 * Modifica si la orden se encuentra en viaje o no mediante el valor booleano dado.
	 */	
	public void setEstaEnViaje(boolean valor) {
		this.estaEnViaje = valor;
	}
	
	/**
	 * Describe los servicios que tiene la orden.
	 */
    public List<Servicio> getServiciosOrden(){
    	return new ArrayList<Servicio>(serviciosACobrar);
    }
    
    /**
     * Describe la fecha de salida del viaje que tiene la orden.
     */	
    public LocalDateTime fechaDeSalida() {
    	return viaje.fechaDeSalida();
    }
    
    /**
     * Describe la fecha de llegada del viaje que tiene la orden.
     * @param terminalPortuaria es la terminal portuaria que se toma como fecha de llegada.
     */	
    public LocalDateTime fechaDeLlegadaA(TerminalPortuaria terminalPortuaria) {
    	return viaje.fechaDeLlegadaATerminal(terminalPortuaria);
    }
	
	/**
	 * Crea los servicios a cobrar en la orden en base a la carga que tiene.
	 */
	public void crearServiciosACobrar() {
		this.validarCrearServiciosACobrar();
      	List<Servicio> serviciosACobrar = carga.acceptVisitor(this.visitanteContainer());
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
	 * Acepta el visitor del reporte, el cual se encarga de devolver un string en base a la orden.
	 */
	public String accept(VisitorReporte visitor) {
		return visitor.visitOrden(this);
	}
}