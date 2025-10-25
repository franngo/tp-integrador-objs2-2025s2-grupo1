package ar.edu.unq.po2.registrable;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/**
* Define el protocolo de un objeto que es considerado registrable.
* @author Benjamin Maldonado.
*/

public interface Registrable {
	
	/**
	 * Define el registro del objeto hacia la terminal portuaria dada.
	 * @param terminalPortuaria es la terminal portuaria en la que se registra el objeto.
	 */
	public void registrarse(TerminalPortuaria terminalPortuaria);
}