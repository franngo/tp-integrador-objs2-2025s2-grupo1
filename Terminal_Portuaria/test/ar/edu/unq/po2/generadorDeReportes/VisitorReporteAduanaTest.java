package ar.edu.unq.po2.generadorDeReportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.Orden;

public class VisitorReporteAduanaTest {
	//SUT
	VisitorReporteAduana visitor;
	
	//DOCs
	Orden ordenImp;
	Orden ordenExp;
	Container container;
	
	@BeforeEach
	public void setUp() {
		visitor = new VisitorReporteAduana();
		
		container = mock(Container.class);
		when(container.tipoCarga()).thenReturn("Tanque");
		when(container.getIdConnteiner()).thenReturn("MARC9378524");
	}
	
	@Test
	public void visitOrdenDeImportacion() {
		
		ordenImp = mock(Orden.class);
		when(ordenImp.getCarga()).thenReturn(container);
		
		assertEquals("    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n", 
				visitor.visitOrden(ordenImp));

	}
	
	@Test
	public void visitOrdenDeExportacion() {
		
		ordenExp = mock(Orden.class);
		when(ordenExp.getCarga()).thenReturn(container);
		
		assertEquals("    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n", 
				visitor.visitOrden(ordenExp));

	}
}