package ar.edu.unq.po2.generadorDeReportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.orden.Orden;

public class GeneradorDeReportes {
	
	private VisitorReporteAduana visitorA;
	private VisitorReporteBuque visitorB;
	
	public GeneradorDeReportes() {
		this.visitorA = new VisitorReporteAduana();
		this.visitorB = new VisitorReporteBuque();
	}
		
	protected Map<String,Reporte> generarReportesConImportaciones(Buque buque, List<Orden> ordenes) {
		
		String nombreBuque = buque.getNombre();
		String fechaPartida = buque.getViajeActual().fechaDeSalida().toString();
		String fechaArribo = buque.getViajeActual().fechaDeLlegada().toString();
		
		///////////////////////Reporte muelle/////////////////////////
		
		String muelle = "Nombre del buque: " + nombreBuque +"\n"
					+"Fecha de partida: " + fechaPartida + "\n"
					+"Fecha de arribo: " + fechaArribo + "\n"
					+"Contenedores descargados: " + ordenes.size() + "\n";
		Reporte r1 = new Reporte(muelle);
		
		///////////////////////Reporte muelle/////////////////////////
		
		///////////////////////Reporte aduana/////////////////////////
		
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
		
		Reporte r2 = new Reporte(aduana);
		
		///////////////////////Reporte aduana/////////////////////////
			
		///////////////////////Reporte buque/////////////////////////
		
		String repBuque = "<report>\n"
					+	  "		<import>\n";
		
		for(Orden orden : ordenes) {
			repBuque = repBuque + orden.accept(visitorB);
		}
		
		repBuque = repBuque +	"		</import>\n";
		
		Reporte r3 = new Reporte(repBuque);
		
		///////////////////////Reporte buque/////////////////////////
		
		Map<String,Reporte> rs = new HashMap<String,Reporte>();
		rs.put("muelle", r1);
		rs.put("aduana", r2);
		rs.put("buque", r3);
		
		return rs;
				
	}
	
	protected List<Reporte> finalizarReportesConExportaciones(Map<String,Reporte> reportes, List<Orden> ordenes) {
		
		///////////////////////Reporte muelle/////////////////////////
		///
		String muelle = "Contenedores cargados: " + ordenes.size();
		
		Reporte r1 = reportes.get("muelle"); //el reporte muelle con únicamente la información de importaciones
		r1.setTexto(r1.getTexto() + muelle);
		
		///////////////////////Reporte muelle/////////////////////////
		
		///////////////////////Reporte aduana/////////////////////////
		
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
		
		/* esto se hace recién en el segundo método!!!
		aduana = aduana
				+ "    		</ul>"
				+ "    </body>"	
				+ "</html>";	
		*/
		Reporte r2 = reportes.get("muelle"); //el reporte muelle con únicamente la información de importaciones
		r1.setTexto(r1.getTexto() + muelle);
		
		///////////////////////Reporte aduana/////////////////////////
			
		///////////////////////Reporte buque/////////////////////////
		
		String repBuque = "<report>\n"
					+	  "		<import>\n";
		
		for(Orden orden : ordenes) {
			repBuque = repBuque + orden.accept(visitorB);
		}
		
		repBuque = repBuque +	"		</import>\n";
		
		Reporte r3 = reportes.get("muelle"); //el reporte muelle con únicamente la información de importaciones
		r1.setTexto(r1.getTexto() + muelle);
		
		///////////////////////Reporte buque/////////////////////////
		
	}

}
