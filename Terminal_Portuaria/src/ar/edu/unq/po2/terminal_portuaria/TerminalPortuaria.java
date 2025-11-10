package ar.edu.unq.po2.terminal_portuaria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.generadorDeReportes.*;
import ar.edu.unq.po2.naviera.Naviera;
import ar.edu.unq.po2.orden.*;
import ar.edu.unq.po2.servicio.PrecioServicioTerminal;
import ar.edu.unq.po2.viaje.Viaje;

/**
* Describe una terminal portuaria.
* @author Benjamin Maldonado & Franco Oreskovic.
*/

public class TerminalPortuaria {
	private Coordenada coordenada;
	private GeneradorDeReportes generadorReportes;
	
	private Set<EmpresaTransportista> empresasTransportistasRegistradas;
	private Set<Naviera> navierasRegistradas;
	private Set<Cliente> clientesRegistrados;
	private Set<CircuitoMaritimo> circuitosMaritimosRegistrados;

	private Set<PrecioServicioTerminal> serviciosDisponibles;
	private Set<Orden> ordenesDeImportacion;
	private Set<Orden> ordenesDeExportacion;
	private List<Reporte> reportesGenerados;
  	
	/**
	 * @param coordenada son las coordenadas en donde se encuentra geográficamente la Terminal Portuaria.
	 */
	public TerminalPortuaria(Coordenada coordenada) {
		this.coordenada = coordenada;
		this.generadorReportes = new GeneradorDeReportes();
		
		this.empresasTransportistasRegistradas = new HashSet<EmpresaTransportista>();
		this.navierasRegistradas = new HashSet<Naviera>();
		this.clientesRegistrados = new HashSet<Cliente>();
		this.circuitosMaritimosRegistrados = new HashSet<CircuitoMaritimo>();
		
		this.serviciosDisponibles = new HashSet<PrecioServicioTerminal>();
		this.ordenesDeImportacion = new HashSet<Orden>();
		this.ordenesDeExportacion = new HashSet<Orden>();
		this.reportesGenerados = new ArrayList<Reporte>();
		
		serviciosDisponibles.add(PrecioServicioTerminal.DIAEXCEDENTE);
		serviciosDisponibles.add(PrecioServicioTerminal.KILOWATTCONSUMIDO);
		serviciosDisponibles.add(PrecioServicioTerminal.LAVADOCOMUN);
		serviciosDisponibles.add(PrecioServicioTerminal.LAVADOPESADO);
		serviciosDisponibles.add(PrecioServicioTerminal.PESAJE);
		serviciosDisponibles.add(PrecioServicioTerminal.PRECIODESCONSOLIDADO);
		serviciosDisponibles.add(PrecioServicioTerminal.REVISIONDIARIA);
	}
	
	/**
	 * Describe la coordenada de la Terminal Portuaria.
	 */
	public Coordenada getCoordenada() {
		return new Coordenada(coordenada.getLatitud(), coordenada.getLongitud());
	}

	
	
	
	
	
	
	
	/**
	 * 
	 * @param camion es el camion informado por el consignee que va a ingresar la carga.
	 * @param chofer es el chofer informado por el consignee que va a ingresar la carga.
	 * @param consignee es el dueño de la carga a retirar.
	 */
	public void retirarImportacion(Camion camion, Chofer chofer, Cliente consignee) {
		this.validarRetirarImportacion(camion, chofer, consignee);
		
		
		// Desglose de conceptos.
	}
	
	
	public void validarRetirarImportacion(Camion camion, Chofer chofer, Cliente consignee) {
		if(this.tieneOrdenDeImportacionParaRetirar(consignee) && this.) {
			throw new RuntimeException("");
		}
	}	
	
	
	/**
	 * Indica si tiene registrados en la terminal el camion y chofer que se encuentran en la orden dada.
	 * @param orden es la orden que se toma de referencia para evaluar si cumple con el transporte asociado a la misma.
	 * @param camion es el camion informado por el consignee que va a ingresar la carga.
	 * @param chofer es el chofer informado por el consignee que va a ingresar la carga.
	 * @param consignee es el dueño de la carga.
	 */
	private boolean estanRegistradosParaIngresar(Camion camion, Chofer chofer, Cliente consignee) {
		return this.estaRegistradoCamionYChofer(camion, chofer) && this.estaRegistradoCliente(consignee);
	}
	
