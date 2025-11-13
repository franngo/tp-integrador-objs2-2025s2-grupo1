package ar.edu.unq.po2.terminal_portuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buscador_de_circuito.BuscadorDeCircuito;
import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.container.Reefer;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.empresa_transportista.EmpresaTransportista;
import ar.edu.unq.po2.naviera.Naviera;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.viaje.Viaje;

/**
* Definen los tests unitarios de la clase TerminalPortuaria.
* @author Benjamin Maldonado.
*/

class TerminalPortuariaTest {
	TerminalPortuaria terminalGestionada;
	EmpresaTransportista andreani;
	CircuitoMaritimo circuitoMV;
	CircuitoMaritimo circuitoBA;
	Buque buqueMV;
	Buque buqueBA;
	Viaje viajeMV;
	Viaje viajeBA;
	Cliente shipperMV;
	Cliente shipperBA;
	Cliente consigneeMV;
	Cliente consigneeBA;
	Container containerMV;
	Container containerBA;
	Chofer choferMV;
	Chofer choferBA;
	Camion camionMV;
	Camion camionBA;
	Orden ordenMV;	
	Orden ordenBA;	
	
	@BeforeEach
	public void setUp() {
		// Inicializacion de la Terminal Gestionada junto a la empresa transportista
		this.terminalGestionada = new TerminalPortuaria(new Coordenada(-34.6412, -58.3439));
		
		this.andreani = new EmpresaTransportista();
		Camion scaniaR580 = new Camion("Scania R580", "AE471WD");
		Camion volvoFH460 = new Camion("Volvo FH460", "NRG113");
		Chofer carlos = new Chofer("Carlos Romero", "25.755.002");
		Chofer jose   = new Chofer("Jose Fernandez", "38.091.105");
		andreani.añadirCamion(scaniaR580);
		andreani.añadirCamion(volvoFH460);
		andreani.añadirChofer(carlos);
		andreani.añadirChofer(jose);
		
		// Orden Montevideo
		this.circuitoMV = mock(CircuitoMaritimo.class);
		this.buqueMV = mock(Buque.class);
		this.viajeMV = new Viaje(LocalDateTime.now().plusDays(2), circuitoMV, buqueMV);
		this.shipperMV = new Cliente("Alberto Gonzalez");
		this.consigneeMV = new Cliente("Ricardo Fort");
		this.containerMV = new Reefer(consigneeMV, 1000, 4750, 2950, 15000, 20);
		this.choferMV = andreani.contratarChofer();
		this.camionMV = andreani.contratarCamion();
		this.ordenMV = new Orden(camionMV, choferMV, containerMV, viajeMV, shipperMV);
		
		camionMV.cambiarOrdenActualPor(ordenMV);
		choferMV.cambiarEstaDisponiblePor(false);

		// Orden Buenos Aires
		this.circuitoBA = mock(CircuitoMaritimo.class);
		this.buqueBA = mock(Buque.class);
		this.viajeBA = new Viaje(LocalDateTime.now().plusHours(2), circuitoBA, buqueBA);
		this.shipperBA = new Cliente("Carlos Rodriguez");
		this.consigneeBA = new Cliente("Roberto Paniagua");
		this.choferBA = andreani.contratarChofer();
		this.camionBA = andreani.contratarCamion();
		this.containerBA = new Reefer(consigneeBA, 1100, 4500, 3050, 25000, 15);
		this.ordenBA = new Orden(camionBA, choferBA, containerBA, viajeBA, shipperBA);
		
        camionBA.cambiarOrdenActualPor(ordenBA);
        choferBA.cambiarEstaDisponiblePor(false);
        
        // Registro de los consignee y la empresa transportista
        terminalGestionada.registrarCliente(consigneeBA);
        terminalGestionada.registrarCliente(consigneeMV);
        terminalGestionada.registrarCliente(shipperBA);
        terminalGestionada.registrarCliente(shipperMV);
        terminalGestionada.registrarEmpresaTransportista(andreani);
	}
	
	@Test
	public void testFuncionamientoCircuitoDeExportacion() {
		// La orden de MV no se exporta porque el horario no coincide para el ingreso del camión.
		assertThrows(RuntimeException.class, () -> camionMV.transportarExportacionA(terminalGestionada, choferMV));
		
		// La orden de BA se exporta normalmente, porque el horario coincide.
		camionBA.transportarExportacionA(terminalGestionada, choferBA);
	}
	
	@Test
	public void testFuncionamientoCircuitoDeImportacion() {
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
	
	@Test
	public void testFuncionamientoBuscarCircuito() {
		Naviera naviera1 = mock(Naviera.class);
		Naviera naviera2 = mock(Naviera.class);
		Naviera naviera3 = mock(Naviera.class);
		
		terminalGestionada.registrarNaviera(naviera1);
		terminalGestionada.registrarNaviera(naviera2);
		terminalGestionada.registrarNaviera(naviera3);
		
		TerminalPortuaria destino = mock(TerminalPortuaria.class);
		
		List<CircuitoMaritimo> cs1 = new ArrayList<CircuitoMaritimo>();
		CircuitoMaritimo c1 = mock(CircuitoMaritimo.class);
		CircuitoMaritimo c2 = mock(CircuitoMaritimo.class);
		cs1.add(c1);
		cs1.add(c2);
		
		List<CircuitoMaritimo> cs2 = new ArrayList<CircuitoMaritimo>();
		CircuitoMaritimo c3 = mock(CircuitoMaritimo.class);
		cs2.add(c3);
		
		
		List<CircuitoMaritimo> cs3 = new ArrayList<CircuitoMaritimo>();
		CircuitoMaritimo c4 = mock(CircuitoMaritimo.class);
		CircuitoMaritimo c5 = mock(CircuitoMaritimo.class);
		cs3.add(c4);
		cs3.add(c5);
		
		when(naviera1.circuitosQueUnan(terminalGestionada, destino)).thenReturn(cs1);
		when(naviera2.circuitosQueUnan(terminalGestionada, destino)).thenReturn(cs2);
		when(naviera3.circuitosQueUnan(terminalGestionada, destino)).thenReturn(cs3);
		
		BuscadorDeCircuito buscador = mock(BuscadorDeCircuito.class);
		
		terminalGestionada.setBuscadorDeCircuito(buscador);
		
		List<CircuitoMaritimo> csDef = new ArrayList<CircuitoMaritimo>();
		csDef.addAll(cs1);
		csDef.addAll(cs2);
		csDef.addAll(cs3);
		
		when(buscador.buscarMejorCircuito(argThat(lista -> lista.containsAll(csDef)), eq(terminalGestionada), eq(destino))).
		thenReturn(c4);
		
		assertEquals(c4, terminalGestionada.buscarCircuito(destino));
	}
}