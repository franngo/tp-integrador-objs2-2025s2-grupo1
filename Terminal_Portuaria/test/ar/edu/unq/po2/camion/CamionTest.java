package ar.edu.unq.po2.camion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CamionTest {
	private Camion scaniaR580;
	
	@BeforeEach
	public void setUp() {
		scaniaR580 = new Camion("Scania R580", "AE471WD");
	}

	@Test
	public void testFuncionamientoGetters() {
		assertEquals("Scania R580", scaniaR580.getMarcaYModelo());
		assertEquals("AE471WD", scaniaR580.getPatente());
	}
	
	@Test
	public void testRegistrarseEnTerminalPortuaria() {
		
	}
}