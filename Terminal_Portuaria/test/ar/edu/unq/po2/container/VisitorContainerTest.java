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

import ar.edu.unq.po2.container.bls.CargaBL;

import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.servicio.ServicioDesconsolidado;
import ar.edu.unq.po2.servicio.ServicioElectricidad;
import ar.edu.unq.po2.servicio.ServicioLavado;
import ar.edu.unq.po2.servicio.ServicioPesaje;
import ar.edu.unq.po2.servicio.ServicioRevisionDiaria;

class VisitorContainerTest {

    ConcreteVisitorContainer miVisitanteContainer; //SUT
	
    
	//DryCompuesto containerDryCompuesto; // DOC
	Dry containerDry;
	Tanque containerTanque; //DOC
	Reefer containerRefeer; //DOC
	
	//carga de Dry's mockeados para el dryCompuesto
	CargaBL cargaDryCompuesto;
	
	//Servicios que crea el VisitanteConcretoContainer
	List<Servicio> serviciosDry;
	//List<Servicio> serviciosDryCompuesto;
	List<Servicio> serviciosReefer;
	List<Servicio> serviciosTanque;
	
	@BeforeEach
	public void setUp() {
		miVisitanteContainer = new ConcreteVisitorContainer();
	    containerDry = mock(Dry.class);
	   // cargaDryCompuesto = mock(DryCompuesto.class);
		containerTanque = mock(Tanque.class);
		containerRefeer = mock(Reefer.class);
		
	//Simulo que mi container mockeado tiene una carga	
	 
	  
		when(containerDry.carga()).
		 thenReturn(cargaDryCompuesto);
		
		serviciosReefer= miVisitanteContainer.serviciosReefer(containerRefeer);
		serviciosTanque = miVisitanteContainer.serviciosTanque(containerTanque);
		
		serviciosDry = miVisitanteContainer.serviciosDry(containerDry);
		
	}
	/*
	 * Verifica que los container aceptaron al visitante
	 * */
	@Test
	void testComportamientoVisitor() {
		containerDry.acceptVisitor(miVisitanteContainer);
		containerTanque.acceptVisitor(miVisitanteContainer);
		containerRefeer.acceptVisitor(miVisitanteContainer);
		
		
		
		
		verify(containerDry,times(1)).acceptVisitor(miVisitanteContainer);
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
				List.of(serviciosReefer,serviciosTanque,serviciosDry);
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
				serviciosDry.stream().anyMatch(s -> s instanceof ServicioDesconsolidado),
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
	
	
	
}
