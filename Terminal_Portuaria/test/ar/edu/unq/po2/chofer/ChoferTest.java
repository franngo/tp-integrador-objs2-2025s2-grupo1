package ar.edu.unq.po2.chofer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

class ChoferTest {
	private Chofer jose;

	@BeforeEach
	public void setUp() {
		jose = new Chofer("Jose Fernandez", "38.091.105");
	}

	@Test
	public void testFuncionamientoGetters() {
		assertEquals("Jose Fernandez", jose.getNombreYApellido());
		assertEquals("38.091.105", jose.getDni());
	}
	
	@Test
	public void testRegistrarseEnTerminalPortuaria() {
        TerminalPortuaria terminal = mock(TerminalPortuaria.class);
        jose.registrarse(terminal);
        verify(terminal).registrarChofer(jose);
	}
}