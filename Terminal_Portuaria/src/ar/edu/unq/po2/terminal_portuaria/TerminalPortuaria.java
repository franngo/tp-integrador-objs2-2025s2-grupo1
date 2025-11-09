package ar.edu.unq.po2.terminal_portuaria;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.generadorDeReportes.GeneradorDeReportes;
import ar.edu.unq.po2.generadorDeReportes.Reporte;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;

public class TerminalPortuaria {
	
	private GeneradorDeReportes generadorReportes;
	private List<Reporte> reportesGenerados;
	private List<Orden> ordenesParaImportacion; 
	private List<Orden> ordenesParaExportacion; 

	public void registrarEmpresaTransportista(EmpresaTransportista empresaTransportista) {
		// TODO Auto-generated method stub
	}

	public void retirarCamion(Camion camion) {
		// TODO Auto-generated method stub
	}

	public void ingresarCamion(Camion camion) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * 
	 * mensaje para Benja: el constructor de TerminalPortuaria tiene que recibir un Set de 
	 * enumerativos PrecioServicioTerminal(o en su defecto instanciar la lista). Tambien habria que agrega
	 * un setter por si hay que agregar otro PrecioServicioTerminal
	 * */
	
	/*
	 * @Autor: Matias Sanchez
	 * metodos para los cobros de servicios
	 
	 * */
	 private Set<PrecioServicioTerminal> servicios;

	 public double precioServicio(PrecioServicioTerminal servicio) {
		 
		 this.validarServicio(servicio);
		 return servicio.getPrecio();
	 }

	 private void validarServicio(PrecioServicioTerminal servicio) {
		// TODO Auto-generated method stub
		 if (!servicios.contains(servicio)) {
			 throw new IllegalArgumentException("Servicio no disponible en esta terminal");
		 }
	 }
	 
	 	/*
	 - Genera reportes que unicamente tienen cargada la información de las importaciones.
 
	 - Se debería llamar después de descargar los containers de importaciones del buque.
	 */
	protected Map<String,Reporte> generarReportesConImportaciones(Buque buque) {
  
		List<Orden> ordenes = ordenesParaImportacion.stream().filter((o) -> buque.getOrdenes().contains(o)).toList();
		return generadorReportes.generarReportesConImportaciones(buque, ordenes);
  
	}
	
	/*
	 - Agrega la información de las exportaciones a los Reportes pasados y los guarda en la lista de reportes de la 
	   terminal gestionada (debido a que ya son considerados Reportes completos).
 	   
	 - Se debería llamar después de cargar los containers de exportaciones al buque.
	 
 	 - Se le deben pasar por parámetro lo que devuelve el método generarReportesConImportaciones(), que son los reportes que 
 	   tienen las importaciones cargadas, y a estos se les agregará la información de las exportaciones.
	 */
	protected void finalizarReportesConExportaciones(Buque buque, Map<String,Reporte> reportes) {
    
		List<Orden> ordenes = ordenesParaExportacion.stream().filter((o) -> buque.getOrdenes().contains(o)).toList();
		List<Reporte> reportesPorAgregar = generadorReportes.finalizarReportesConExportaciones(reportes, ordenes);
		reportesGenerados.addAll(reportesPorAgregar);
    
	}
	 
}