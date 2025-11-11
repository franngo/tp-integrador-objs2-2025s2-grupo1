package ar.edu.unq.po2.generadorDeReportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.orden.OrdenDeExportacion;
import ar.edu.unq.po2.orden.OrdenDeImportacion;

public class GeneradorDeReportesTest {
	
	//SUT
	GeneradorDeReportes generador;
	
	//DOCs
	OrdenDeImportacion ordenImp1;
	OrdenDeImportacion ordenImp2;
	
	OrdenDeExportacion ordenExp1;
	OrdenDeExportacion ordenExp2;
	
	Buque buque;
	
	Container container1;
	Container container2;
	Container container3;
	Container container4;
	
	//auxiliares
	
	List<Orden> imps;
	List<Orden> exps;
	
	@Test
	public void generarReportesConImportacionesYfinalizarReportesConExportaciones() {
		
		generador = new GeneradorDeReportes();
		
		imps = new ArrayList<Orden>();
		exps = new ArrayList<Orden>();
		
		buque = mock(Buque.class);
		
		container1 = mock(Container.class);
		when(container1.tipoCarga()).thenReturn("Tanque");
		when(container1.getIdConnteiner()).thenReturn("MARC9378524");
		
		container2 = mock(Container.class);
		when(container2.tipoCarga()).thenReturn("Tanque");
		when(container2.getIdConnteiner()).thenReturn("MARC9378524");
		
		container3 = mock(Container.class);
		when(container3.tipoCarga()).thenReturn("Tanque");
		when(container3.getIdConnteiner()).thenReturn("MARC9378524");
		
		container4 = mock(Container.class);
		when(container4.tipoCarga()).thenReturn("Tanque");
		when(container4.getIdConnteiner()).thenReturn("MARC9378524");
		
		///////
		
		ordenImp1 = mock(OrdenDeImportacion.class);
		when(ordenImp1.getCarga()).thenReturn(container);
		
		ordenImp2 = mock(OrdenDeImportacion.class);
		when(ordenImp2.getCarga()).thenReturn(container);
		
		///////
		
		ordenExp1 = mock(OrdenDeExportacion.class);
		when(ordenExp1.getCarga()).thenReturn(container);
		
		ordenExp2 = mock(OrdenDeExportacion.class);
		when(ordenExp2.getCarga()).thenReturn(container);
		
		
		//assertEquals("			<item>MARC9378524</item>", visitor.visitOrdenDeImportacion(ordenImp));

	}

}
