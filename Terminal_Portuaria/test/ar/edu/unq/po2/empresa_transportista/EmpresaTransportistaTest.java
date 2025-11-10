package ar.edu.unq.po2.empresa_transportista;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.orden.Orden;

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
		
		assertTrue(andreani.getCamiones().contains(scaniaR580));
		assertTrue(andreani.getCamiones().contains(volvoFH460));
		assertEquals(2, andreani.getCamiones().size());
		
		assertTrue(andreani.getChoferes().contains(jose));
		assertTrue(andreani.getChoferes().contains(carlos));
		assertEquals(2, andreani.getChoferes().size());
	}
	
	@Test
	public void testFuncionamientoDeAñadirChoferYCamion() {
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
	public void testDisponibilidadDeCamiones() {
		// Setup
		Camion camionDisponible = andreani.contratarCamion();
		Orden orden = mock(Orden.class);		
		
		// Exercise & Verify
		assertTrue(andreani.tieneCamionDisponible());
		assertTrue(camionDisponible.estaDisponible());
		
		camionDisponible.cambiarOrdenActualPor(orden);
		assertTrue(andreani.tieneCamionDisponible());
		assertFalse(camionDisponible.estaDisponible());
		
		Camion camionDisponibleD = andreani.contratarCamion();
		assertTrue(camionDisponibleD.estaDisponible());
		
		camionDisponibleD.cambiarOrdenActualPor(orden);
		assertFalse(andreani.tieneCamionDisponible());
		assertFalse(camionDisponibleD.estaDisponible());
	}
	
	@Test
	public void testDisponibilidadDeChoferes() {
		// Exercise & Verify
		Chofer choferDisponible = andreani.contratarChofer();
		assertTrue(andreani.tieneChoferDisponible());
		assertTrue(choferDisponible.estaDisponible());
		
		choferDisponible.cambiarEstaDisponiblePor(false);
		assertTrue(andreani.tieneChoferDisponible());
		assertFalse(choferDisponible.estaDisponible());
		
		Chofer choferDisponibleD = andreani.contratarChofer();
		assertTrue(choferDisponibleD.estaDisponible());
		
		choferDisponibleD.cambiarEstaDisponiblePor(false);
		assertFalse(andreani.tieneChoferDisponible());
		assertFalse(choferDisponibleD.estaDisponible());
	}
	
	@Test
	public void testFuncionamientoValidaciones() {
		// Setup
		Orden orden = mock(Orden.class);
		
		Camion camion1 = andreani.contratarCamion();
		camion1.cambiarOrdenActualPor(orden);

		Camion camion2 = andreani.contratarCamion();
		camion2.cambiarOrdenActualPor(orden);

		Chofer chofer1 = andreani.contratarChofer();
		chofer1.cambiarEstaDisponiblePor(false);

		Chofer chofer2 = andreani.contratarChofer();
		chofer2.cambiarEstaDisponiblePor(false);
		
		// Exercise & Verify
		assertThrows(RuntimeException.class, () -> andreani.contratarCamion());
		assertThrows(RuntimeException.class, () -> andreani.contratarChofer());
	}
	
	@Test
	public void testFuncionamientoRegistrados() {
		// Setup
		Chofer ricardo = new Chofer("Ricardo Fort", "29.950.112");
		Camion manTGX560 = new Camion("Man TGX560", "AC093FF");
		
		// Exercise & Verify
		assertTrue(andreani.tieneCamionYChoferRegistrados(scaniaR580, jose));
		assertFalse(andreani.tieneCamionYChoferRegistrados(volvoFH460, ricardo));
		assertFalse(andreani.tieneCamionYChoferRegistrados(manTGX560, ricardo));
		assertFalse(andreani.tieneCamionYChoferRegistrados(manTGX560, jose));
	}
}