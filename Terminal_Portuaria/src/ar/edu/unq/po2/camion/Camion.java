package ar.edu.unq.po2.camion;

import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/**
* Describe un camión, el cual posee marca y modelo, patente y la orden de lo que transporta (si es que se encuentra transportando algo).
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
	 * Describe la marca y modelo del camión representado en un String.
	 */
	public String getMarcaYModelo() {
		return this.marcaYModelo;
	}

	/**
	 * Describe la combinación de letras y números de la placa de identificación del camión representado en un String.
	 */
	public String getPatente() {
		return this.patente;
	}
	
	/**
	 * Describe la orden actual de lo que transporta el camion actualmente. Null si no transporta nada.
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
	 * Transporta la carga actual del camión hacia la terminal portuaria dada para ser registrada como exportación.
	 * @param terminalPortuaria es la terminal portuaria a la que ingresa el camión, dejando su carga en el lugar para ser registrada como exportación.
	 */
	public void transportarExportacionA(TerminalPortuaria terminalPortuaria) {
		this.validarTransportarExportacion();
		terminalPortuaria.registrarExportacion(ordenActual);
	}
	
	/**
	 * Valida que el camión puede transportar una exportación. Solo puede hacerlo si tiene un trabajo asignado.
	 */
	private void validarTransportarExportacion() {
		if(this.estaDisponible()) {
			new RuntimeException("No puede transportar niguna exportación porque no tiene cargada una orden para ello.");
		}
	}
	
	/**
	 * Retira la carga que tiene que ir a buscar el camión de la terminal portuaria dada.
	 * @param terminalPortuaria es la terminal portuaria de la que se retira el camión, cargando en el lugar lo que debe transportar.
	 */
	public void retirarImportacionDe(TerminalPortuaria terminalPortuaria) {
		this.validarRetirarImportacion();
		terminalPortuaria.retirarImportacion(this);
	}
	
	/**
	 * Valida que el camión puede retirar una importación. Solo puede hacerlo si está disponible.
	 */
	private void validarRetirarImportacion() {
		if(!this.estaDisponible()) {
			new RuntimeException("No puede retirar la importación porque ya se encuentra cargado con una orden.");
		}
	}
	
	// #################################### MÉTODOS AUXILIARES ################################## \\
	
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