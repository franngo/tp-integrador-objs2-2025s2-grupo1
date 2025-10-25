package ar.edu.unq.po2.camion;

import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/**
* Describe a un camión con marca y modelo, su patente y la orden de lo que transporta (si transporta algo).
* @author Benjamin Maldonado.
*/

public class Camion {
	private String marcaYModelo;
	private String patente;
	private Orden ordenActual;
	
	/**
	 * @param marcaYModelo es la marca y el modelo del camión.
	 * @param patente es la combinación de letras y números de la placa de identificación del camión.
	 */
	public Camion(String marcaYModelo, String patente) {
		this.marcaYModelo = marcaYModelo;
		this.patente = patente;
		this.ordenActual = null;
	}
	
	/**
	 * Describe la marca y modelo del camión.
	 */
	public String getMarcaYModelo() {
		return this.marcaYModelo;
	}

	/**
	 * Describe la combinación de letras y números de la placa de identificación del camión.
	 */
	public String getPatente() {
		return this.patente;
	}
	
	/**
	 * Describe la orden actual de lo que transporta el camion actualmente (puede no transportar nada).
	 */
	public Orden getOrdenActual() {
		return this.ordenActual;
	}
	
	/**
	 * Indica si el camion se encuentra disponible para ser asignado a un trabajo.
	 */
	public boolean estaDisponible() {
		return this.ordenActual == null;
	}

	/**
	 * Cambia la orden actual del camión por la orden dada por parámetro.
	 */
	public void cambiarOrdenActualPor(Orden nuevaOrden) {
		this.ordenActual = nuevaOrden;
	}
	
	/**
	 * Ingresa el camión de la terminal portuaria dada.
	 * @param terminalPortuaria es la terminal portuaria a la que ingresa el camión.
	 */
	public void ingresarA(TerminalPortuaria terminalPortuaria) {
		terminalPortuaria.ingresarCamion(this);
	}
	
	/**
	 * Retira el camión de la terminal portuaria dada.
	 * @param terminalPortuaria es la terminal portuaria de la que se retira el camión.
	 */
	public void retirarseDe(TerminalPortuaria terminalPortuaria) {
		terminalPortuaria.retirarCamion(this);
	}
	
	@Override
	public boolean equals(Object object) {
		return (this == object) || (this.esCamion(object) && (this.esElMismoQue(object)));
	}
	
	private boolean esCamion(Object object) {
		return object instanceof Camion;
	}
	
	private boolean esElMismoQue(Object object) {
		Camion camionAComparar = (Camion) object;
		return patente.equals(camionAComparar.getPatente());
	}
	
	@Override
	public int hashCode() {
		return patente.hashCode();
	}
}