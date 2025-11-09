package ar.edu.unq.po2.empresa_transportista;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;

/**
* Describe una empresa transportista capaz de poseer choferes y camiones; inicialmente vacía.
* @author Benjamin Maldonado.
*/

public class EmpresaTransportista {
	private Set<Chofer> choferes;
	private Set<Camion> camiones;
	
	public EmpresaTransportista() {
		this.choferes = new HashSet<Chofer>();
		this.camiones = new HashSet<Camion>();
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
	 * Indica si tiene algún camión en la empresa transportista que se encuentre disponible para ser asignado a un trabajo.
	 */
	public boolean tieneCamionDisponible() {
		return !this.camionesDisponibles().isEmpty();
	}
	
	/**
	 * Describe un camión disponible de la empresa transportista para ser asignado a un trabajo.
	 */
	public Camion contratarCamion() {
		this.validarContratarCamion();
		return this.camionesDisponibles().getFirst();
	}
	
	/**
	 * Valida si se encuentra algún camión disponible para ser contratado.
	 */
	private void validarContratarCamion() {
		if(!this.tieneCamionDisponible()) {
			new RuntimeException("No hay ningún camión disponible.");
		}
	}
	
	/**
	 * Describe todos los camiones que se encuentran disponibles en la empresa transportista para ser asignados a un trabajo.
	 */
	private List<Camion> camionesDisponibles(){
		return camiones.stream()
				   	   .filter(camion -> camion.estaDisponible())
				       .toList();
	}
	
	/**
	 * Añade el chofer dado a la empresa transportista.
	 * @param chofer es el chofer a añadir en la empresa transportista.
	 */
	public void añadirChofer(Chofer chofer) {
		choferes.add(chofer);
	}
	
	/**
	 * Indica si tiene algún chofer que se encuentre disponible en la empresa transportista para ser asignado a un trabajo.
	 */
	public boolean tieneChoferDisponible() {
		return !this.choferesDisponibles().isEmpty();
	}
	
	/**
	 * Describe un chofer disponible de la empresa transportista para ser asignado a un trabajo.
	 */
	public Chofer contratarChofer() {
		this.validarContratarChofer();
		return this.choferesDisponibles().getFirst();
	}
	
	/**
	 * Valida si se encuentra algún chofer disponible para ser contratado.
	 */
	private void validarContratarChofer() {
		if(!this.tieneChoferDisponible()) {
			new RuntimeException("No hay ningún chofer disponible.");
		}
	}
	
	/**
	 * Describe todos los choferes que se encuentran disponibles en la empresa transportista para ser asignados a un trabajo.
	 */
	private List<Chofer> choferesDisponibles(){
		return choferes.stream()
				   	   .filter(chofer -> chofer.estaDisponible())
				   	   .toList();
	}

	/**
	 * Indica si la empresa transportista tiene registrados al camion y al chofer dados.
	 * @param camion es el camion a verificar si está registrado en la empresa transportista.
	 * @param chofer es el chofer a verificar si está registrado en la empresa transportista.
	 */
	public boolean tieneCamionYChoferRegistrados(Camion camion, Chofer chofer) {
		return camiones.contains(camion) && choferes.contains(chofer);
	}
}