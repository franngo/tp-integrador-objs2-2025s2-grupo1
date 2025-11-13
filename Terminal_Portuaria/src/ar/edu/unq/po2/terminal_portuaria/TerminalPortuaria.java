package ar.edu.unq.po2.terminal_portuaria;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buscador_de_circuito.BuscadorDeCircuito;
import ar.edu.unq.po2.buscador_de_circuito.BuscadorPorPrecio;
import ar.edu.unq.po2.buscador_de_viaje.BuscadorDeViaje;
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
import ar.edu.unq.po2.servicio.Servicio;
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
	
	private BuscadorDeCircuito buscadorDeCircuito;
	private BuscadorDeViaje buscadorDeViaje;
  	
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
		
		this.buscadorDeCircuito = new BuscadorPorPrecio();
		this.buscadorDeViaje = new BuscadorDeViaje();
		
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
	
	/**
	 * Retira la importación la orden del consignee dado que se encuentra en la terminal, en base al camión y chofer dado.
	 * @param camion es el camion informado por el consignee que va a retirar la carga.
	 * @param chofer es el chofer informado por el consignee que va a retirar la carga.
	 * @param consignee es el dueño de la carga a retirar.
	 */
	public void retirarImportacion(Camion camion, Chofer chofer, Cliente consignee) {
		this.validarRetirarImportacion(camion, chofer, consignee);
		Orden orden = this.ordenDeConsignee(consignee);
		this.cobrarServicioConsignee(orden);
		ordenesDeImportacion.remove(orden);
	}
	
	void cobrarServicioConsignee(Orden orden) {
		String factura = this.generarFacturaServicios(orden);
		orden.getConsignee().recibirMail(factura);
	}
	
	private String generarFacturaServicios(Orden orden) {
		 StringBuilder desgloceConceptos = new StringBuilder();

		    desgloceConceptos.append("Estimado/a ").append(orden.getConsignee().nombreCliente()).append(",\n\n");
		    desgloceConceptos.append("A continuación se detalla el desglose de los servicios asociados a su orden:\n\n");

		    desgloceConceptos.append(String.format("%-30s %10s\n", "Servicio", "Precio"));
		    desgloceConceptos.append("--------------------------------------------------\n");

		    double total = 0.0;
		    for (Servicio serv : orden.getServiciosOrden()) {
		        desgloceConceptos.append(String.format("%-30s $%10.2f\n", serv.tipoServicio(), serv.costoServicio(this, LocalDateTime.now())));
		        
		    }

		    desgloceConceptos.append("--------------------------------------------------\n");
		    desgloceConceptos.append(String.format("%-30s $%10.2f\n", "TOTAL", total));
		    desgloceConceptos.append("\nGracias por confiar en nosotros.\n");
		    desgloceConceptos.append("Atentamente,\n");
		    desgloceConceptos.append("Equipo de Logística\n");
		    
		    return desgloceConceptos.toString();
		
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

	/**
	 * Realiza los trabajos de carga y descarga con el buque dado, si el mismo se encuentra en la terminal.
	 * @param buque es el buque sobre el cual realizar los trabajos.
	 */
	public void trabajarEnBuque(Buque buque) {
		this.validarTrabajosEnBuque(buque); // Valida que puede trabajar en el buque (misma coordenada).
		this.iniciarTrabajos(buque); 	// Inicia la descarga y carga de ordenes en el buque.
		this.generarReportes(buque); // Genera los reportes en base a lo cargado y descargado del buque.
		this.finalizarTrabajos(buque);  // Finaliza la carga y descarga de ordenes en el buque, borrando de ambos lados lo cargado y descargado respectivamente.
	}
	
	
	
	/**
	 * Valida que la terminal puede trabajar con el buque dado.
	 * @param buque es el buque sobre el cual realizar la validación.
	 */
	private void validarTrabajosEnBuque(Buque buque) {
		if(!coordenada.equals(buque.posicionActual())) { // Esto debería verificar si está en estado Arrived.
			throw new RuntimeException("El buque no se encuentra en la terminal para realizar la carga o descarga");
		}
	}
	
	/**
	 * Inicia los trabajos de carga y descarga en el buque dado.
	 * @param buque es el buque sobre el cual realizar la carga y descarga.
	 */
	private void iniciarTrabajos(Buque buque) {
		buque.iniciarTrabajos(); 	  		// Esto debería pasar el buque a estado Working.
		this.iniciarDescargaOrdenes(buque); // Añade las ordenes a la lista de importaciones.
		this.iniciarCargaOrdenes(buque); 	// Añade las ordenes de la lista de exportaciones al buque.
	}
	
	/**
	 * Inicia la descarga de las ordenes del buque en la terminal dada.
	 * @param buque es el buque sobre el cual realizar la descarga.
	 */
	private void iniciarDescargaOrdenes(Buque buque) {
		List<Orden> ordenes = buque.getOrdenesADescargar(this);
		
		for(Orden o : ordenes) {
			ordenesDeImportacion.add(o);
			o.crearServiciosACobrar();
		}
	}

	/**
	 * Inicia la carga de las ordenes del buque en la terminal dada.
	 * @param buque es el buque sobre el cual realizar la carga.
	 */
	private void iniciarCargaOrdenes(Buque buque) {
		List<Orden> ordenes = ordenesDeExportacion.stream()
												  .filter(o -> o.getViaje().equals(buque.getViajeActual()))
												  .toList();
		buque.cargarOrdenes(ordenes);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Genera los reportes de las importaciones y exportaciones realizadas con el buque dado.
	 * @param buque es el buque que se toma de referencia para realizar los reportes.
	 */
	private void generarReportes(Buque buque) {
		// Genera reportes de importación
		List<Orden> ordenesImp = this.ordenesDelViaje(ordenesDeImportacion, buque);
		Map<String, Reporte> reportesImportaciones = generadorReportes.generarReportesConImportaciones(buque, ordenesImp);
		
		// Genera reportes de exportación
		List<Orden> ordenesExp = this.ordenesDelViaje(ordenesDeExportacion, buque);
		List<Reporte> reportesCompletos = generadorReportes.finalizarReportesConExportaciones(reportesImportaciones, ordenesExp);
		
		// Añadir los reportes generados
		reportesGenerados.addAll(reportesCompletos);
	}
	
	/**
	 * Describe las ordenes del viaje del buque dado que coincidan con las ordenes dadas.
	 * @param ordenes son las ordenes que se toman de referencia.
	 * @param buque es el buque a tomar de referencia el viaje.
	 */
	private List<Orden> ordenesDelViaje(List<Orden> ordenes, Buque buque) {
		List<Orden> aDevolver = ordenes.stream()
									   .filter(o -> buque.getOrdenes().contains(o))
									   .toList();
		return aDevolver;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Finaliza los trabajos de la terminal en el buque dado, terminando de cargar y descargar ordenes del mismo.
	 * @param buque es el buque en el cual finalizar los trabajos
	 */
	private void finalizarTrabajos(Buque buque) {
		List<Orden> ordenesCargadas = ordenesDeExportacion.stream()
				  										  .filter(o -> o.getViaje().equals(buque.getViajeActual()))
				  										  .toList();
		List<Orden> ordenesDescargadas = buque.getOrdenesADescargar(this);
		
		buque.finalizarDescargaDeOrdenes(ordenesDescargadas); // Elimina las ordenes descargadas en la terminal que estaban en el buque.
		this.finalizarCargaDeOrdenes(ordenesCargadas); // Elimina las ordenes cargadas en el buque de la terminal.
		buque.finalizarTrabajos(); // Esto debería pasar el buque a estado Departing.
	}
	
	/**
	 * Finaliza la carga de ordenes, donde las ordenes dadas son las ordenes a eliminar de la terminal porque fueron cargadas.
	 * @param ordenesCargadas son las ordenes que anteriormente fueron cargadas y deben eliminarse de la terminal.
	 */
	private void finalizarCargaDeOrdenes(List<Orden> ordenesCargadas) {
		for(Orden o : ordenesCargadas) {
			this.ordenesDeExportacion.remove(o);
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
	 * Registra el circuito marítimo dado en la Terminal Portuaria. Este debe incluir a la terminal.
	 * @param circuitoMaritimo es el circuito marítimo a registrar en la Terminal Portuaria. Debe incluir a la terminal.
	 */
	public void registrarCircuitoMaritimo(CircuitoMaritimo circuitoMaritimo) {
		this.validarQueIncluyaTerminal(circuitoMaritimo);
		this.circuitosMaritimosRegistrados.add(circuitoMaritimo);
	}
	
	/**
	 * Valida que el circuito incluya a esta terminal.
	 * @param circuitoMaritimo es el circuito maritimo que se valida.
	 */
	private void validarQueIncluyaTerminal(CircuitoMaritimo circuitoMaritimo) {
		if(!circuitoMaritimo.incluyeA(this)) {
			throw new RuntimeException("El circuito marítimo debe incluir a la terminal para poder ser registrado.");
		}
	}
	
	//Creado únicamente para poder mockear la dependencia y llevar a cabo el testing de la clase.
	public void setBuscadorDeViaje(BuscadorDeViaje buscador) {
		this.buscadorDeViaje = buscador;
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
		return new Orden(camion, chofer, container, viaje, shipper);
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

	/**
	 * 
	 * @param 
	 */
	public void setBuscadorDeCircuito(BuscadorDeCircuito buscador) {
		this.buscadorDeCircuito = buscador;
	}
	
	/**
	 * 
	 * @param 
	 */
	public CircuitoMaritimo buscarCircuito(TerminalPortuaria destino) {
		List<CircuitoMaritimo> cs = new ArrayList<CircuitoMaritimo>();
		this.navierasRegistradas.stream().forEach((n) -> cs.addAll(n.circuitosQueUnan(this, destino)));
		
		return buscadorDeCircuito.buscarMejorCircuito(cs, this, destino);
	}
	
	/**
	 * 
	 * @param 
	 */
	public List<Viaje> buscarViaje(Condicion condicion) {
		
		List<Viaje> vs = new ArrayList<Viaje>();
		this.navierasRegistradas.stream().forEach((n) -> vs.addAll(n.viajesQueIncluyenOrigen(this)));
		//Obtenemos la lista de Viajes en los que esta terminal es puerto origen en alguno de los tramos, lo que
		//significa que se puede unir con alguna otra terminal B.
		
		return buscadorDeViaje.buscarViaje(condicion, vs);
		
	}
	
	/**
	 * 
	 * @param 
	 */
	public Duration tiempoEntre(TerminalPortuaria terminalPortuaria, Naviera naviera) {
		return null;
		
	}
	
	/**
	 * 
	 * @param 
	 */
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
	
	
	
	
	/**
	 * 
	 * @param 
	 */
	@Override
	public void adscribirObservado(Buque buque) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @param 
	 */
	public void notificarConsignee(Viaje viajeActual) {
		// TIENE QUE HACERSE UNA SOLA VEZ
		
	}

	/**
	 * 
	 * @param 
	 */
	public void notificarArribo(Buque miBuque) {
		List <Orden> ordenesAvisar = this.ordenesMail(miBuque.getViajeActual());
		ordenesAvisar.stream().forEach(orden -> this.enviarMailLlegada(orden));
	}
	
	public List<Orden> ordenesMail(Viaje viaje){
		 return ordenesDeImportacion.stream()
	            .filter(orden -> orden.getViaje() == viaje)
	            .collect(Collectors.toList());
	}
    
	
	public void enviarMailLlegada(Orden orden) {
		String cuerpoMail = "Estimado/a " + orden.getConsignee().nombreCliente() + ",\n\n" +
			    "Le informamos que su carga asociada al contenedor " + orden.getCarga().getIdConnteiner() +
			    " está próxima a llegar a destino.\n\n" +
			    "Por favor, manténgase atento a las próximas actualizaciones.\n\n" +
			    "Saludos cordiales,\n" +
			    "Departamento de Logística\n" +
			    "Puerto Internacional de Buenos Aires";
		
		orden.getConsignee().recibirMail(cuerpoMail);
	}
	/**
	 * 
	 * @param 
	 */
	public void notificarSalidaTerminal(Buque miBuque) {
		System.out.println("MANDA LOS MAILS A LOS SHIPPERS");
		
	}

	/**
	 * 
	 * @param 
	 */
	public double limiteHorasAlmacenaje() {
		// TODO Auto-generated method stub
		return 24;
	}
}