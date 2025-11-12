package ar.edu.unq.po2.generadorDeReportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReporteTest {
	
	String s;
	Reporte r;
	
	@BeforeEach
	public void setUp() {
		
		s = "texto de prueba 1";
		r = new Reporte(s);
				
	}
	
	@Test
	public void getTexto() {
		
		assertEquals("texto de prueba 1", r.getTexto());
		
	}
	
	@Test
	public void setTexto() {
		
		r.setTexto("texto de prueba 2");
		
		assertNotEquals("texto de prueba 1", r.getTexto());
		assertEquals("texto de prueba 2", r.getTexto());
		
	}

}
