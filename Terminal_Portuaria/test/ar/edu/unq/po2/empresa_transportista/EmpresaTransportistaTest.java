package ar.edu.unq.po2.empresa_transportista;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/**
* Definen los tests unitarios de la clase EmpresaTransportista.
* @author Benjamin Maldonado.
*/

class EmpresaTransportistaTest {
	private EmpresaTransportista andreani;
	private Chofer jose;
	private Chofer carlos;
	private Camion scaniaR580;
	private Camion volvoFH460;

	@BeforeEach
	public void setUp() {
		andreani = new EmpresaTransportista();
		
		jose = new Chofer("Jose Fernandez", "38.091.105");
		carlos = new Chofer("Carlos Romero", "25.755.002");
		
		scaniaR580 = new Camion("Scania R580", "AE471WD");
		volvoFH460 = new Camion("Volvo FH460", "NRG113");
		
		andreani.añadirCamion(scaniaR580);
		andreani.añadirCamion(volvoFH460);
		andreani.añadirChofer(jose);
		andreani.añadirChofer(carlos);
	}

	@Test
	public void testFuncionamientoGetters() {
		assertEquals("Jose Fernandez", jose.getNombreYApellido());
		assertEquals("38.091.105", jose.getDni());
		
		assertEquals("Carlos Romero", carlos.getNombreYApellido());
		assertEquals("25.755.002", carlos.getDni());
		
		assertEquals("Scania R580", scaniaR580.getMarcaYModelo());
		assertEquals("AE471WD", scaniaR580.getPatente());
		
		assertEquals("Volvo FH460", volvoFH460.getMarcaYModelo());
		assertEquals("NRG113", volvoFH460.getPatente());
		
		assertTrue(andreani.tieneCamion(scaniaR580));
		assertTrue(andreani.tieneCamion(volvoFH460));
		assertEquals(2, andreani.getCamiones().size());
		
		assertTrue(andreani.tieneChofer(jose));
		assertTrue(andreani.tieneChofer(carlos));
		assertEquals(2, andreani.getChoferes().size());
	}
	
	@Test
	public void testFuncionamientoDeAñadirALaEmpresaTransportista() {
		assertEquals(2, andreani.getCamiones().size());
		assertEquals(2, andreani.getChoferes().size());
		
		andreani.añadirCamion(scaniaR580);
		andreani.añadirChofer(jose);
		
		assertEquals(2, andreani.getCamiones().size());
		assertEquals(2, andreani.getChoferes().size());
		
		Camion scaniaP310 = new Camion("Scania P310", "HGP440");
		Chofer ricardo = new Chofer("Ricardo Iorio", "27.908.100");
		
		andreani.añadirCamion(scaniaP310);
		andreani.añadirChofer(ricardo);
		
		assertEquals(3, andreani.getCamiones().size());
		assertEquals(3, andreani.getChoferes().size());
	}
	
	@Test
	public void testRegistrarseEnTerminalPortuaria() {
		// Setup
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        andreani.registrarse(terminal);

        // Exercise & Verify
        verify(terminal).registrarEmpresaTransportista(andreani);
	}
}