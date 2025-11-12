package ar.edu.unq.po2.container;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.bls.CargaBL;
import ar.edu.unq.po2.container.bls.CargaBLCompuesto;

class DryTestCase {
   
	
	Dry containerDry;
	CargaBL cargaBL;
	
	ConcreteVisitorContainer visitanteContainer;
	
	@BeforeEach
	void setUp()  {
		visitanteContainer = mock(ConcreteVisitorContainer.class);
		
		cargaBL= mock(CargaBLCompuesto.class);
		when(cargaBL.tipoCargaBL()).thenReturn("Carga compuesta por 10 Bl's");
		when(cargaBL.dueños()).thenReturn("Matias,Benja,Franco,Alonso");
		when(cargaBL.getPeso()).thenReturn(2000d);
		
		containerDry = new Dry(cargaBL);
	}

	@Test
	void tipoCargaTest() {
		assertEquals("Carga compuesta por 10 Bl's",containerDry.tipoCarga());
	}
	@Test
	void testDueniosCarga() {
		assertEquals("Matias,Benja,Franco,Alonso",containerDry.getDueños());
	}
	@Test
	void testPesoContainerDry() {
		assertEquals(2000d,containerDry.getPeso());
		
	}
	@Test
	void containerAceptaVisitante() {
		when(visitanteContainer.serviciosDry(containerDry)).thenReturn(List.of());
		containerDry.acceptVisitor(visitanteContainer);
		verify(visitanteContainer,times(1)).serviciosDry(containerDry);
		
		
	}
	@Test
	void laCargaEsCoherente() {
		assertEquals(cargaBL,containerDry.carga());
	}
	

}
