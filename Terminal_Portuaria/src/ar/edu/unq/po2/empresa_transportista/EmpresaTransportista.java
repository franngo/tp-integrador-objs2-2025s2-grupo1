package ar.edu.unq.po2.empresa_transportista;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.registrable.Registrable;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/**
* Describe una empresa transportista que posee una lista de choferes y camiones.
* @author Benjamin Maldonado.
*/

public class EmpresaTransportista implements Registrable {
	private Set<Chofer> choferes;
	private Set<Camion> camiones;
	
	public EmpresaTransportista() {
		this.choferes = new HashSet<Chofer>();
		this.camiones = new HashSet<Camion>();
	}

	/**
	 * Realiza el registro de la empresa transportista a la terminal portuaria dada.
	 * @param terminalPortuaria es la terminal portuaria en la que se registra la empresa transportista.
	 */
	@Override
	public void registrarse(TerminalPortuaria terminalPortuaria) {
		terminalPortuaria.registrarEmpresaTransportista(this);
	}
	
	/**
	 * Describe el conjunto de choferes que tiene la empresa transportista.
	 */
	public Set<Chofer> getChoferes(){
	    return new HashSet<Chofer>(choferes);
	}
	
	/**
	 * Describe el conjunto de camiones que tiene la empresa transportista.
	 */
	public Set<Camion> getCamiones(){
	    return new HashSet<Camion>(camiones);
	}
	
	/**
	 * Añade el camión dado a la empresa transportista.
	 * @param camion es el camión a añadir en la empresa transportista.
	 */
	public void añadirCamion(Camion camion) {
		camiones.add(camion);
	}
	
	/**
	 * Añade el chofer dado a la empresa transportista.
	 * @param chofer es el chofer a añadir en la empresa transportista.
	 */
	public void añadirChofer(Chofer chofer) {
		choferes.add(chofer);
	}
	
	/**
	 * Indica si el chofer dado se encuentra en los choferes de la empresa transportista.
	 * @param chofer es el chofer a verificar si existe en la empresa transportista.
	 */
	public boolean tieneChofer(Chofer chofer) {
		return choferes.contains(chofer);
	}
	
	/**
	 * Indica si el camión dado se encuentra en los camiones de la empresa transportista.
	 * @param camion es el camion a verificar si existe en la empresa transportista.
	 */
	public boolean tieneCamion(Camion camion) {
		return camiones.contains(camion);
	}
}