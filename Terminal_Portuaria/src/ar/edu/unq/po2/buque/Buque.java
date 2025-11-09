package ar.edu.unq.po2.buque;

import java.util.List;

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
	
	private String nombre;
	private Viaje viajeActual;
	private List<Orden> ordenes;
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Viaje getViajeActual() {
		return this.viajeActual;
	}
	
	public List<Orden> getOrdenes() {
		return this.ordenes;
	}
	
  /*
    Viaje viajeActual;
    Coordenada posicionActual;
    TerminalPortuaria terminalDestino;

    EstadoBuque estadoBuque;

    public Buque(){}

    public void avanzarHacia(double latitud, double longitud){
        posicionActual.nuevaPosicion(latitud,longitud);
        // this.notificarNuevaPosicion(); ESPERAR A QUE BENJA IMPLEMENTE TERMINAL
    }

    public void iniciarViaje(Viaje Viaje){
        viajeActual=viaje;
        estadoBuque = new OutBound();
    }


    public void notificarNuevaPosicion(TerminalPortuaria terminal){
        terminal.notificarPosicion(this);
    }
*/


}