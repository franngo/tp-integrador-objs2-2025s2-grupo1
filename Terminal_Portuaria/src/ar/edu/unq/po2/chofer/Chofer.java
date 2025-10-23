package ar.edu.unq.po2.chofer;

import ar.edu.unq.po2.registrable.Registrable;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Chofer implements Registrable {
	private String nombreYApellido;
	private String dni;

	public Chofer(String nombreYApellido, String dni) {
		this.nombreYApellido = nombreYApellido;
		this.dni = dni;
	}

	@Override
	public void registrarse(TerminalPortuaria terminalPortuaria) {
		terminalPortuaria.registrarChofer(this);
	}
	
	public String getDni() {
		return this.dni;
	}
	
	public String getNombreYApellido() {
		return this.nombreYApellido;
	}
}