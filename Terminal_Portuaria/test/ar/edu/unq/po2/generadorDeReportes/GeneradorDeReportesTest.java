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
import ar.edu.unq.po2.viaje.Viaje;

public class GeneradorDeReportesTest {
	
	//SUT
	GeneradorDeReportes generador;
	
	//DOCs
	Orden ordenImp1;
	Orden ordenImp2;
	
	Orden ordenExp1;
	Orden ordenExp2;
	
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
		
		ordenImp1 = mock(Orden.class);
		when(ordenImp1.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n");
		when(ordenImp1.accept(visitorB)).thenReturn
		("			<item>MARC9378524</item>\n");
		
		ordenImp2 = mock(Orden.class);
		when(ordenImp2.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Refeer, ID: LUIS9378524</p></li>\n");
		when(ordenImp2.accept(visitorB)).thenReturn
		("			<item>LUIS9378524</item>\n");
		
		
		///////
		
		ordenExp1 = mock(Orden.class);
		when(ordenExp1.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Tanque, ID: JULI9378524</p></li>\n");
		when(ordenExp1.accept(visitorB)).thenReturn
		("			<item>JULI9378524</item>\n");
		
		ordenExp2 = mock(Orden.class);
		when(ordenExp2.accept(visitorA)).thenReturn
		("    			<li><p> Tipo: Refeer, ID: LAUR9378524</p></li>\n");
		when(ordenExp2.accept(visitorB)).thenReturn
		("			<item>LAUR9378524</item>\n");
		
		///////
		
		imps = new ArrayList<Orden>();
		imps.add(ordenImp1);
		imps.add(ordenImp2);
		
		exps = new ArrayList<Orden>();
		exps.add(ordenExp1);
		exps.add(ordenExp2);
		
		//assertEquals("			<item>MARC9378524</item>", visitor.visitOrdenDeImportacion(ordenImp));
		
		Map<String, Reporte> rs = generador.generarReportesConImportaciones(buque, imps);
		
		///////
		
		assertEquals(3, rs.size());
		
		Reporte mue1 = rs.get("muelle");
		
		assertEquals("Nombre del buque: " + "Buque argentino" +"\n"
				+"Fecha de partida: " + "2007-12-03T10:15:30" + "\n"
				+"Fecha de arribo: " + "2007-12-06T10:15:30" + "\n"
				+"Contenedores descargados: " + "2" + "\n", 
				mue1.getTexto());
		
		///////
		
		Reporte adu1 = rs.get("aduana");
		
		assertEquals("<html lang=\"en\">\n"
				+ "    <head>\n"
				+ "        <meta charset=\"utf-8\">\n"
				+ "        <title>Reporte aduana</title>\n"
				+ "    </head>\n"
				+ "    <body>"
				+ "	   		<h2>Nombre del buque: " + "Buque argentino" +"</h2>\n"
				+ "    		<h1>Fecha de partida: " + "2007-12-03T10:15:30" + "</h1>\n"
				+ "    		<h1>Fecha de arribo: " + "2007-12-06T10:15:30" + "</h1>\n"
				+ "	   		<h2>Lista de contenedores (con su tipo y ID): </h2>\n"
				+ "    		<ul>\n"
				+ "    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n"
				+ "    			<li><p> Tipo: Refeer, ID: LUIS9378524</p></li>\n", 
				adu1.getTexto());
		
		///////
		
		Reporte buq1 = rs.get("buque");
		
		assertEquals("<report>\n"
				+	  "		<import>\n"
				+ "			<item>MARC9378524</item>\n"
				+ "			<item>LUIS9378524</item>\n"
				+	"		</import>\n",
				buq1.getTexto());
		
		///////
		
		List<Reporte> rs2 = generador.finalizarReportesConExportaciones(rs, exps);

		Reporte mueDef = rs2.get(0);
		Reporte aduDef = rs2.get(1);
		Reporte buqDef = rs2.get(2);
		
		///////

		assertEquals(3, rs2.size());
		
		assertEquals("Nombre del buque: " + "Buque argentino" +"\n"
				+"Fecha de partida: " + "2007-12-03T10:15:30" + "\n"
				+"Fecha de arribo: " + "2007-12-06T10:15:30" + "\n"
				+"Contenedores descargados: " + "2" + "\n"
				+"Contenedores cargados: 2", 
				mueDef.getTexto());
		
	
		
		///////
		
		assertEquals("<html lang=\"en\">\n"
				+ "    <head>\n"
				+ "        <meta charset=\"utf-8\">\n"
				+ "        <title>Reporte aduana</title>\n"
				+ "    </head>\n"
				+ "    <body>"
				+ "	   		<h2>Nombre del buque: " + "Buque argentino" +"</h2>\n"
				+ "    		<h1>Fecha de partida: " + "2007-12-03T10:15:30" + "</h1>\n"
				+ "    		<h1>Fecha de arribo: " + "2007-12-06T10:15:30" + "</h1>\n"
				+ "	   		<h2>Lista de contenedores (con su tipo y ID): </h2>\n"
				+ "    		<ul>\n"
				+ "    			<li><p> Tipo: Tanque, ID: MARC9378524</p></li>\n"
				+ "    			<li><p> Tipo: Refeer, ID: LUIS9378524</p></li>\n"
				+ "    			<li><p> Tipo: Tanque, ID: JULI9378524</p></li>\n"
				+ "    			<li><p> Tipo: Refeer, ID: LAUR9378524</p></li>\n"
				+ "    		</ul>\n"
				+ "    </body>\n"	
				+ "</html>", 
				aduDef.getTexto());
		
		///////

		assertEquals("<report>\n"
				+	  "		<import>\n"
				+ "			<item>MARC9378524</item>\n"
				+ "			<item>LUIS9378524</item>\n"
				+	"		</import>\n"
				+ "		<export>\n"
				+ "			<item>JULI9378524</item>\n"
				+ "			<item>LAUR9378524</item>\n"
				+ "		</export>\n"
				+ "/report", 
				buqDef.getTexto());
		
	}

}
