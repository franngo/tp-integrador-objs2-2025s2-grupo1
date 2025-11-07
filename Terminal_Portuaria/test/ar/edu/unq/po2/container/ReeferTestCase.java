package ar.edu.unq.po2.container;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import ar.edu.unq.po2.cliente.Consignee;



/*
 * @Autor: Matias Sanchez 
 * */
class ReeferTestCase {
    
	Reefer miRefeer; 
	@Mock
	Consignee dumbCliente;
	ConcreteVisitorContainer mockVisitor; 
	@BeforeEach
	void setUp()  {
		dumbCliente = mock(Consignee.class);
		when(dumbCliente.nombreCliente()).thenReturn("Matias");
	
		miRefeer= new Reefer(dumbCliente,10d,20d,30d,400d, 10);
		mockVisitor = mock(ConcreteVisitorContainer.class);
	}
    
	/*
	 * La altura, el ancho, el largo, el peso del reefer son coherente 
	 * */
	@Test
	public void variablesDeInstanciaCoherentes() {
	
	assertEquals(10d,miRefeer.getAncho());
	assertEquals(20d,miRefeer.getLargo());
	assertEquals(30d,miRefeer.getAltura());
	assertEquals(400d,miRefeer.getPeso());
	assertEquals(10,miRefeer.getConsumoPorHora());
	}
	
	@Test
	public void comunicacionConVisitante() {
	    miRefeer.acceptVisitor(mockVisitor);
		verify(mockVisitor,times(1)).serviciosReefer(miRefeer);
		
	}
	
	@Test 
	public void testNombreNombreCrga() {
		assertEquals("Refeer",miRefeer.tipoCarga());
	}

}
