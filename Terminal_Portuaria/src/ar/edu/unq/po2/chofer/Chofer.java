package ar.edu.unq.po2.chofer;

/**
* Describe a un chofer con nombre y apellido, además de su DNI.
* @author Benjamin Maldonado.
*/

public class Chofer {
	private String nombreYApellido;
	private String dni;

	/**
	 * @param nombreYApellido es el nombre y el apellido del chofer.
	 * @param dni es el número del Documento Nacional de Identidad del chofer.
	 */
	public Chofer(String nombreYApellido, String dni) {
		this.nombreYApellido = nombreYApellido;
		this.dni = dni;
	}
	
	/**
	 * Describe el número del DNI del chofer.
	 */
	public String getDni() {
		return this.dni;
	}
	
	/**
	 * Describe el nombre y el apellido del chofer.
	 */
	public String getNombreYApellido() {
		return this.nombreYApellido;
	}
	
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