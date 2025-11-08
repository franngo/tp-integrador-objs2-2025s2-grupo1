package ar.edu.unq.po2.generadorDeReportes;

import java.util.List;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.orden.Orden;

public class GeneradorDeReportes {
	
	private VisitorReporteMuelle visitorM;
	private VisitorReporteAduana visitorA;
	private VisitorReporteBuque visitorB;
		
	protected List<Reporte> generarReportesConImportaciones(Buque buque, List<Orden> ordenes) {
		
		String nombreBuque = buque.getNombre();
		String fechaPartida = buque.getViajeActual().fechaDeSalida().toString();
		String fechaArribo = buque.getViajeActual().fechaDeLlegada().toString();
		
		String muelle = "Nombre del buque: " + nombreBuque +"\n"
					+"Fecha de partida: " + fechaPartida + "\n"
					+"Fecha de arribo: " + fechaArribo + "\n";
		
		String aduana = "<html lang=\"en\">\n"
				+ "    <head>\n"
				+ "        <meta charset=\"utf-8\">\n"
				+ "        <title>Reporte aduana</title>\n"
				+ "    </head>\n"
				+ "    <body>"
				+ "	   		<h2>Nombre del buque: " + nombreBuque +"</h2>\n"
				+ "    		<h1>Fecha de partida: " + fechaPartida + "</h1>\n"
				+ "    		<h1>Fecha de arribo: " + fechaArribo + "</h1>\n"
				+ "    		<ul>";
		
		//<li><p> ... </p></li>
		
		String repBuque = "<report>";
				
	}
	
	protected List<Reporte> finalizarReportesConExportaciones(List<Reporte> reportes, List<Orden> ordenes) {
		
	}

}
