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
import ar.edu.unq.po2.container.Dry;

public class DryCompuestoTestCase {
	DryCompuesto miDryCompuesto; 
	@Mock
	Dry dumbDryUnico;
	Dry dumbDryUnico2;
	Dry dumbDryUnico3;
	Dry dumbDryUnico4;
	
	List<IDry> dumbDrys;
	ConcreteVisitorContainer mockVisitor; 
	
	@BeforeEach
	void setUp() throws Exception {
		
		dumbDryUnico= mock(Dry.class);
		dumbDryUnico2 = mock(Dry.class);
		dumbDryUnico3 = mock(Dry.class);
		dumbDryUnico4 = mock(Dry.class);
		
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
