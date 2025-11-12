package ar.edu.unq.po2.terminal_portuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.cliente.Consignee;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.container.Reefer;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.naviera.Naviera;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.orden.OrdenDeExportacion;
import ar.edu.unq.po2.viaje.Viaje;

/**
* Definen los tests unitarios de la clase TerminalPortuaria.
* @author Benjamin Maldonado.
*/

class TerminalPortuariaTest {
	TerminalPortuaria terminalGestionada;
	
	@BeforeEach
	public void setUp() {
		// Inicializacion de la Terminal Gestionada junto a la empresa transportista
		this.terminalGestionada = new TerminalPortuaria(new Coordenada(-34.6412, -58.3439));
		
		EmpresaTransportista andreani = new EmpresaTransportista();
		Camion scaniaR580 = new Camion("Scania R580", "AE471WD");
		Camion volvoFH460 = new Camion("Volvo FH460", "NRG113");
		Chofer carlos = new Chofer("Carlos Romero", "25.755.002");
		Chofer jose   = new Chofer("Jose Fernandez", "38.091.105");
		andreani.añadirCamion(scaniaR580);
		andreani.añadirCamion(volvoFH460);
		andreani.añadirChofer(carlos);
		andreani.añadirChofer(jose);
		
		// Falta poner una naviera aca.
		
		// Orden Montevideo
		CircuitoMaritimo circuitoMV = mock(CircuitoMaritimo.class);
		Buque buqueMSC = mock(Buque.class);
		Viaje viajeMV = new Viaje(LocalDateTime.now().plusDays(2), circuitoMV, buqueMSC);
		Cliente consigneeMV = new Consignee("Ricardo Fort");
		Container containerMV = new Reefer(consigneeMV, 1000, 4750, 2950, 15000, 20);
		Chofer choferMV = andreani.contratarChofer();
		Camion camionMV = andreani.contratarCamion();
		Orden ordenMV = new OrdenDeExportacion(camionMV, choferMV, containerMV, viajeMV);
		
		camionMV.cambiarOrdenActualPor(ordenMV);
		choferMV.cambiarEstaDisponiblePor(false);

		// Orden Buenos Aires
		CircuitoMaritimo circuitoBA = mock(CircuitoMaritimo.class);
		Buque buqueEvergreen = mock(Buque.class);
		Viaje viajeBA = new Viaje(LocalDateTime.now().plusHours(2), circuitoBA, buqueEvergreen);
		Cliente consigneeBA = new Consignee("Roberto Paniagua");
		Chofer choferBA = andreani.contratarChofer();
		Camion camionBA = andreani.contratarCamion();
		Container containerBA = new Reefer(consigneeBA, 1100, 4500, 3050, 25000, 15);
        Orden ordenBA = new OrdenDeExportacion(camionBA, choferBA, containerBA, viajeBA);
		
        camionBA.cambiarOrdenActualPor(ordenBA);
        choferBA.cambiarEstaDisponiblePor(false);
        
        // Registro de los consignee y la empresa transportista
        terminalGestionada.registrarCliente(consigneeBA);
        terminalGestionada.registrarCliente(consigneeMV);
        terminalGestionada.registrarEmpresaTransportista(andreani);
	}
	
	@Test
	public void testFuncionamientoCircuitoDeExportacionExitoso() {
		/* Viaje viajeElegido = terminalGestionada.buscarViaje();
		Camion camionContratado = andreani.contratarCamion();
		Chofer choferContratado = andreani.contratarChofer();
		Container container = new Dry(null, 0, 0, 0, 0);
		
		Orden orden = terminalGestionada.generarOrden(camionContratado, choferContratado, container, viajeElegido); */
	}
	
	@Test
	public void testFuncionamientoCircuitoDeExportacionFallido() {
		/* Viaje viajeElegido = terminalGestionada.buscarViaje();
		Camion camionContratado = andreani.contratarCamion();
		Chofer choferContratado = andreani.contratarChofer();
		Container container = new Dry(null, 0, 0, 0, 0);
		
		Orden orden = terminalGestionada.generarOrden(camionContratado, choferContratado, container, viajeElegido); */
	}
	
	@Test
	public void testFuncionamientoCircuitoDeImportacionExitoso() {
		/* Viaje viajeElegido = terminalGestionada.buscarViaje();
		Camion camionContratado = andreani.contratarCamion();
		Chofer choferContratado = andreani.contratarChofer();
		Container container = new Dry(null, 0, 0, 0, 0);
		
		Orden orden = terminalGestionada.generarOrden(camionContratado, choferContratado, container, viajeElegido); */
	}
	
	@Test
	public void testFuncionamientoCircuitoDeImportacionFallido() {
		/* Viaje viajeElegido = terminalGestionada.buscarViaje();
		Camion camionContratado = andreani.contratarCamion();
		Chofer choferContratado = andreani.contratarChofer();
		Container container = new Dry(null, 0, 0, 0, 0);
		
		Orden orden = terminalGestionada.generarOrden(camionContratado, choferContratado, container, viajeElegido); */
	}
	
	@Test
	public void testFuncionamientoRegistrarEnLaTerminal() {
		// Setup
		CircuitoMaritimo circuitoMaritimo = mock(CircuitoMaritimo.class);
		Cliente cliente = mock(Cliente.class);
		EmpresaTransportista empresaTransportista = mock(EmpresaTransportista.class);
		Naviera naviera = mock(Naviera.class);
		
		when(circuitoMaritimo.incluyeA(terminalGestionada)).thenReturn(true);
		
		// Exercise
		terminalGestionada.registrarCircuitoMaritimo(circuitoMaritimo);
		terminalGestionada.registrarCliente(cliente);
		terminalGestionada.registrarEmpresaTransportista(empresaTransportista);
		terminalGestionada.registrarNaviera(naviera);
	}
}