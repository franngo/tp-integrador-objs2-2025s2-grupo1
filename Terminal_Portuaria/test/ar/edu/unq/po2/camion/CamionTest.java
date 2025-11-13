package ar.edu.unq.po2.camion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.cliente.*;
import ar.edu.unq.po2.container.*;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

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
		
		// Equals con misma referencia de tipo Camion.
		assertTrue(scaniaR580.equals(scaniaR580));
		assertEquals(scaniaR580.hashCode(), scaniaR580.hashCode());
		
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
	public void testTransportarExportacionExitoso() {
		// Setup
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        EmpresaTransportista andreani = new EmpresaTransportista();
        
        CircuitoMaritimo circuito = mock(CircuitoMaritimo.class);
        Buque buque = mock(Buque.class);
        Viaje viaje = new Viaje(LocalDateTime.now().plusHours(2), circuito, buque);

        Cliente consignee = new Cliente("Roberto Paniagua");
        Cliente shipper = new Cliente("Benjamin Maldonado");
        Chofer chofer = new Chofer("Jose Fernandez", "38.091.105");
        Container container = new Reefer(consignee, 1100, 4500, 3050, 25000, 15);
        
        Orden orden = new Orden(scaniaR580, chofer, container, viaje, shipper);
        
        andreani.añadirCamion(scaniaR580);
        andreani.añadirChofer(chofer);

        terminal.registrarCliente(consignee);
        terminal.registrarEmpresaTransportista(andreani);
        
        // Exercise
        scaniaR580.cambiarOrdenActualPor(orden);
        scaniaR580.transportarExportacionA(terminal, chofer);
        
        // Verify
        verify(terminal).registrarExportacion(scaniaR580.getOrdenActual(), scaniaR580, chofer);
	}
	
	@Test
	public void testTransportarExportacionFallido() {
		// Setup
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        EmpresaTransportista andreani = new EmpresaTransportista();
        
        Cliente consignee = new Cliente("Roberto Paniagua");
        Chofer chofer = new Chofer("Jose Fernandez", "38.091.105");
        
        andreani.añadirCamion(scaniaR580);
        andreani.añadirChofer(chofer);

        terminal.registrarCliente(consignee);
        terminal.registrarEmpresaTransportista(andreani);
        
        // Exercise & Verify
        assertThrows(RuntimeException.class, () -> scaniaR580.transportarExportacionA(terminal, chofer));
	}
	
	@Test
	public void testRetirarseImportacionExitoso() {
		// Setup
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        Chofer chofer = new Chofer("Jose Fernandez", "38.091.105");
        Cliente consignee = new Cliente("Roberto Paniagua");

        // Exercise
        volvoFH460.retirarImportacionDe(terminal, chofer, consignee);
        
        // Verify
        verify(terminal).retirarImportacion(volvoFH460, chofer, consignee);
	}
	
	@Test
	public void testRetirarseImportacionFallido() {
		// Setup
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        
        Cliente consignee = mock(Cliente.class);
        Cliente shipper = mock(Cliente.class);
        Chofer chofer = mock(Chofer.class);
        
        Container container = mock(Container.class);
        Viaje viaje = mock(Viaje.class);
        Orden orden = new Orden(volvoFH460, chofer, container, viaje, shipper);
        
        // Exercise
        volvoFH460.cambiarOrdenActualPor(orden);
        
        // Verify
        assertThrows(RuntimeException.class, () -> volvoFH460.retirarImportacionDe(terminal, chofer, consignee));
	}
}