package ar.edu.unq.po2.terminal_portuaria;

import java.util.List;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.generadorDeReportes.GeneradorDeReportes;
import ar.edu.unq.po2.generadorDeReportes.Reporte;

public class TerminalPortuaria {
	
	private GeneradorDeReportes generadorReportes;

	public void registrarEmpresaTransportista(EmpresaTransportista empresaTransportista) {
		// TODO Auto-generated method stub
	}

	public void retirarCamion(Camion camion) {
		// TODO Auto-generated method stub
	}

	public void ingresarCamion(Camion camion) {
		// TODO Auto-generated method stub
	}
	
	protected List<Reporte> generarReportesConImportaciones(Buque buque) {
		
	}
	
	protected void finalizarReportesConExportaciones(Buque buque, List<Reporte> reportes) {
		
	}
}