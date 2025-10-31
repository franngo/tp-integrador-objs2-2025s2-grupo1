package ar.edu.unq.po2.buscador_de_viaje;

import java.util.List;

import ar.edu.unq.po2.viaje.Viaje;

abstract public class Condicion {
	
	/*
	 * Es la versión del patrón de diseño Composite que prioriza seguridad sobre transparencia, por lo que no existen
	 * operaciones de añadir o borrar en esta interfaz.
	 */
	
	abstract public List<Viaje> losQueCumplen(List<Viaje> viajes);
	
	/*
	 * Objetivo: Como se prioriza la seguridad, la interfaz de Condicion no tiene operaciones para añadir o borrar.
	 * Lo que sí tiene es una operación para que el objeto "se retorne a sí mismo como Conector/Composite".
	 * Si efectivamente es un Conector, se le podrán enviar exitosamente mensajes como los mencionados.
	 * En caso de no serlo y ser una Condicion Leaf/Hoja, se dará un fallo al enviarle ese tipo de mensajes.
	 * Es una capa más de seguridad respecto a la versión del patrón que prioriza la transparencia.
	 */
	public Conector getConector() {
		return null;
	}

}
