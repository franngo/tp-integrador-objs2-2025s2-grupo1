package ar.edu.unq.po2.camion;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/**
* Describe a un camión con marca y modelo, además de su patente.
* @author Benjamin Maldonado.
*/

public class Camion {
	private String marcaYModelo;
	private String patente;
	
	/**
	 * @param marcaYModelo es la marca y el modelo del camión.
	 * @param patente es la combinación de letras y números de la placa de identificación del camión.
	 */
	public Camion(String marcaYModelo, String patente) {
		this.marcaYModelo = marcaYModelo;
		this.patente = patente;
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
	 * Realiza el ingreso del camión a la terminal portuaria dada.
	 * @param terminalPortuaria es la terminal portuaria a la que ingresa el camión.
	 */
	public void ingresarA(TerminalPortuaria terminalPortuaria) {

	}
	
	/**
	 * Realiza el retiro del camión a la terminal portuaria dada.
	 * @param terminalPortuaria es la terminal portuaria de la que se retira el camión.
	 */
	public void retirarseDe(TerminalPortuaria terminalPortuaria) {
		
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