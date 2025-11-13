package ar.edu.unq.po2.terminal_portuaria;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buscador_de_viaje.Condicion;
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
* @author Benjamin Maldonado 
* @author Franco Oreskovic.
*/

public class TerminalPortuaria implements TerminalObservadora {
	private Coordenada coordenada;
	private GeneradorDeReportes generadorReportes;
	
	private Set<EmpresaTransportista> empresasTransportistasRegistradas;
	private Set<Naviera> navierasRegistradas;
	private Set<Cliente> clientesRegistrados;
	private Set<CircuitoMaritimo> circuitosMaritimosRegistrados;

	private Set<PrecioServicioTerminal> serviciosDisponibles;
	private List<Orden> ordenesDeImportacion;
	private List<Orden> ordenesDeExportacion;
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
		this.ordenesDeImportacion = new ArrayList<Orden>();
		this.ordenesDeExportacion = new ArrayList<Orden>();
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Registra como exportación en la terminal la orden dada.
	 * @param orden es la orden a ser registrada como exportación en la terminal.
	 * @param camion es el camion informado por el shipper que va a ingresar la carga a la terminal.
	 * @param chofer es el chofer informado por el shipper que va a ingresar la carga a la terminal.
	 */
	public void registrarExportacion(Orden orden, Camion camion, Chofer chofer) {
		this.validarRegistrarExportacion(orden, camion, chofer, orden.getShipper());
		orden.crearServiciosACobrar();
		this.ordenesDeExportacion.add(orden);
	}

	/**
	 * Valida si la orden dada puede registrarse como exportación.
	 * @param orden es la orden a ser validada como exportación.
	 * @param camion es el camion informado por el shipper que va a ingresar la carga.
	 * @param chofer es el chofer informado por el shipper que va a ingresar la carga.
	 * @param shipper es el que realiza la exportación. 
	 */
	private void validarRegistrarExportacion(Orden orden, Camion camion, Chofer chofer, Cliente shipper) {
		if(!this.cumpleHorarioExportacion(orden) || !this.cumpleIngresoExportacion(orden, camion, chofer, shipper)) {
			throw new RuntimeException("No se encuentra en horario de exportación, o no se ha informado correctamente quienes deberan ingresar a la terminal.");
		}
	}
	
	/**
	 * Indica si la orden dada cumple el horario de exportación. Es decir, si se encuentra 3 horas antes de la hora de salida en el momento de consulta.
	 * La fecha de salida de la orden siempre es respecto a esta terminal.
	 * @param orden es la orden que se toma de referencia para evaluar si cumple con el horario de exportación.
	 */
	private boolean cumpleHorarioExportacion(Orden orden) {
		LocalDateTime fechaActual = LocalDateTime.now();
		LocalDateTime fechaSalida = orden.fechaDeSalida();
		LocalDateTime fechaMinimaPermitida = fechaSalida.minusHours(3);
		return fechaActual.isAfter(fechaMinimaPermitida) && fechaActual.isBefore(fechaSalida);
	}
	
	/**
	 * Indica si tiene registrados en la terminal el camion y chofer que se encuentran en la orden dada.
	 * @param orden es la orden que se toma de referencia para evaluar si cumple con el transporte asociado a la misma.
	 * @param camion es el camion informado por el shipper que va a ingresar la carga.
	 * @param chofer es el chofer informado por el shipper que va a ingresar la carga.
	 * @param shipper es el que realiza la exportación.
	 */
	private boolean cumpleIngresoExportacion(Orden orden, Camion camion, Chofer chofer, Cliente shipper) {
		return this.estanRegistradosParaIngresar(camion, chofer, shipper) && 
			   this.sonLosInformadosPorElShipper(orden, camion, chofer, shipper);
	}
	
