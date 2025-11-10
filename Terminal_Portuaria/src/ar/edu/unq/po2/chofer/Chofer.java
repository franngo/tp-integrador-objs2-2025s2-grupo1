package ar.edu.unq.po2.chofer;

/**
* Describe un chofer, el cual posee nombre y apellido, DNI, y un booleano que indica si se encuentra disponible para ser asignado a un trabajo.
* @author Benjamin Maldonado.
*/

public class Chofer {
	private String nombreYApellido;
	private String dni;
	private boolean estaDisponible;

	/**
	 * @param nombreYApellido es el nombre y el apellido del chofer.
	 * @param dni es el número del Documento Nacional de Identidad del chofer.
	 */
	public Chofer(String nombreYApellido, String dni) {
		this.nombreYApellido = nombreYApellido;
		this.dni = dni;
		this.estaDisponible = true;
	}
	
	/**
	 * Describe el número del DNI del chofer representado en un String.
	 */
	public String getDni() {
		return this.dni;
	}
	
	/**
	 * Describe el nombre y el apellido del chofer representado en un String.
	 */
	public String getNombreYApellido() {
		return this.nombreYApellido;
	}
	
	/**
	 * Indica si el chofer se encuentra disponible para ser asignado a un trabajo.
	 */
	public boolean estaDisponible() {
		return this.estaDisponible;
	}

	/**
	 * Cambia la disponibilidad del chofer a la dada por parámetro. Si es true, está disponible para un trabajo. Si es false, no se encuentra disponible.
	 * @param disponibilidad es el valor que va a tener el chofer, el cual indica si se encuentra disponible para un trabajo o no.
	 */
	public void cambiarEstaDisponiblePor(boolean disponibilidad) {
		this.estaDisponible = disponibilidad;
	}

	// #################################### MÉTODOS AUXILIARES ################################## \\
	
	@Override
	public boolean equals(Object object) {
		return (this == object) || (this.esChofer(object) && (this.esElMismoQue(object)));
	}
	
	private boolean esChofer(Object object) {
		return object instanceof Chofer;
	}
	
	private boolean esElMismoQue(Object object) {
		Chofer choferAComparar = (Chofer) object;
		return dni.equals(choferAComparar.getDni());
	}
	
	@Override
	public int hashCode() {
		return dni.hashCode();
	}
}