package ar.edu.unq.po2.buscador_de_viaje;

import java.util.List;

import ar.edu.unq.po2.viaje.Viaje;

abstract public class Conector extends Condicion{
	
	private Condicion subcondicionIzquierda;
	private Condicion subcondicionDerecha;
	
	public Conector(Condicion subcondicionIzquierda, Condicion subcondicionDerecha) {
		this.subcondicionIzquierda = subcondicionIzquierda;
		this.subcondicionDerecha = subcondicionDerecha;
	}
	
	abstract public List<Viaje> losQueCumplen(List<Viaje> viajes);
	
	public Conector getConector() {
		return this;
	}
	
	/*
	 * En vez de la operación getChild(int i), como solo tengo 2 hijos, hago una operación para cada uno.
	 */
	
	public Condicion getSubcondicionIzquierda() {
		return subcondicionIzquierda;
	}
	
	public Condicion getSubcondicionDerecha() {
		return subcondicionDerecha;
	}
	
	/*
	 * Si el conector ya tiene una subcondición izquierda y una derecha, entonces no le puedo agregar otra subcondición,
	 * ya que los conectores lógicos modelados (and y or) son binarios.
	 */
	public void addCondicion(Condicion cond) {
		if(subcondicionIzquierda == null) {
			subcondicionIzquierda = cond;
		} else if(subcondicionDerecha == null) {
			subcondicionDerecha = cond;
		}
	}
	
	public void removeCondicion(Condicion cond) {
		if(subcondicionIzquierda == cond) {
			subcondicionIzquierda = null;
		} 
		if(subcondicionDerecha == cond) {
			subcondicionDerecha = null;
		}
	}

}
