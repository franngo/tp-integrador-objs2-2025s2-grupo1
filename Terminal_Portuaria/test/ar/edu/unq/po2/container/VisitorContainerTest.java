package ar.edu.unq.po2.container;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.dry.Dry;
import ar.edu.unq.po2.container.dry.DryCompuesto;
import ar.edu.unq.po2.container.dry.DryUnico;
import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.servicio.ServicioDesconsolidado;
import ar.edu.unq.po2.servicio.ServicioElectricidad;
import ar.edu.unq.po2.servicio.ServicioLavado;
import ar.edu.unq.po2.servicio.ServicioPesaje;
import ar.edu.unq.po2.servicio.ServicioRevisionDiaria;

class VisitorContainerTest {

    ConcreteVisitorContainer miVisitanteContainer; //SUT
	
    
	DryCompuesto containerDryCompuesto; // DOC
	DryUnico containerDryUnico;
	Tanque containerTanque; //DOC
	Reefer containerRefeer; //DOC
	
	//carga de Dry's mockeados para el dryCompuesto
	List<Dry> cargaDryCompuesto;
	
	//Servicios que crea el VisitanteConcretoContainer
	List<Servicio> serviciosDryUnico;
	List<Servicio> serviciosDryCompuesto;
	List<Servicio> serviciosReefer;
	List<Servicio> serviciosTanque;
	
	@BeforeEach
	public void setUp() {
		miVisitanteContainer = new ConcreteVisitorContainer();
	    containerDryUnico = mock(DryUnico.class);
	    containerDryCompuesto = mock(DryCompuesto.class);
		containerTanque = mock(Tanque.class);
		containerRefeer = mock(Reefer.class);
		
	//Simulo que mi container mockeado tiene una carga	
	  cargaDryCompuesto = List.of(containerDryUnico);
	  
		when(containerDryCompuesto.cargas()).
		 thenReturn(cargaDryCompuesto);
		
		serviciosReefer= miVisitanteContainer.serviciosReefer(containerRefeer);
		serviciosTanque = miVisitanteContainer.serviciosTanque(containerTanque);
		serviciosDryCompuesto = miVisitanteContainer.serviciosDryCompuesto(containerDryCompuesto);
		serviciosDryUnico = miVisitanteContainer.serviciosDry(containerDryUnico);
		
	}
	/*
	 * Verifica que los container aceptaron al visitante
	 * */
	@Test
	void testComportamientoVisitor() {
		containerDryUnico.acceptVisitor(miVisitanteContainer);
		containerTanque.acceptVisitor(miVisitanteContainer);
		containerRefeer.acceptVisitor(miVisitanteContainer);
		containerDryCompuesto.acceptVisitor(miVisitanteContainer);
		
		
		verify(containerDryCompuesto).acceptVisitor(miVisitanteContainer);
		verify(containerDryUnico).acceptVisitor(miVisitanteContainer);
		verify(containerTanque,times(1)).acceptVisitor(miVisitanteContainer);
		verify(containerRefeer,times(1)).acceptVisitor(miVisitanteContainer);
	}
	
	/*
	 * Verifica que se creen las instancias de servicios comunes en todas las listas
	 * de servicios que crea un visitante
	 * */
	@Test
	public void testServiciosComunes() {
		List<List<Servicio>> todasLasListas = 
				List.of(serviciosReefer,serviciosTanque,serviciosDryUnico);
		todasLasListas.forEach(lista -> {
		    assertTrue(
		        lista.stream().anyMatch(s -> s instanceof ServicioLavado),
		        "Debe existir al menos un ServicioLavado en la lista"
		    );

		    assertTrue(
		        lista.stream().anyMatch(s -> s instanceof ServicioPesaje),
		        "Debe existir al menos un ServicioPesaje en la lista"
		    );
		});
	}
	
	
	/*
	 * Verifica que se creen las instancias de servicios extra de 
	 * un Container Dry Compuesto
	 * */
	@Test
	public void creacionServiciosExtraDryCompuesto() {
		assertTrue(
				serviciosDryCompuesto.stream().anyMatch(s -> s instanceof ServicioDesconsolidado),
		        "Debe existir al menos un ServicioDesconsolidado en la lista"
		    );
	}
	
	
	/*
	 * Verifica que se creen las instancias de servicios extra de 
	 * un Container Tanque
	 * */
	@Test
	public void creacionServiciosExtraTanque() {
		assertTrue(
				serviciosTanque.stream().anyMatch(s -> s instanceof ServicioRevisionDiaria),
		        "Debe existir al menos un ServicioRevisionDiaria en la lista"
		    );
	}
	
	
	/*
	 * Verifica que se creen las instancias de servicios extra de 
	 * un Container Reefer
	 * */
	@Test
	public void creacionServiciosExtraReefer() {
		assertTrue(
				serviciosReefer.stream().anyMatch(s -> s instanceof ServicioElectricidad),
		        "Debe existir al menos un ServicioElectricidad en la lista"
		    );
	}
	
	/*
	 * Verifica que sea valido el porcentaje a aplicar si es container Compuesto
	 * */
	@Test
	public void valoresValidosPorcentajes() {
	   assertEquals(1, miVisitanteContainer.porcentajeServicio(cargaDryCompuesto));
	}
	
	
}
