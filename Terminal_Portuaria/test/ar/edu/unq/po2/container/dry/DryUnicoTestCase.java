package ar.edu.unq.po2.container.dry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.cliente.Consignee;
import ar.edu.unq.po2.container.ConcreteVisitorContainer;


public class DryUnicoTestCase {
	DryUnico miDryUnico; 
	@Mock
	Consignee dumbCliente;
	ConcreteVisitorContainer mockVisitor; 
	@BeforeEach
	void setUp() throws Exception {
		dumbCliente = mock(Consignee.class);
		when(dumbCliente.nombreCliente()).thenReturn("Matias");
	
		miDryUnico= new DryUnico(dumbCliente,10d,20d,30d,400d);
		mockVisitor = mock(ConcreteVisitorContainer.class);
	}
    
	/*
	 * La altura, el ancho, el largo, el peso del reefer son coherente 
	 * */
	@Test
	public void variablesDeInstanciaCoherentes() {
	
	assertEquals(10d,miDryUnico.getAncho());
	assertEquals(20d,miDryUnico.getLargo());
	assertEquals(30d,miDryUnico.getAltura());
	assertEquals(400d,miDryUnico.getPeso());
	
	}
	
	@Test
	public void comunicacionConVisitante() {
		miDryUnico.acceptVisitor(mockVisitor);
		verify(mockVisitor,times(1)).serviciosDry(miDryUnico);
		
	}
	
	@Test 
	public void testNombreNombreCrga() {
		assertEquals("Dry individual",miDryUnico.tipoCarga());
	}
}
