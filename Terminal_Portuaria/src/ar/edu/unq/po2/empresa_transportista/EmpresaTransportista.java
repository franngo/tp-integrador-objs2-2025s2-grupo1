package ar.edu.unq.po2.empresa_transportista;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.registrable.Registrable;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class EmpresaTransportista implements Registrable {
	private Set<Chofer> choferes;
	private Set<Camion> camiones;
	
	public EmpresaTransportista() {
		this.choferes = new HashSet<Chofer>();
		this.camiones = new HashSet<Camion>();
	}

	@Override
	public void registrarse(TerminalPortuaria terminalPortuaria) {
		terminalPortuaria.registrarEmpresaTransportista(this);
	}
	
	public Set<Chofer> getChoferes(){
	    return new HashSet<Chofer>(choferes);
	}
	
	public Set<Camion> getCamiones(){
	    return new HashSet<Camion>(camiones);
	}
	
	public void añadirCamion(Camion camion) {
		camiones.add(camion);
	}
	
	public void añadirChofer(Chofer chofer) {
		choferes.add(chofer);
	}
}