	/**
	 * Indica si tiene registrados en la terminal el camion y chofer que se encuentran en la orden dada.
	 * @param camion es el camion a verificar si se encuentra registrado en la terminal.
	 * @param chofer es el chofer a verificar si se encuentra registrado en la terminal.
	 */
	private boolean estaRegistradoCamionYChofer(Camion camion, Chofer chofer) {
		return empresasTransportistasRegistradas.stream()
												.anyMatch(e -> e.tieneCamionYChoferRegistrados(camion, chofer));
	}
	
	/**
	 * Indica si el cliente dado se encuentra registrado en la terminal.
	 */
	private boolean estaRegistradoCliente(Cliente cliente) {
		return clientesRegistrados.contains(cliente);
	}
	
	/**
	 * Indica si la orden dada cumple con el plazo de almacenamiento que ofrece la Terminal Portuaria, el cual es de 24 horas almacenado en la misma.
	 * @param orden es la orden a verificar si cumple con el plazo de almacenamiento gratuito.
	 */
	private boolean cumplePlazoAlmacenamientoGratuito(Orden orden) {
		LocalDateTime fechaActual  = LocalDateTime.now(); 
		LocalDateTime fechaLlegada = orden.fechaDeLlegada();
		LocalDateTime fechaMaxima  = fechaLlegada.plusDays(1);
		return fechaActual.isAfter(fechaLlegada) && fechaActual.isBefore(fechaMaxima);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Registra como exportación en la Terminal Portuaria la orden dada.
	 * @param orden es la orden a ser registrada como exportación en la Terminal Portuaria.
	 * @param camion es el camion informado por el consignee que va a ingresar la carga.
	 * @param chofer es el chofer informado por el consignee que va a ingresar la carga.
	 * @param consignee es el dueño de la carga.
	 */
	public void registrarExportacion(Orden orden, Camion camion, Chofer chofer, Cliente consignee) {
		this.validarRegistrarExportacion(orden, camion, chofer, consignee);
		this.ordenesDeExportacion.add(orden);
	}

	/**
	 * Valida si la orden dada puede registrarse como exportación.
	 * @param orden es la orden a ser validada como exportación.
	 * @param camion es el camion informado por el consignee que va a ingresar la carga.
	 * @param chofer es el chofer informado por el consignee que va a ingresar la carga.
	 * @param consignee es el dueño de la carga. 
	 */
	private void validarRegistrarExportacion(Orden orden, Camion camion, Chofer chofer, Cliente consignee) {
		if(!this.cumpleHorarioExportacion(orden) || !this.cumpleIngresoExportacion(orden, camion, chofer, consignee)) {
			new RuntimeException("No se encuentra en horario de exportación, o bien el camion y/o el chofer no estan registrados en la Terminal.");
		}
	}
	
	/**
	 * Indica si la orden dada cumple el horario de exportación. Es decir, si se encuentra 3 horas antes de la hora de salida en el momento de consulta.
	 * @param orden es la orden que se toma de referencia para evaluar si cumple con el horario de exportación.
	 */
	private boolean cumpleHorarioExportacion(Orden orden) {
		LocalDateTime fechaActual  = LocalDateTime.now();
		LocalDateTime fechaSalida = orden.fechaDeSalida();
		LocalDateTime fechaMinimaPermitida = fechaSalida.minusHours(3);
		return fechaActual.isAfter(fechaMinimaPermitida) && fechaActual.isBefore(fechaSalida);
	}
	
	/**
	 * Indica si tiene registrados en la Terminal el camion y chofer que se encuentran en la orden dada.
	 * @param orden es la orden que se toma de referencia para evaluar si cumple con el transporte asociado a la misma.
	 * @param camion es el camion informado por el consignee que va a ingresar la carga.
	 * @param chofer es el chofer informado por el consignee que va a ingresar la carga.
	 * @param consignee es el dueño de la carga.
	 */
	private boolean cumpleIngresoExportacion(Orden orden, Camion camion, Chofer chofer, Cliente consignee) {
		return this.sonLosInformadosPorElConsignee(orden, camion, chofer, consignee) && 
			   this.estanRegistradosParaIngresar(camion, chofer, consignee);
	}
	
	/**
	 * Indica si el cliente dado se encuentra registrado en la terminal.
	 */
	private boolean sonLosInformadosPorElConsignee(Orden orden, Camion camion, Chofer chofer, Cliente consignee) {
		Camion camionOrden = orden.getCamion();
		Chofer choferOrden = orden.getChofer();
		Cliente consigneeOrden = orden.getConsignee();
		return camionOrden.equals(camion) && choferOrden.equals(chofer) && consigneeOrden.equals(consignee);
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
	
	/**
	 * Describe el precio del servicio dado.
	 * @param servicio es el servicio a consultar su precio.
	 */
	public double precioServicio(PrecioServicioTerminal servicio) {
		this.validarPrecioServicio(servicio);
		return serviciosDisponibles.stream()
								   .filter(s -> s.equals(servicio))
								   .findFirst()
								   .get()
								   .getPrecio();
	}

	/**
	 * Valida si puede devolver el precio del servicio dado.
	 * @param servicio es el servicio a verificar si existe en los servicios disponibles en la terminal.
	 */
	private void validarPrecioServicio(PrecioServicioTerminal servicio) {
		if (!serviciosDisponibles.contains(servicio)) {
			throw new RuntimeException("El servicio dado no se encuentra disponible en la terminal.");
		}
	}
	
	/**
	 * Genera reportes que unicamente tienen cargada la información de las importaciones.
	 * Se debería llamar después de descargar los containers de importaciones del buque.
	 * @param buque es el buque del que se toma como referencia para las ordenes de importación.
	 */
	private Map<String, Reporte> generarReportesConImportaciones(Buque buque) {
		List<Orden> ordenes = ordenesDeImportacion.stream()
												  .filter(o -> buque.getOrdenes().contains(o))
												  .toList();
		return generadorReportes.generarReportesConImportaciones(buque, ordenes);
	}
	
	/**
	 * Agrega la información de las exportaciones a los Reportes pasados y los guarda en la lista de reportes de la Terminal Portuaria.
	 * Se debería llamar después de cargar los containers de exportaciones al buque.
	 * @param buque es el buque del que se toma como referencia para las ordenes de exportación.
	 * @param Map<String, Reporte> son los reportes que tienen las importaciones cargadas, los cuales se les agregará la información de las exportaciones.
	 */
	private void finalizarReportesConExportaciones(Buque buque, Map<String, Reporte> reportes) {
		List<Orden> ordenes = ordenesDeExportacion.stream()
												  .filter(o -> buque.getOrdenes().contains(o))
												  .toList();
		List<Reporte> reportesPorAgregar = generadorReportes.finalizarReportesConExportaciones(reportes, ordenes);
		reportesGenerados.addAll(reportesPorAgregar);
	}
	
	// #################################### MÉTODOS AUXILIARES ################################## \\
	
	@Override
	public boolean equals(Object object) {
		return (this == object) || (this.esTerminalPortuaria(object) && (this.esElMismoQue(object)));
	}
	
	private boolean esTerminalPortuaria(Object object) {
		return object instanceof TerminalPortuaria;
	}
	
	private boolean esElMismoQue(Object object) {
		TerminalPortuaria terminalAComparar = (TerminalPortuaria) object;
		return coordenada.equals(terminalAComparar.getCoordenada());
	}
	
	@Override
	public int hashCode() {
		return coordenada.hashCode();
	}
}