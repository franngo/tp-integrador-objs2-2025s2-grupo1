package ar.edu.unq.po2.container;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.dry.Dry;
import ar.edu.unq.po2.container.dry.DryCompuesto;
import ar.edu.unq.po2.container.dry.DryUnico;
import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.servicio.ServicioLavado;
import ar.edu.unq.po2.servicio.ServicioPesaje;

class VisitorContainerTest {

    ConcreteVisitorContainer miVisitanteContainer; //SUT
	
    
	DryCompuesto containerDryCompuesto; // DOC
	DryUnico containerDryUnico;
	Tanque containerTanque; //DOC
	Reefer containerRefeer; //DOC
	
	List<Servicio> serviciosDryUnico;
	List<Servicio> serviciosDryCompuesto;
	List<Servicio> serviciosReefer;
	List<Servicio> serviciosTanque;
	
	@BeforeEach
	public void setUp() {
		miVisitanteContainer = new ConcreteVisitorContainer();
	    containerDryUnico = mock(DryUnico.class);
		containerTanque = mock(Tanque.class);
		containerRefeer = mock(Reefer.class);
		
		serviciosReefer= miVisitanteContainer.serviciosReefer(containerRefeer);
		serviciosTanque = miVisitanteContainer.serviciosTanque(containerTanque);
		//serviciosDryCompuesto = miVisitanteContainer.serviciosDryCompuesto(containerDryCompuesto);
		//serviciosDryUnico = miVisitanteContainer.serviciosDry(containerDryUnico);
		
	}
	/*
	 * Verifica que los container aceptaron al visitante
	 * */
	@Test
	void testComportamientoVisitor() {
		containerDryUnico.acceptVisitor(miVisitanteContainer);
		containerTanque.acceptVisitor(miVisitanteContainer);
		containerRefeer.acceptVisitor(miVisitanteContainer);
		
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
				List.of(serviciosReefer,serviciosTanque);
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
	
}
