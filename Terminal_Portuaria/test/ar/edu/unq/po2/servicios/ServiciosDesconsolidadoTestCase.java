package ar.edu.unq.po2.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.container.Dry;
import ar.edu.unq.po2.container.bls.CargaBL;
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
	CargaBL cargas;
	
	
	@BeforeEach
	void setUp(){
		dumbDry = mock(Dry.class);
		
		
		
		when(dumbDry.carga()).thenReturn(cargas);
		
		
		miServConDryUnico= new ServicioDesconsolidado(dumbDry);
		
	}

	

}
