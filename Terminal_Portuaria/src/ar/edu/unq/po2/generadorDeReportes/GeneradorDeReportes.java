package ar.edu.unq.po2.generadorDeReportes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.orden.Orden;

public class GeneradorDeReportes {
	
	private VisitorReporteAduana visitorA;
	private VisitorReporteBuque visitorB;
	
	public GeneradorDeReportes() {
		this.visitorA = new VisitorReporteAduana();
		this.visitorB = new VisitorReporteBuque();
	}
		
	protected List<Reporte> generarReportesConImportaciones(Buque buque, List<Orden> ordenes) {
		
		String nombreBuque = buque.getNombre();
		String fechaPartida = buque.getViajeActual().fechaDeSalida().toString();
		String fechaArribo = buque.getViajeActual().fechaDeLlegada().toString();
		
		String muelle = "Nombre del buque: " + nombreBuque +"\n"
					+"Fecha de partida: " + fechaPartida + "\n"
					+"Fecha de arribo: " + fechaArribo + "\n"
					+"Contenedores descargados: " + ordenes.size() + "\n";
		Reporte r1 = new Reporte(muelle);
		
		String aduana = "<html lang=\"en\">\n"
				+ "    <head>\n"
				+ "        <meta charset=\"utf-8\">\n"
				+ "        <title>Reporte aduana</title>\n"
				+ "    </head>\n"
				+ "    <body>"
				+ "	   		<h2>Nombre del buque: " + nombreBuque +"</h2>\n"
				+ "    		<h1>Fecha de partida: " + fechaPartida + "</h1>\n"
				+ "    		<h1>Fecha de arribo: " + fechaArribo + "</h1>\n"
				+ "	   		<h2>Lista de contenedores (con su tipo y ID): </h2>\n"
				+ "    		<ul>";
		
		for(Orden orden : ordenes) {
			aduana = aduana + orden.accept(visitorA);
		}
		
		aduana = aduana
				+ "    		</ul>"
				+ "    </body>"	
				+ "</html>";	
		Reporte r2 = new Reporte(aduana);
			
		
		//<li><p> ... </p></li>
		
		String repBuque = "<report>";
		
		List<Reporte> rs = new ArrayList<Reporte>();
		rs.add(r1);
		rs.add(r2);
		//rs.add(r3);
		
		return rs;
				
	}
	
	protected List<Reporte> finalizarReportesConExportaciones(List<Reporte> reportes, List<Orden> ordenes) {
		
	}

}
