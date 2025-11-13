package ar.edu.unq.po2.orden;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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
import ar.edu.unq.po2.servicio.ServicioLavado;
import ar.edu.unq.po2.viaje.Viaje;

class OrdenTestCase {
	
	Orden miOrden;
	
	Container containerMock;
	Cliente shipper;
	Cliente consignee;
	Camion camionDumb;
	Viaje viajeDumb;
	Chofer choferDumb;

	@BeforeEach
	void setUp() {
		consignee = mock(Cliente.class);
		when(consignee.nombreCliente()).thenReturn("Matias");
		containerMock = new Tanque(consignee, 0, 0, 0, 0);
		
		
		when(containerMock.getDuenioConsignee()).thenReturn(consignee);
		shipper = mock(Cliente.class);
		//consignee = mock(Cliente.class);
		camionDumb = mock(Camion.class);
		choferDumb = mock(Chofer.class);
		viajeDumb = mock(Viaje.class);
		
		miOrden = new Orden(camionDumb, choferDumb, containerMock, viajeDumb, shipper);
		when(containerMock.acceptVisitor(miOrden.visitanteContainer())).thenReturn(List.of(new ServicioLavado(null, null)));
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
	}
	
	
	 /* Solo se puede crear los servicios una vez
	 * */
	/*
	@Test
	void testComprobacionDeServicios() {
		miOrden.crearServiciosACobrar();
		assertFalse(miOrden.getServiciosOrden().isEmpty());
		
		assertThrows(Exception.class, () -> {
			miOrden.crearServiciosACobrar();
		});
	}*/
}