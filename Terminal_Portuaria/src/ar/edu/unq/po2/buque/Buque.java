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
public class Buque {
	
	
    List<Orden> ordenesExportacion;
    List<Orden> ordenesImportacion;
    
    
    Viaje viajeActual = null;
    Coordenada posicionActual;
    
    TerminalPortuaria terminalAArribar;
    /*
    public TerminalPortuaria terminalActual() {
    	return viajeActual.
    }*/

    EstadoBuque estadoBuque;

    public Buque(Coordenada coordenadas,
    		List<Orden> ordenesImportacion, 
    		List<Orden> ordenesExportacion){
    	
    	this.posicionActual=coordenadas;
    	this.ordenesExportacion=ordenesExportacion;
    	this.ordenesImportacion=ordenesImportacion;
    }
    
    //public TerminalPortuaria terminalAArribar() {
    	//return viaje.terminalSiguiente()
    //} 

    public void avanzarHacia(double latitud, double longitud){
        posicionActual.nuevaPosicion(latitud,longitud);
        // this.notificarNuevaPosicion(); ESPERAR A QUE BENJA IMPLEMENTE TERMINAL
    }

    public void iniciarViaje(Viaje viajeActual){
        this.viajeActual=viajeActual;
        estadoBuque = new OutBound();
    }
    
    

   /*
    public void notificarNuevaPosicion(TerminalPortuaria terminal){
        terminal.notificarPosicion(this);
    }*/



}