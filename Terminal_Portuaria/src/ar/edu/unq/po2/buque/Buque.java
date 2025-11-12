package ar.edu.unq.po2.buque;

import java.util.List;

import ar.edu.unq.po2.buque.estadosBuque.EstadoBuque;
import ar.edu.unq.po2.buque.estadosBuque.OutBound;
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
    
    
    
    
    String nombre;
    
    Viaje viajeActual = null;

    Coordenada posicionActual;
    
    TerminalPortuaria terminalAArribar; // el OBSERVER
  
    public Coordenada posicionActual() {return posicionActual;}
    
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
        estadoBuque.notificarEstado(this.terminalAArribar());
       
    
    }
    
    
   
	

	public void iniciarViaje(Viaje viajeActual){
        this.viajeActual=viajeActual;
        estadoBuque = new OutBound(this);
    }
    
    
    public void descargarContainers() {} // depende de los estados//NECESITA UN CONDICIONAL
    // PARA QUE LA INSTANCIA DE DWORKING DIGA QUE PUEDE 
    public void cargarContainers() {} // depende de los estados

	@Override
	public void establecerEstado(EstadoBuque nuevoEstado) {
		this.estadoBuque = nuevoEstado;
		
	}

	public TerminalPortuaria terminalAArribar() {
		return terminalAArribar;
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


    

   
   
    
   

}