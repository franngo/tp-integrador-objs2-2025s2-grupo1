package ar.edu.unq.po2.terminal_portuaria;

import java.util.Set;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.circuito.Circuito;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.naviera.Naviera;

public class TerminalPortuaria {
	private Set<EmpresaTransportista> empresasTransportistas;
	
	public TerminalPortuaria() {
		
	}

	// #########################################################################################
	
	public void retirarImportacion(Camion camion) {
		// Se baja la orden del barco, queda almacenada en la terminal como orden de importación.
		
		// Al mismo tiempo se le envía un mail al dueño diciendo que tiene que venir a buscarla, y darle un lapso maximo de 24 horas.
		
		// Si viene cumpliendo el lapso de tiempo, se instancian los servicios necesarios menos el de almacenamiento excedente.
		
		// Si no cumple, sucede lo contrario.
		
		// Supongo que aca iria el desglose de conceptos.
	}

	public void registrarExportacion(Camion camion) {
		// Se crea la orden en la terminal, la misma la devuelve.
		
		// La misma se carga en el camion pasado por parámetro, y se lo hace ingresar en la terminal.
		
		// Si el camion está 3 horas como minimo de antelacion y pasa las validaciones, todo bien; en caso contrario, falla.
		
		// Si no falló, la orden queda almacenada como orden de exportación esperando el barco.
		
		// Cuando el barco llega, carga las cosas que estan almacenadas que coincidan con su viaje.
		
		// Faltaría ver el tema del mail diciendo que se mandó.
	}

	public void registrarEmpresaTransportista(EmpresaTransportista empresaTransportista) {
		this.empresasTransportistas.add(empresaTransportista);
	}
	
	public void registrarNaviera(Naviera naviera) {
		// TODO Auto-generated method stub
	}
	
	public void registrarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
	}
	
	public void registrarCircuito(Circuito circuito) {
		// TODO Auto-generated method stub
	}
}