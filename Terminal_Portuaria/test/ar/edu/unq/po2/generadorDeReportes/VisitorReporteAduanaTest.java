package ar.edu.unq.po2.generadorDeReportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.OrdenDeExportacion;
import ar.edu.unq.po2.orden.OrdenDeImportacion;

public class VisitorReporteAduanaTest {
	
	//SUT
	VisitorReporteAduana visitor;
	
	//DOCs
	OrdenDeImportacion ordenImp;
	OrdenDeExportacion ordenExp;
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
		
		ordenImp = mock(OrdenDeImportacion.class);
		when(ordenImp.getCarga()).thenReturn(container);
		
		assertEquals("    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n", 
				visitor.visitOrdenDeImportacion(ordenImp));

	}
	
	@Test
	public void visitOrdenDeExportacion() {
		
		ordenExp = mock(OrdenDeExportacion.class);
		when(ordenExp.getCarga()).thenReturn(container);
		
		assertEquals("    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n", 
				visitor.visitOrdenDeExportacion(ordenExp));

	}

}
