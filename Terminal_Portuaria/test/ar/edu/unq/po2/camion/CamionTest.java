package ar.edu.unq.po2.camion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/**
* Definen los tests unitarios de la clase Camion.
* @author Benjamin Maldonado.
*/

class CamionTest {
	private Camion scaniaR580;
	private Camion volvoFH460;
	
	@BeforeEach
	public void setUp() {
		scaniaR580 = new Camion("Scania R580", "AE471WD");
		volvoFH460 = new Camion("Volvo FH460", "NRG113");
	}

	@Test
	public void testFuncionamientoGetters() {
		assertEquals("Scania R580", scaniaR580.getMarcaYModelo());
		assertEquals("AE471WD", scaniaR580.getPatente());
		
		assertEquals("Volvo FH460", volvoFH460.getMarcaYModelo());
		assertEquals("NRG113", volvoFH460.getPatente());
	}
	
	@Test
	public void testFuncionamientoEquals() {
		Camion scaniaR580D = new Camion("Scania R580", "AA975BA"); // Tiene la misma marca y modelo que scaniaR580, pero la misma patente.
		Camion volvoFH500  = new Camion("Volvo FH500", "NRG113");  // Tiene la misma patente que volvoFH460, pero no el mismo modelo y marca.
		Chofer jose = new Chofer("Jose Fernandez", "38.091.105");  // Chofer utilizado de referencia.
		
		// Equals con misma referencia de tipo Camion.
		assertTrue(scaniaR580.equals(scaniaR580));
		assertEquals(scaniaR580.hashCode(), scaniaR580.hashCode());
		
		// Equals con una instancia que no es del tipo Camion.
		assertFalse(scaniaR580.equals(jose));
		assertNotEquals(scaniaR580.hashCode(), jose.hashCode());
		
		// Equals con una instancia de tipo Camion pero con distinta patente.
		assertFalse(scaniaR580.equals(scaniaR580D));
		assertNotEquals(scaniaR580.hashCode(), scaniaR580D.hashCode());
		
		// Equals con una instancia de tipo Camion pero con misma patente.
		assertTrue(volvoFH460.equals(volvoFH500));
		assertEquals(volvoFH460.hashCode(), volvoFH500.hashCode());
	}
	
	@Test
	public void testFuncionamientoOrdenActual() {
		// Setup
		Orden orden = mock(Orden.class);
		
		// Exercise & Verify
		assertEquals(null, scaniaR580.getOrdenActual());
		
		scaniaR580.cambiarOrdenActualPor(orden);
		
		assertEquals(orden, scaniaR580.getOrdenActual());
	}

	@Test
	public void testFuncionamientoDisponibilidad() {
		// Setup
		assertTrue(scaniaR580.estaDisponible());
		Orden orden = mock(Orden.class);
		
		// Exercise
		scaniaR580.cambiarOrdenActualPor(orden);
		
		// Verify
		assertFalse(scaniaR580.estaDisponible());
	}

	@Test
	public void testIngresarALaTerminalPortuaria() {
		// Setup
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        Orden ordenS = mock(Orden.class);
        Orden ordenV = mock(Orden.class);
        Chofer chofer = mock(Chofer.class);
        Cliente consignee = mock(Cliente.class);
        
        // Exercise
        scaniaR580.cambiarOrdenActualPor(ordenS);
        volvoFH460.cambiarOrdenActualPor(ordenV);
        
        scaniaR580.transportarExportacionA(terminal, chofer);
        volvoFH460.transportarExportacionA(terminal, chofer);

        // Verify
        verify(terminal).registrarExportacion(scaniaR580.getOrdenActual(), scaniaR580, chofer, consignee);
        verify(terminal).registrarExportacion(volvoFH460.getOrdenActual(), volvoFH460, chofer, consignee);
	}
	
	@Test
	public void testRetirarseDeLaTerminalPortuaria() {
		// Setup
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        Chofer chofer = mock(Chofer.class);
        Cliente consignee = mock(Cliente.class);

        // Exercise
        scaniaR580.retirarImportacionDe(terminal, chofer, consignee);
        volvoFH460.retirarImportacionDe(terminal, chofer, consignee);
        
        // Verify
        verify(terminal).retirarImportacion(scaniaR580, chofer, consignee);
        verify(terminal).retirarImportacion(volvoFH460, chofer, consignee);
	}
}