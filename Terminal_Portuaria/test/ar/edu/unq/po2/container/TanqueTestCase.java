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


class TanqueTestCase {
    
	
	Tanque miTanque; 
	@Mock
	Consignee dumbCliente;
	ConcreteVisitorContainer mockVisitor; 
	@BeforeEach
	void setUp() {
		dumbCliente = mock(Consignee.class);
		when(dumbCliente.nombreCliente()).thenReturn("Matias");
	
		miTanque= new Tanque(dumbCliente,10d,20d,30d,400d);
		mockVisitor = mock(ConcreteVisitorContainer.class);
	}
    
	/*
	 * La altura, el ancho, el largo, el peso del reefer son coherente 
	 * */
	@Test
	public void variablesDeInstanciaCoherentes() {
	
	assertEquals(10d,miTanque.getAncho());
	assertEquals(20d,miTanque.getLargo());
	assertEquals(30d,miTanque.getAltura());
	assertEquals(400d,miTanque.getPeso());
	}
	
	@Test
	public void comunicacionConVisitante() {
		 miTanque.acceptVisitor(mockVisitor);
		verify(mockVisitor,times(1)).serviciosTanque(miTanque);
		
	}
	
	@Test 
	public void testNombreNombreCrga() {
		assertEquals("Tanque",miTanque.tipoCarga());
	}
	

}
