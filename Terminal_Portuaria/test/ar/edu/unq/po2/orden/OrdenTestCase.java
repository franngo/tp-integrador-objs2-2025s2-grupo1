package ar.edu.unq.po2.orden;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.camion.Camion;
import ar.edu.unq.po2.chofer.Chofer;
import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.container.Reefer;
import ar.edu.unq.po2.container.Tanque;
import ar.edu.unq.po2.generadorDeReportes.VisitorReporte;
import ar.edu.unq.po2.servicio.ServicioLavado;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

class OrdenTestCase {
	
	Orden miOrden;
	
	Reefer containerMock;
	Cliente shipper;
	Cliente consignee;
	Camion camionDumb;
	Viaje viajeDumb;
	Chofer choferDumb;
	ConcreteVisitorContainer visitante;
	VisitorReporte visitanteReporter;
	TerminalPortuaria terminalDumb;

	@BeforeEach
	void setUp() {
		terminalDumb = mock(TerminalPortuaria.class);
		visitanteReporter = mock(VisitorReporte.class);
		consignee = new Cliente("Matias Sanchez");
		
		
		
		containerMock = new Reefer(consignee, 0, 0, 0, 0, 0);
		
		
		shipper = mock(Cliente.class);
		
		camionDumb = mock(Camion.class);
		choferDumb = mock(Chofer.class);
		LocalDateTime fechaEsperada = LocalDateTime.of(2025, 11, 12, 10, 30);
		LocalDateTime fechaEsperada2 = LocalDateTime.of(2025, 11, 14, 18, 0);
		
		viajeDumb = mock(Viaje.class);
		when(viajeDumb.fechaDeSalida()).thenReturn(fechaEsperada);
		
        when(viajeDumb.fechaDeLlegadaATerminal(any())).thenReturn(fechaEsperada2);
		miOrden = new Orden(camionDumb, choferDumb, containerMock, viajeDumb, shipper);
		//when(viajeDumb.fechaDeSalida()).thenReturn(LocalDate.now());
		
        // Configurar comportamiento del mock
	}
	@Test
	void testDatosOrden() {
		assertEquals(containerMock,miOrden.getCarga());
		assertEquals(camionDumb,miOrden.getCamion());
		assertEquals(choferDumb,miOrden.getChofer());
		assertEquals(viajeDumb,miOrden.getViaje());
		assertEquals(consignee,miOrden.getConsignee());
		assertEquals(shipper,miOrden.getShipper());
		assertTrue(miOrden.visitanteContainer() instanceof ConcreteVisitorContainer);
		assertTrue(miOrden.getServiciosOrden().isEmpty());
		
	}
	
	@Test
	void testCreacionServicios() {
		
		miOrden.crearServiciosACobrar();
		
		assertFalse(miOrden.getServiciosOrden().isEmpty());
		//assertEquals(10,miOrden.getServiciosOrden().size());
	}
	
	
	 /* Solo se puede crear los servicios una vez
	 * */
	
	@Test
	void testComprobacionDeServicios() {
		miOrden.crearServiciosACobrar();
		assertFalse(miOrden.getServiciosOrden().isEmpty());
		
		assertThrows(Exception.class, () -> {
			miOrden.crearServiciosACobrar();
		});
	}
	
	@Test
	void verificarInteraccionConVisitanteTest() {
		miOrden.accept(visitanteReporter);
		verify(visitanteReporter,times(1)).visitOrden(miOrden);
	}
	@Test 
	void verificarInteraccionViaje() {
		assertTrue(miOrden.fechaDeSalida() instanceof LocalDateTime);
		assertTrue(miOrden.fechaDeLlegadaA(terminalDumb) instanceof LocalDateTime);
	}
}