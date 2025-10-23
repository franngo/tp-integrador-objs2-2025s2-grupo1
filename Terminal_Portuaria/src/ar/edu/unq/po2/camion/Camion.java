package ar.edu.unq.po2.camion;

import ar.edu.unq.po2.registrable.Registrable;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Camion implements Registrable {
	private String marcaYModelo;
	private String patente;
	
	public Camion(String marcaYModelo, String patente) {
		this.marcaYModelo = marcaYModelo;
		this.patente = patente;
	}

	@Override
	public void registrarse(TerminalPortuaria terminalPortuaria) {
		terminalPortuaria.registrarCamion(this);
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public String getMarcaYModelo() {
		return this.marcaYModelo;
	}
	
	public void ingresarA(TerminalPortuaria terminalPortuaria) {
		// TODO: Ingresar el camión a la terminal portuaria dada.
	}
	
	public void retirarseDe(TerminalPortuaria terminalPortuaria) {
		// TODO: Retirar el camión de la terminal dada.
	}
}