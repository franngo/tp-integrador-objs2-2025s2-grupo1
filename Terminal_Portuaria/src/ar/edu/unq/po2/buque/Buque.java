package ar.edu.unq.po2.buque;

import java.util.List;

import ar.edu.unq.po2.buque.estadosBuque.Arrived;

import ar.edu.unq.po2.buque.estadosBuque.EstadoBuque;
import ar.edu.unq.po2.buque.estadosBuque.OutBound;
import ar.edu.unq.po2.buque.estadosBuque.Working;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;


/**
* Describe un buque.
* @author Matias Sanchez 
* @author Benjamin Maldonado.
*/
public class Buque implements BuqueObservado{
    List<Orden> ordenes;
    boolean enViaje;
    String nombre;
    Viaje viajeActual;
    Coordenada posicionActual;
    TerminalPortuaria terminalAArribar; // El observer
    EstadoBuque estadoBuque; // No debería ser inicializado con null?
  
    public Buque(Coordenada coordenadas, List<Orden> ordenes, String nombre){
    	this.enViaje = false;
    	this.viajeActual = null;
    	this.posicionActual = coordenadas;
    	this.ordenes = ordenes;
    	this.nombre = nombre;
    }

    public Coordenada posicionActual() {
    	return posicionActual;
    }
    
    public void nuevaPosicion(double latitud, double longitud) {
    	this.posicionActual.nuevaPosicion(latitud, longitud);
    }
    
    public void adscribirObservador(TerminalPortuaria terminalObservadora) {
    	this.terminalAArribar = terminalObservadora;
    }
    
    public EstadoBuque obtenerEstado() {
    	return estadoBuque;
    }

    public void avanzarHacia(double latitud, double longitud){
        estadoBuque.avanzar(latitud,longitud);
        estadoBuque.actualizarSiSeRequiere();
        estadoBuque.notificarEstado();
     }
    
    public void sinViaje() {
    	this.enViaje = false;
    }
    
    public void enViaje() {
    	this.enViaje = true;
    }

	public void iniciarViaje(Viaje viajeActual) throws Exception{
		this.validarSiEstaEnViaje();
        this.viajeActual = viajeActual;
        this.terminalAArribar = viajeActual.puertoDestino();
        estadoBuque = new OutBound(this);
    }
	
	private void validarSiEstaEnViaje() throws Exception {
		if(enViaje) { throw new Exception("El barco ya esta en viaje");}
	}
    
	public void terminalAArribar(TerminalPortuaria terminal) {
		this.terminalAArribar=terminal;
	}
	
	public TerminalPortuaria terminalAArribar() {
		return terminalAArribar;
	}
    
	public Viaje getViajeActual() {
		return this.viajeActual;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void establecerEstado(EstadoBuque nuevoEstado) {
		this.estadoBuque = nuevoEstado;
		
	}

	@Override
	public void notificarEstado() {
		
	}
     
	public void iniciarTrabajos() {
		this.validarIniciarTrabajos();
		this.obtenerEstado().puedeIniciarWorking();
		this.obtenerEstado().modificarEstadoBuque();
	}
	
	private void validarIniciarTrabajos() {
		if(!(this.obtenerEstado() instanceof Arrived)) {
			throw new RuntimeException("El buque no se encuentra en la terminal");
		}
	}
   
     public void finalizarTrabajos() {
    	 this.validarFinalizarTrabajos();
    	 this.obtenerEstado().puedePartir();
    	 this.obtenerEstado().modificarEstadoBuque();
     }
     
     private void validarFinalizarTrabajos() {
    	 if(!(this.obtenerEstado() instanceof Working)) {
    		 throw new RuntimeException("El buque aun no se encuentra en condiciones de partir");
    	 }
     }

	 public void finalizarDescargaDeOrdenes(List<Orden> ordenesDescargadas) {
		
	 }

	 public List<Orden> getOrdenesADescargar(TerminalPortuaria terminalPortuaria) {
		return null;
	 }

	 public List<Orden> getOrdenes() {
		return ordenes;
	 }

	 public void cargarOrdenes(List<Orden> ordenes) {
		 
	 } 
     
     //Cuando termina todo el proceso, el barco deberia cambiar su destino a la proxima terminal
     // que le indique el viaje. No nos interesa que sucede 
	 public void arriboConExito() {
		  this.terminalAArribar = null;
	 } 
}