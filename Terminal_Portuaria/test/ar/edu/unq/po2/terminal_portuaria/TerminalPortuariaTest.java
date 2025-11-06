package ar.edu.unq.po2.terminal_portuaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Container;

/**
* Definen los tests unitarios de la clase TerminalPortuaria.
* @author Benjamin Maldonado.
*/

class TerminalPortuariaTest {
	private TerminalPortuaria terminalGestionada;

	@BeforeEach
	public void setUp() {
		
	}
	
	@Test
	public void testFuncionamientoCircuitoDeExportacion() {
		Viaje viaje = terminalGestionada.buscarViaje();
		Camion camion = andreani.contratarCamion();
		Chofer chofer = andreani.contratarChofer();
		Container container = 
		
		OrdenDeExportacion orden = terminalGestionada.crearOrdenDeExportacion(viaje, container, camion, chofer);
		
	}
	
	@Test
	public void testFuncionamientoCircuitoDeImportacion() {
		
	}
}