package ar.edu.unq.po2.container.dry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import ar.edu.unq.po2.container.ConcreteVisitorContainer;

public class DryCompuestoTestCase {
	DryCompuesto miDryCompuesto; 
	@Mock
	DryUnico dumbDryUnico;
	DryUnico dumbDryUnico2;
	DryUnico dumbDryUnico3;
	DryUnico dumbDryUnico4;
	
	List<Dry> dumbDrys;
	ConcreteVisitorContainer mockVisitor; 
	
	@BeforeEach
	void setUp() throws Exception {
		
		dumbDryUnico= mock(DryUnico.class);
		dumbDryUnico2 = mock(DryUnico.class);
		dumbDryUnico3 = mock(DryUnico.class);
		dumbDryUnico4 = mock(DryUnico.class);
		
		when(dumbDryUnico.tipoCarga()).thenReturn("algo");
		when(dumbDryUnico2.tipoCarga()).thenReturn("algo2");
		when(dumbDryUnico3.tipoCarga()).thenReturn("algo3");
		when(dumbDryUnico4.tipoCarga()).thenReturn("algo4");
		
		
		dumbDrys = List.of(dumbDryUnico,dumbDryUnico2,dumbDryUnico3,dumbDryUnico4);
		
		miDryCompuesto= new DryCompuesto(dumbDrys);
		mockVisitor = mock(ConcreteVisitorContainer.class);
	}
    
	/*
	 * La altura, el ancho, el largo, el peso del reefer son coherente 
	 * */
	
	
	@Test
	public void comunicacionConVisitante() {
		miDryCompuesto.acceptVisitor(mockVisitor);
		verify(mockVisitor,times(1)).serviciosDryCompuesto(miDryCompuesto);
		
	}
	
	@Test 
	public void testNombreNombreCrga() {
		assertEquals("Dry compuesto por 4 containers Dry",miDryCompuesto.tipoCarga());
	}
	
	/*
	 * El container compuesto devuelve sus cargas
	 * */
	@Test 
	public void testCargas() {
		assertEquals(dumbDrys,miDryCompuesto.cargas());
	}
}
