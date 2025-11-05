package ar.edu.unq.po2.container;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.container.dry.Dry;

class VisitorContainerTestCase {
   
	ConcreteVisitorContainer miVisitanteContainer; //SUT
	
	Container containerDry;
	Container containerTanque;
	Container containerRefee;
	
	@BeforeEach
	public void setUp() {
		miVisitanteContainer = new ConcreteVisitorContainer();
	    containerDry = spy(Dry.class);
		containerTanque = spy(Tanque.class);
		containerRefee = spy(Reefer.class);
	}
	/*
	 * Verifica que los container aceptaron al visitante
	 * */
	@Test
	void testComportamientoVisitor() {
		//containerDry.acceptVisitor(miVisitanteContainer);
		containerTanque.acceptVisitor(miVisitanteContainer);
		containerTanque.acceptVisitor(miVisitanteContainer);
		
		//verify(containerDry).acceptVisitor(miVisitanteContainer);
		verify(containerTanque).acceptVisitor(miVisitanteContainer);
		verify(containerTanque).acceptVisitor(miVisitanteContainer);
	}

}
