package ar.edu.unq.po2.terminal_portuaria;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.naviera.Naviera;

/**
* Describe una terminal portuaria.
* @author Benjamin Maldonado & Franco Oreskovic.
*/

public class TerminalPortuaria {
	private Coordenada coordenada;
	private Set<EmpresaTransportista> empresasTransportistasRegistradas;
	private Set<Naviera>  navierasRegistradas;
	private Set<Cliente>  clientesRegistrados;
	private Set<CircuitoMaritimo> circuitosMaritimosRegistrados;
	
	/**
	 * @param coordenada son las coordenadas de la Terminal Portuaria.
	 */
	public TerminalPortuaria(Coordenada coordenada) {
		this.coordenada = coordenada;
		this.empresasTransportistasRegistradas = new HashSet<EmpresaTransportista>();
		this.navierasRegistradas = new HashSet<Naviera>();
		this.clientesRegistrados = new HashSet<Cliente>();
		this.circuitosMaritimosRegistrados = new HashSet<CircuitoMaritimo>();
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
	 * TODO:
	 */
	public void registrarExportacion(Camion camion) {
		// Se crea la orden en la terminal, la misma la devuelve.
		
		// La misma se carga en el camion pasado por parámetro, y se lo hace ingresar en la terminal.
		
		// Si el camion está 3 horas como minimo de antelacion y pasa las validaciones, todo bien; en caso contrario, falla.
		
		// Si no falló, la orden queda almacenada como orden de exportación esperando el barco.
		
		// Cuando el barco llega, carga las cosas que estan almacenadas que coincidan con su viaje.
		
		// Faltaría ver el tema del mail diciendo que se mandó.
	}

	/**
	 * TODO:
	 */
	public void registrarEmpresaTransportista(EmpresaTransportista empresaTransportista) {
		this.empresasTransportistasRegistradas.add(empresaTransportista);
	}
	
	/**
	 * TODO:
	 */
	public void registrarNaviera(Naviera naviera) {
		this.navierasRegistradas.add(naviera);
	}
	
	/**
	 * TODO:
	 */
	public void registrarCliente(Cliente cliente) {
		this.clientesRegistrados.add(cliente);
	}
	
	/**
	 * TODO:
	 */
	public void registrarCircuitoMaritimo(CircuitoMaritimo circuitoMaritimo) {
		this.circuitosMaritimosRegistrados.add(circuitoMaritimo);
	}
}