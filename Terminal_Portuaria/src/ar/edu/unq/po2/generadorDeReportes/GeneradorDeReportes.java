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
	
	public VisitorReporteAduana getVisitorAduana() {
		return this.visitorA;
	}
	
	public VisitorReporteBuque getVisitorBuque() {
		return this.visitorB;
	}
		
	public Map<String, Reporte> generarReportesConImportaciones(Buque buque, List<Orden> ordenes) {
		
		String nombreBuque = buque.getNombre();
		String fechaPartida = buque.getViajeActual().fechaDeSalida().toString();
		String fechaArribo = buque.getViajeActual().fechaDeLlegada().toString();
		
		///////////////////////Reporte muelle/////////////////////////
		
		String muelle = this.reporteMuelleImportaciones(nombreBuque, fechaPartida, fechaArribo, ordenes);
		Reporte r1 = new Reporte(muelle);
		
		///////////////////////Reporte muelle/////////////////////////
		
		///////////////////////Reporte aduana/////////////////////////
		
		String aduana = this.reporteAduanaImportaciones(nombreBuque, fechaPartida, fechaArribo, ordenes);
		Reporte r2 = new Reporte(aduana);
		
		///////////////////////Reporte aduana/////////////////////////
			
		///////////////////////Reporte buque/////////////////////////
		
		String repBuque = this.reporteBuqueImportaciones(ordenes);
		Reporte r3 = new Reporte(repBuque);
		
		///////////////////////Reporte buque/////////////////////////
		
		Map<String,Reporte> rs = new HashMap<String,Reporte>();
		rs.put("muelle", r1);
		rs.put("aduana", r2);
		rs.put("buque", r3);
		
		return rs;
	}
	
	private String reporteMuelleImportaciones(String nombreBuque, String fechaPartida, String fechaArribo,
			List<Orden> ordenes) {
		
		String muelle = "Nombre del buque: " + nombreBuque +"\n"
				+"Fecha de partida: " + fechaPartida + "\n"
				+"Fecha de arribo: " + fechaArribo + "\n"
				+"Contenedores descargados: " + ordenes.size() + "\n";
		return muelle;
		
	}
	
	private String reporteAduanaImportaciones(String nombreBuque, String fechaPartida, String fechaArribo,
			List<Orden> ordenes) {
		
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
				+ "    		<ul>\n";
		
		for(Orden orden : ordenes) {
			aduana = aduana + orden.accept(visitorA);
		}
		
		return aduana;
		
	}
	
	private String reporteBuqueImportaciones(List<Orden> ordenes) {
		
		String repBuque = "<report>\n"
				+	  "		<import>\n";
		
		for(Orden orden : ordenes) {
			repBuque = repBuque + orden.accept(visitorB);
		}
		
		repBuque = repBuque +	"		</import>\n";
		
		return repBuque;
		
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public List<Reporte> finalizarReportesConExportaciones(Map<String,Reporte> reportes, List<Orden> ordenes) {
		
		///////////////////////Reporte muelle/////////////////////////
		
		String muelle = this.reporteMuelleExportaciones(ordenes);
		
		Reporte r1 = reportes.get("muelle"); //el reporte muelle con únicamente la información de importaciones
		r1.setTexto(r1.getTexto() + muelle); //el reporte muelle listo, con la información de importaciones y exportaciones
		
		///////////////////////Reporte muelle/////////////////////////
		
		///////////////////////Reporte aduana/////////////////////////
		
		String aduana = this.reporteAduanaExportaciones(ordenes);

		Reporte r2 = reportes.get("aduana"); //el reporte aduana con únicamente la información de importaciones
		r2.setTexto(r2.getTexto() + aduana); //el reporte aduana listo, con la información de importaciones y exportaciones
		
		///////////////////////Reporte aduana/////////////////////////
			
		///////////////////////Reporte buque/////////////////////////
		
		String repBuque = this.reporteBuqueExportaciones(ordenes);
		
		Reporte r3 = reportes.get("buque"); //el reporte buque con únicamente la información de importaciones
		r3.setTexto(r3.getTexto() + repBuque); //el reporte buque listo, con la información de importaciones y exportaciones
		
		///////////////////////Reporte buque/////////////////////////
		
		List<Reporte> rs = new ArrayList<Reporte>();
		rs.add(r1);
		rs.add(r2);
		rs.add(r3);
		
		return rs;
		
	}
	
	private String reporteMuelleExportaciones(List<Orden> ordenes) {
		
		String muelle = "Contenedores cargados: " + ordenes.size();
		return muelle;
		
	}
	
	private String reporteAduanaExportaciones(List<Orden> ordenes) {
		
		String aduana = "";
		
		for(Orden orden : ordenes) {
			aduana = aduana + orden.accept(visitorA);
		}

		aduana = aduana
				+ "    		</ul>\n"
				+ "    </body>\n"	
				+ "</html>";
		
		return aduana;
		
	}
	
	private String reporteBuqueExportaciones(List<Orden> ordenes) {
		
		String repBuque = "		<export>\n";
		
		for(Orden orden : ordenes) {
			repBuque = repBuque + orden.accept(visitorB);
		}
		
		repBuque = repBuque +	"		</export>\n"
							+ "/report";
		
		return repBuque;
		
	}

}
