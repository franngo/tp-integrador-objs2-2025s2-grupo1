package ar.edu.unq.po2.terminal_portuaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;

/**
* Definen los tests unitarios de la clase TerminalPortuaria.
* @author Benjamin Maldonado.
*/

class TerminalPortuariaTest {
	private TerminalPortuaria terminalGestionada;

	@BeforeEach
	public void setUp() {
		Camion scaniaR580 = new Camion("Scania R580", "AE471WD");
		Chofer jose = new Chofer("Jose Fernandez", "38.091.105");
		EmpresaTransportista andreani = new EmpresaTransportista();
		
		andreani.añadirChofer(jose);
		andreani.añadirCamion(scaniaR580);
	}
	
	@Test
	public void testFuncionamientoCircuitoDeExportacion() {
		Viaje viajeElegido = terminalGestionada.buscarViaje();
		Camion camionContratado = andreani.contratarCamion();
		Chofer choferContratado = andreani.contratarChofer();
		
		OrdenDeExportacion orden = terminalGestionada.crearOrdenDeExportacion(viajeElegido, container, camionContratado, choferContratado);
		
	}
	
	@Test
	public void testFuncionamientoCircuitoDeImportacion() {
		
	}
}