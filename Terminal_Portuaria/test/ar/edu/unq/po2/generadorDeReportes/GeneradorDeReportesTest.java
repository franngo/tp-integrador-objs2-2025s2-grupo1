package ar.edu.unq.po2.generadorDeReportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.orden.OrdenDeExportacion;
import ar.edu.unq.po2.orden.OrdenDeImportacion;
import ar.edu.unq.po2.viaje.Viaje;

public class GeneradorDeReportesTest {
	
	//SUT
	GeneradorDeReportes generador;
	
	//DOCs
	OrdenDeImportacion ordenImp1;
	OrdenDeImportacion ordenImp2;
	
	OrdenDeExportacion ordenExp1;
	OrdenDeExportacion ordenExp2;
	
	Buque buque;
	Viaje viaje;
	LocalDateTime t1;
	LocalDateTime t2;
	
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
		VisitorReporteAduana visitorA = generador.getVisitorAduana();
		VisitorReporteBuque visitorB = generador.getVisitorBuque();
		
		///////
		
		buque = mock(Buque.class);
		viaje = mock(Viaje.class);
		t1 = mock(LocalDateTime.class);
		t2 = mock(LocalDateTime.class);
		
		when(buque.getNombre()).thenReturn("Buque argentino");
		
		when(buque.getViajeActual()).thenReturn(viaje);
		when(viaje.fechaDeSalida()).thenReturn(t1);
		when(t1.toString()).thenReturn("2007-12-03T10:15:30");
		
		when(viaje.fechaDeLlegada()).thenReturn(t2);
		when(t2.toString()).thenReturn("2007-12-06T10:15:30");

		///////
		
		ordenImp1 = mock(OrdenDeImportacion.class);
		when(ordenImp1.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n");
		when(ordenImp1.accept(visitorB)).thenReturn
		("			<item>MARC9378524</item>");
		
		ordenImp2 = mock(OrdenDeImportacion.class);
		when(ordenImp2.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Refeer, ID: LUIS9378524</p></li>\n");
		when(ordenImp2.accept(visitorB)).thenReturn
		("			<item>LUIS9378524</item>");
		
		
		///////
		
		ordenExp1 = mock(OrdenDeExportacion.class);
		when(ordenExp1.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Tanque, ID: JULI9378524</p></li>\n");
		when(ordenExp1.accept(visitorB)).thenReturn
		("			<item>JULI9378524</item>");
		
		ordenExp2 = mock(OrdenDeExportacion.class);
		when(ordenExp2.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Refeer, ID: LAUR9378524</p></li>\n");
		when(ordenExp2.accept(visitorB)).thenReturn
		("			<item>LAUR9378524</item>");
		
		///////
		
		imps = new ArrayList<Orden>();
		imps.add(ordenImp1);
		imps.add(ordenImp2);
		
		exps = new ArrayList<Orden>();
		exps.add(ordenExp1);
		exps.add(ordenExp2);
		
		//assertEquals("			<item>MARC9378524</item>", visitor.visitOrdenDeImportacion(ordenImp));
		
		Map<String, Reporte> rs = generador.generarReportesConImportaciones(buque, imps);
		
		List<Reporte> rs2 = generador.finalizarReportesConExportaciones(rs, exps);

	}

}