	/**
	 * Indica si el camion, el chofer y el shipper dado son los informados en la orden dada.
	 * @param orden es la orden a verificar en la que se encuentran los datos dados.
	 * @param camion es el camion a verificar que se encuentra en la orden.
	 * @param chofer es el chofer a verificar que se encuentra en la orden.
	 * @param shipper es el shipper a verificar que se encuentra en la orden.
	 */
	private boolean sonLosInformadosPorElShipper(Orden orden, Camion camion, Chofer chofer, Cliente shipper) {
		Camion camionOrden = orden.getCamion();
		Chofer choferOrden = orden.getChofer();
		Cliente shipperOrden = orden.getShipper();
		return camionOrden.equals(camion) && choferOrden.equals(chofer) && shipperOrden.equals(shipper);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * Retira la importación la orden del consignee dado que se encuentra en la terminal, en base al camión y chofer dado.
	 * @param camion es el camion informado por el consignee que va a retirar la carga.
	 * @param chofer es el chofer informado por el consignee que va a retirar la carga.
	 * @param consignee es el dueño de la carga a retirar.
	 */
	public void retirarImportacion(Camion camion, Chofer chofer, Cliente consignee) {
		this.validarRetirarImportacion(camion, chofer, consignee);
		Orden orden = this.ordenDeConsignee(consignee);
		ordenesDeImportacion.remove(orden);
	}
	
	/**
	 * Valida si el consignee dado puede retirar una importación registrada a su nombre en la terminal, en base al camión y chofer dados.
	 * @param camion es el camion informado por el consignee que va a retirar la carga.
	 * @param chofer es el chofer informado por el consignee que va a retirar la carga.
	 * @param consignee es el dueño de la carga a retirar.
	 */
	private void validarRetirarImportacion(Camion camion, Chofer chofer, Cliente consignee) {
		if(this.tieneOrdenDeImportacionParaRetirar(consignee) && this.estanRegistradosParaIngresar(camion, chofer, consignee)) {
			throw new RuntimeException("No puede retirar ninguna exportación, ya que el consignee no tiene ninguna y/o no se encuentran registrados.");
		}
	}
	
	/**
	 * Indica si el consignee dado tiene una orden de importación registrada en la terminal.
	 * @param consignee es el consignee a verificar si se tiene una orden de importación registrada en la terminal.
	 */
	private boolean tieneOrdenDeImportacionParaRetirar(Cliente consignee) {
		return ordenesDeImportacion.stream()
								   .anyMatch(o -> consignee.equals(o.getConsignee()));
	}
	
	/**
	 * Indica si tiene registrados en la terminal el camion, chofer y el consignee dados.
	 * @param camion es el camion a verificar si se encuentra registrado en la terminal.
	 * @param chofer es el chofer a verificar si se encuentra registrado en la terminal.
	 * @param cliente es el cliente a verificar si se encuentra registrado en la terminal.
	 */
	private boolean estanRegistradosParaIngresar(Camion camion, Chofer chofer, Cliente cliente) {
		return this.estaRegistradoCamionYChofer(camion, chofer) && this.estaRegistradoCliente(cliente);
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
	 * @param cliente es el cliente a verificar si se encuentra registrado en la terminal.
	 */
	private boolean estaRegistradoCliente(Cliente cliente) {
		return clientesRegistrados.contains(cliente);
	}
	
	/**
	 * Describe la orden de importacion del consignee dado que se encuentra registrada en la terminal.
	 * @param consignee es el dueño de la carga.
	 */
	private Orden ordenDeConsignee(Cliente consignee) {
		return ordenesDeImportacion.stream()
								   .filter(o -> consignee.equals(o.getConsignee()))
								   .findFirst()
								   .get();
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void trabajarEnBuque(Buque buque) {
		this.validarTrabajosEnBuque(buque); // Valida que puede trabajar en el buque (misma coordenada).
		this.iniciarTrabajos(buque); 	// Inicia la descarga y carga de ordenes en el buque.
		this.generarReportes(buque); 	// Genera los reportes en base a lo cargado y descargado del buque.
		this.finalizarTrabajos(buque);  // Finaliza la carga y descarga de ordenes en el buque, borrando de ambos lados lo cargado y descargado respectivamente.
	}
	
	private void validarTrabajosEnBuque(Buque buque) {
		if(!coordenada.equals(buque.posicionActual())) { // Esto debería verificar si está en estado Arrived.
			throw new RuntimeException("El buque no se encuentra en la terminal para realizar la carga o descarga");
		}
	}
	
	private void iniciarTrabajos(Buque buque) {
		buque.iniciarTrabajos(); 	  		// Esto debería pasar el buque a estado Working.
		this.iniciarDescargaOrdenes(buque); // Añade las ordenes a la lista de importaciones.
		this.iniciarCargaOrdenes(buque); 	// Añade las ordenes de la lista de exportaciones al buque.
	}
	
	private void iniciarDescargaOrdenes(Buque buque) {
		List<Orden> ordenes = buque.getOrdenesADescargar(this);
		
		for(Orden o : ordenes) {
			ordenesDeImportacion.add(o);
			o.crearServiciosACobrar();
		}
	}

	private void iniciarCargaOrdenes(Buque buque) {
		List<Orden> ordenes = ordenesDeExportacion.stream()
												  .filter(o -> o.getViaje().equals(buque.getViajeActual()))
												  .toList();
		buque.cargarOrdenes(ordenes);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void generarReportes(Buque buque) {
		// Genera reportes de importación
		List<Orden> ordenesImportacion = ordenesDeImportacion.stream()
				  						   				     .filter(o -> buque.getOrdenes().contains(o))
				  								  			 .toList();
		Map<String, Reporte> reportesImportaciones = generadorReportes.generarReportesConImportaciones(buque, ordenesImportacion);
		
		// Genera reportes de exportación
		List<Orden> ordenesExportacion = ordenesDeExportacion.stream()
				  											 .filter(o -> buque.getOrdenes().contains(o))
				  											 .toList();
		List<Reporte> reportesCompletos = generadorReportes.finalizarReportesConExportaciones(reportesImportaciones, ordenesExportacion);
		
		// Añadir los reportes generados
		reportesGenerados.addAll(reportesCompletos);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void finalizarTrabajos(Buque buque) {
		List<Orden> ordenesCargadas = ordenesDeExportacion.stream()
				  										  .filter(o -> o.getViaje().equals(buque.getViajeActual()))
				  										  .toList();
		List<Orden> ordenesDescargadas = buque.getOrdenesADescargar(this);
		
		buque.finalizarDescargaDeOrdenes(ordenesDescargadas); // Elimina las ordenes descargadas en la terminal que estaban en el buque.
		this.finalizarCargaDeOrdenes(ordenesCargadas); // Elimina las ordenes cargadas en el buque de la terminal.
		buque.finalizarTrabajos(); // Esto debería pasar el buque a estado Departing.
	}
	
	private void finalizarCargaDeOrdenes(List<Orden> ordenesCargadas) {
		for(Orden o : ordenesCargadas) {
			this.ordenesDeExportacion.remove(o);
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
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
	 * Registra el circuito marítimo dado en la Terminal Portuaria. Este debe incluir a la terminal.
	 * @param circuitoMaritimo es el circuito marítimo a registrar en la Terminal Portuaria. Debe incluir a la terminal.
	 */
	public void registrarCircuitoMaritimo(CircuitoMaritimo circuitoMaritimo) {
		this.validarQueIncluyaTerminal(circuitoMaritimo);
		this.circuitosMaritimosRegistrados.add(circuitoMaritimo);
	}
	
	private void validarQueIncluyaTerminal(CircuitoMaritimo circuitoMaritimo) {
		if(!circuitoMaritimo.incluyeA(this)) {
			throw new RuntimeException("El circuito marítimo debe incluir a la terminal para poder ser registrado.");
		}
	}
	
	/**
	 * Describe una orden que puede ser utilizada para exportar desde la Terminal Portuaria.
	 * @param camion es el camion contratado para ser asignado a la orden a generar.
	 * @param chofer es el chofer contratado para ser asignado a la orden a generar.
	 * @param container es el container que contiene lo que va a ser transportado en la orden a generar.
	 * @param viaje es el viaje determinado para ser asignado a la orden a generar.
	 * @param shipper es el encargado de exportar la orden.
	 */
	public Orden generarOrden(Camion camion, Chofer chofer, Container container, Viaje viaje, Cliente shipper) {
		return new OrdenDeExportacion(camion, chofer, container, viaje, shipper);
	}
	
	/**
	 * Describe el precio del servicio dado.
	 * @param servicio es el servicio a consultar su precio.
	 */
	public double precioServicio(PrecioServicioTerminal servicio) {
		this.validarPrecioServicio(servicio);
		return servicio.getPrecio();
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public void setBuscadorDeCircuito(CircuitoMaritimo circuitoMaritimo) {
		
		
	}
	
	public CircuitoMaritimo buscarCircuito(TerminalPortuaria terminalPortuaria) {
		return null;
		
	}
	
	public List<Viaje> buscarViaje(Condicion condicion) {
		return null;
		
	}
	
	public Duration tiempoEntre(TerminalPortuaria terminalPortuaria, Naviera naviera) {
		return null;
		
	}
	
	public LocalDateTime proximaFechaHacia(TerminalPortuaria terminalPortuaria, Buque buque) {
		return null;
		
	}
	
	// #################################### MÉTODOS AUXILIARES ################################## \\
	
	@Override
	public boolean equals(Object object) {
		TerminalPortuaria terminalAComparar = (TerminalPortuaria) object;
		return coordenada.equals(terminalAComparar.getCoordenada());
	}
	
	@Override
	public int hashCode() {
		return coordenada.hashCode();
	}

		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	// METODOS PARA LO QUE  ES EL BUQUE, REFACTORIZAR UNA VEZ ESTEN IMPLEMENTADOS
	//A PARTIR DE AQUI ES TODO LO QUE HACE LA TERMINAL CUANDO EL BUQUE ESTA LLEGANDO
	/*
	public void actualizar(Buque buque) {
		
		
	}
	
	public boolean puedeIniciarWorking(Buque miBuque) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean partidaHabilitada(Buque miBuque) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
	@Override
	public void adscribirObservado(Buque buque) {
		// TODO Auto-generated method stub
		
	}
	public void notificarConsignee(Viaje viajeActual) {
		// TIENE QUE HACERSE UNA SOLA VEZ
		
	}
	
	public void notificarArribo(Buque miBuque) {
		// TODO Auto-generated method stub
		System.out.println("AVISA A LOS CONSIGNEE QUE LA CARGA ESTA POR LLEGAR");
	}
	
	public void notificarSalidaTerminal(Buque miBuque) {
		// TODO Auto-generated method stu
		System.out.println("MANDA LOS MAILS A LOS SHIPPERS");
		
	}
	
	public double limiteHorasAlmacenaje() {
		// TODO Auto-generated method stub
		return 24;
	}
}