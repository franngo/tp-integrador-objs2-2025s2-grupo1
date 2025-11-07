package ar.edu.unq.po2.terminal_portuaria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.naviera.Naviera;
import ar.edu.unq.po2.orden.*;
import ar.edu.unq.po2.reporte.Reporte;
import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.viaje.Viaje;

/**
* Describe una terminal portuaria.
* @author Benjamin Maldonado & Franco Oreskovic.
*/

public class TerminalPortuaria {
	private Coordenada coordenada;
	
	private Set<EmpresaTransportista> empresasTransportistasRegistradas;
	private Set<Naviera> navierasRegistradas;
	private Set<Cliente> clientesRegistrados;
	private Set<CircuitoMaritimo> circuitosMaritimosRegistrados;

	// TODO: Implementar todo lo relacionado a esto:
	private Set<Orden> ordenesDeImportacion;
	private Set<Orden> ordenesDeExportacion;
	private Set<Servicio> serviciosDisponibles;
	private List<Reporte> reportes;
	
	/**
	 * @param coordenada son las coordenadas de la Terminal Portuaria.
	 */
	public TerminalPortuaria(Coordenada coordenada) {
		this.coordenada = coordenada;
		
		this.empresasTransportistasRegistradas = new HashSet<EmpresaTransportista>();
		this.navierasRegistradas = new HashSet<Naviera>();
		this.clientesRegistrados = new HashSet<Cliente>();
		this.circuitosMaritimosRegistrados = new HashSet<CircuitoMaritimo>();
		
		this.ordenesDeExportacion = new HashSet<Orden>();
		this.ordenesDeImportacion = new HashSet<Orden>();
		this.serviciosDisponibles = new HashSet<Servicio>();
		this.reportes = new ArrayList<Reporte>();
	}
	
	/**
	 * Describe la coordenada de la Terminal Portuaria.
	 */
	public Coordenada getCoordenada() {
		return new Coordenada(coordenada.getLatitud(), coordenada.getLongitud());
	}

	/**
	 * TODO:
	 */
	public void retirarImportacion(Camion camion) {
		// Se baja la orden del barco, queda almacenada en la terminal como orden de importación.
		
		// Al mismo tiempo se le envía un mail al dueño diciendo que tiene que venir a buscarla, y darle un lapso maximo de 24 horas.
		
		// Si viene cumpliendo el lapso de tiempo, se instancian los servicios necesarios menos el de almacenamiento excedente.
		
		// Si no cumple, sucede lo contrario.
		
		// Supongo que aca iria el desglose de conceptos.
	}

	/**
	 * Registra como exportación en la Terminal Portuaria la orden que posee el camión dado.
	 * @param camion es el camion que transporta la orden a ser registrada como exportación en la Terminal Portuaria.
	 */
	public void registrarExportacion(Camion camion) {
		Orden ordenARegistrar = camion.getOrdenActual();
		this.validarRegistrarExportacion(ordenARegistrar);
		this.ordenesDeExportacion.add(ordenARegistrar);
	}

	/**
	 * Valida si la orden dada puede registrarse como exportación.
	 * @param orden es la orden a ser validada como exportación.
	 */
	private void validarRegistrarExportacion(Orden orden) {
		if(!this.cumpleHorarioExportacion(orden) || !this.cumpleElTransporte(orden)) {
			new RuntimeException("No se encuentra en horario de exportación, o bien el camion y/o el chofer no son los previamente informados.");
		}
	}
	
	

	/**
	 * Registra la empresa transportista dada en la Terminal Portuaria.
	 * @param empresaTransportista es la empresa transportista a registrar en la Terminal Portuaria.
	 */
	public void registrarEmpresaTransportista(EmpresaTransportista empresaTransportista) {
		this.empresasTransportistasRegistradas.add(empresaTransportista);
	}
	
	/**
	 * Registra la naviera dada en la Terminal Portuaria.
	 * @param naviera es la naviera a registrar en la Terminal Portuaria.
	 */
	public void registrarNaviera(Naviera naviera) {
		this.navierasRegistradas.add(naviera);
	}
	
	/**
	 * Registra el cliente dado en la Terminal Portuaria.
	 * @param cliente es el cliente a registrar en la Terminal Portuaria.
	 */
	public void registrarCliente(Cliente cliente) {
		this.clientesRegistrados.add(cliente);
	}
	
	/**
	 * Registra el circuito marítimo dado en la Terminal Portuaria.
	 * @param circuitoMaritimo es el circuito marítimo a registrar en la Terminal Portuaria.
	 */
	public void registrarCircuitoMaritimo(CircuitoMaritimo circuitoMaritimo) {
		this.circuitosMaritimosRegistrados.add(circuitoMaritimo);
	}
	
	/**
	 * Describe una orden que puede ser utilizada para exportar desde la Terminal Portuaria.
	 * @param camion es el camion contratado para ser asignado a la orden a generar.
	 * @param chofer es el chofer contratado para ser asignado a la orden a generar.
	 * @param container es el container que contiene lo que va a ser transportado en la orden a generar.
	 * @param viaje es el viaje determinado para ser asignado a la orden a generar.
	 */
	public Orden generarOrden(Camion camion, Chofer chofer, Container container, Viaje viaje) {
		return new OrdenDeExportacion(camion, chofer, container, viaje);
	}
}