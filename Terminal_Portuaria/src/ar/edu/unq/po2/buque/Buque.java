package ar.edu.unq.po2.buque;

import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.buque.estadosBuque.EstadoBuque;
import ar.edu.unq.po2.buque.estadosBuque.OutBound;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
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
    		List<Orden> ordenesExportacion){
    	
    	this.posicionActual=coordenadas;
    	this.ordenesExportacion=ordenesExportacion;
    	this.ordenesImportacion=ordenesImportacion;
    }
    
    

    public void avanzarHacia(double latitud, double longitud){
        posicionActual.nuevaPosicion(latitud,longitud);
        this.actualizarEstado();
        estadoBuque.notificarEstado(this.terminalAArribar());
    }
    
    
    public void actualizarEstado(){
    	//nos mandamos a nosotros y a la terminaldonde debemos ir
    	estadoBuque.actualizarSiSeRequiere();
    }
    
    public void notificarEstado(TerminalPortuaria terminalAArribar) {
		terminalAArribar.actualizar(this);
		
	}

	public void iniciarViaje(Viaje viajeActual){
        this.viajeActual=viajeActual;
        estadoBuque = new OutBound(this);
    }
    
    
    public void descargarContainers() {}
    public void cargarContainers() {}

	@Override
	public void establecerEstado(EstadoBuque nuevoEstado) {
		this.estadoBuque = nuevoEstado;
		
	}

	
	
	public TerminalPortuaria terminalAArribar() {
		return terminalAArribar;
	}
    
	
	//Lo implementan Benja y Franco
	public Set<EmpresaTransportista> getOrdenes() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public Orden getViajeActual() {
		// TODO Auto-generated method stub
		return null;
	}
    

   
   
    
   

}