package ar.edu.unq.po2.chofer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.camion.Camion;

/**
* Definen los tests unitarios de la clase Chofer.
* @author Benjamin Maldonado.
*/

class ChoferTest {
	private Chofer jose;
	private Chofer carlos;

	@BeforeEach
	public void setUp() {
		jose   = new Chofer("Jose Fernandez", "38.091.105");
		carlos = new Chofer("Carlos Romero", "25.755.002");
	}

	@Test
	public void testFuncionamientoGetters() {
		assertEquals("Jose Fernandez", jose.getNombreYApellido());
		assertEquals("38.091.105", jose.getDni());
		
		assertEquals("Carlos Romero", carlos.getNombreYApellido());
		assertEquals("25.755.002", carlos.getDni());
	}
	
	@Test
	public void testFuncionamientoEquals() {
		Chofer joseF   = new Chofer("Jose Fernandez", "22.240.777"); // Tiene el mismo nombre y apellido que Jose, pero no el mismo DNI.
		Chofer carlosG = new Chofer("Carlos Gimenez", "25.755.002"); // Tiene el mismo DNI que Carlos, pero no el mismo nombre y apellido.
		
		// Equals con misma referencia de tipo Chofer.
		assertTrue(jose.equals(jose));
		assertEquals(jose.hashCode(), jose.hashCode());
		
		// Equals con una instancia de tipo Chofer pero con distinto DNI.
		assertFalse(jose.equals(joseF));
		assertNotEquals(jose.hashCode(), joseF.hashCode());

		// Equals con una instancia de tipo Chofer pero con mismo DNI.
		assertTrue(carlos.equals(carlosG));
		assertEquals(carlos.hashCode(), carlosG.hashCode());
	}
	
	@Test
	public void testFuncionamientoDisponibilidad() {
		// Verify
		assertTrue(jose.estaDisponible());
		
		// Exercise
		jose.cambiarEstaDisponiblePor(false);
		
		// Verify
		assertFalse(jose.estaDisponible());
	}
}