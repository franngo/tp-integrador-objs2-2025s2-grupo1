package ar.edu.unq.po2.buque.estadosBuque;

import java.util.List;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;


public class Working extends EstadoBuque{
    
	boolean puedePartir = false;
	public Working(Buque miBuque) {
		super(miBuque);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean debeCambiarDeFase() {
		// TODO Auto-generated method stub
		return puedePartir;
	}
	
	public void puedePartir() {
		this.puedePartir=true;
	}

	@Override
	public void modificarEstadoBuque() {
		miBuque.establecerEstado(new Departing(miBuque));
		
	}
	@Override
	public void notificarEstado() {
		// no notifica nada a la terminal
		
	}

	@Override
	public void avanzar(double latitud, double longitud) {
		//No puede moverse tampoco en estado Working
	}

	// IMPLEMENTACIÓN BENJA
	
	@Override
	public void cargarOrdenes(List<Orden> ordenes) {
		this.miBuque.getOrdenes().addAll(ordenes);
	}

	@Override
	public List<Orden> getOrdenesADescargar(TerminalPortuaria terminalPortuaria) {
		return this.miBuque.getOrdenes().stream()
									    .filter(o -> terminalPortuaria.equals(o.getViaje().puertoDestino()))
									    .toList();
	}

	@Override
	public void finalizarDescargaDeOrdenes(List<Orden> ordenes) {
		this.miBuque.getOrdenes().removeAll(ordenes);
	}
}