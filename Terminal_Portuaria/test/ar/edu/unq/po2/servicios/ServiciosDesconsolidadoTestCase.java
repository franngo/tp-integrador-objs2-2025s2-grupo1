package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Dry;
import ar.edu.unq.po2.container.dry.IDry;
import ar.edu.unq.po2.container.dry.DryCompuesto;
import ar.edu.unq.po2.servicio.ServicioDesconsolidado;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/*
 * 
 * Autor: Matias Sanchez 
 * */
class ServiciosDesconsolidadoTestCase {
     
	TerminalPortuaria terminalDumb = mock(TerminalPortuaria.class);
	ServicioDesconsolidado miServConDryUnico;
	ServicioDesconsolidado miServConDryCompuesto;
	Dry dumbDry;
	Dry dumbDry2;
	Dry dumbDry3;
	Dry dumbDry4;
	DryCompuesto dumbCompuesto;
	
	List<IDry> cargas;
	
	
	@BeforeEach
	void setUp(){
		dumbDry = mock(Dry.class);
		
		cargas = List.of(dumbDry,dumbDry2,dumbDry3,dumbDry4);
		dumbCompuesto = mock(DryCompuesto.class);
		when(dumbCompuesto.cargas()).thenReturn(cargas);
		
		
		miServConDryUnico= new ServicioDesconsolidado(dumbDry,1);
		miServConDryCompuesto = new ServicioDesconsolidado(dumbCompuesto,4);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
