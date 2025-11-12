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


/*
 * @Autor : Matias Sanchez
 *
 *
 * */

public class Buque implements BuqueObservado{
	
	
    List<Orden> ordenesExportacion;
   List<Orden> ordenesImportacion;
    
    boolean enViaje = false;
    
    
    
    String nombre;
    
    Viaje viajeActual = null;

    Coordenada posicionActual;
    
    TerminalPortuaria terminalAArribar; // el OBSERVER
  
    public Coordenada posicionActual() {return posicionActual;}
    
    public void nuevaPosicion(double latitud, double longitud) {
    	this.posicionActual.nuevaPosicion(latitud, longitud);
    }
    
    public void adscribirObservador(TerminalPortuaria terminalObservadora) {
    	this.terminalAArribar = terminalObservadora;
    }
    EstadoBuque estadoBuque;
    
    public EstadoBuque obtenerEstado() {
    	return estadoBuque;
    }

    public Buque(Coordenada coordenadas,
    		List<Orden> ordenesImportacion, 
    		List<Orden> ordenesExportacion,
    		String nombre){
    	
    	this.posicionActual=coordenadas;
    	this.ordenesExportacion=ordenesExportacion;
    	this.ordenesImportacion=ordenesImportacion;
    	this.nombre = nombre;
    }
    
    

    public void avanzarHacia(double latitud, double longitud){
        estadoBuque.avanzar(latitud,longitud);
        estadoBuque.actualizarSiSeRequiere();
        estadoBuque.notificarEstado();
     }
    
    
    
    public void sinViaje() {this.enViaje=false;}
    public void enViaje() {this.enViaje=true;}
   
	

	public void iniciarViaje(Viaje viajeActual) throws Exception{
		this.validarSiEstaEnViaje();
        this.viajeActual=viajeActual;
        this.terminalAArribar=viajeActual.puertoDestino();
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
    
    public void descargarContainers() {} // depende de los estados//NECESITA UN CONDICIONAL
    // PARA QUE LA INSTANCIA DE DWORKING DIGA QUE PUEDE 
    public void cargarContainers() {} // depende de los estados

	@Override
	public void establecerEstado(EstadoBuque nuevoEstado) {
		this.estadoBuque = nuevoEstado;
		
	}

	
    
	public List<Orden> getOrdenesExportacion() {
		return this.ordenesExportacion;
	}
	/*
	//Lo implementan Benja y Franco
	public Set<EmpresaTransportista> getOrdenes() {
		// TODO Auto-generated method stub
		return null;
  */
	public List<Orden> getOrdenesImportacion() {
		return this.ordenesImportacion;

	}

	public Viaje getViajeActual() {
		return this.viajeActual;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void notificarEstado() {
		// TODO Auto-generated method stub
		
	}
	
	public void iniciarTrabajos() throws Exception {
		if(this.obtenerEstado() instanceof Arrived){
			this.obtenerEstado().puedeIniciarWorking();
		    this.obtenerEstado().modificarEstadoBuque();
		}
		else {
			throw new Exception("El buque no se encuentra en la terminal");
		}
	} 
   
     public void finalizarTrabajos()  throws Exception{
    	 if(this.obtenerEstado() instanceof Working) {
    		 this.obtenerEstado().puedePartir();
    		 this.obtenerEstado().modificarEstadoBuque();
    	 }
    	 else {
    		 throw new Exception("El buque aun no se encuentra en condiciones de partir");
    	 }
     }
     
     
     //Cuando termina todo el proceso, el barco deberia cambiar su destino a la proxima terminal
     // que le indique el viaje. No nos interesa que sucede 
	 public void arriboConExito() {
		 //provisorio
		 this.terminalAArribar = null;
		// TODO Auto-generated method stub
		 // this.terminalAArribar = this.viaje().proximoDestino()
		
	 } 


    

   
   
    
   

}