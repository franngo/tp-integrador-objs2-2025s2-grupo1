package ar.edu.unq.po2.buque;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.estadosBuque.EstadoBuque;
import ar.edu.unq.po2.buque.estadosBuque.OutBound;
import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.viaje.Viaje;

class BuqueTestCase {

	
	Buque miBuque;
	TerminalPortuaria terminalDumb;
	TerminalPortuaria proximoDestino;
	EstadoBuque estadoBuque;
	Coordenada coordenadasBuque;
	Coordenada coordenadasTerminal;
	List<Orden> ordenesImportacion;
	List<Orden> ordenesExportacion;
	Viaje viajeDumb;
	@BeforeEach
	void setUp()  {
		proximoDestino = mock(TerminalPortuaria.class);
		 
		terminalDumb= mock(TerminalPortuaria.class);
		coordenadasTerminal = new Coordenada(0d,0d);
		when(terminalDumb.coordenadasTerminal()).thenReturn(coordenadasTerminal);
		
		ordenesImportacion=List.of();
		ordenesExportacion = List.of();
		
		coordenadasBuque = new Coordenada(30d,20d);
		viajeDumb = mock(Viaje.class);
		when(viajeDumb.puertoDestino()).thenReturn(terminalDumb);
		when(viajeDumb.proximoDestino()).thenReturn(proximoDestino);
		
		miBuque = new Buque(coordenadasBuque,ordenesImportacion,ordenesExportacion,"El Perla Negra");
		
	}

	
	@Test
	void elBarcoNoPuedeIniciarViaje() {
		miBuque.enViaje();
		 assertThrows(Exception.class, () -> {
		        miBuque.iniciarViaje(viajeDumb);
		    });
		 
		 miBuque.sinViaje();
		 assertDoesNotThrow(() -> {miBuque.iniciarViaje(viajeDumb);});
		 
	}
	@Test
	void elBarcoIniciaElViaje() throws Exception {
		
		miBuque.iniciarViaje(viajeDumb);
		assertEquals(viajeDumb,miBuque.getViajeActual());
		assertTrue(miBuque.obtenerEstado() instanceof OutBound);
		
	}
	@Test
	void elBarcoSeMuevePorSuEstado() throws Exception {
		
		miBuque.iniciarViaje(viajeDumb);
		miBuque.avanzarHacia(30d, 40d);
		
		assertEquals(30d,miBuque.posicionActual().getLatitud());
		assertEquals(40d,miBuque.posicionActual().getLongitud());
		
	}
	@Test 
	void elBuqueComunicaSusDatos() {
		
		assertEquals("El Perla Negra",miBuque.getNombre());
		assertEquals(List.of(),miBuque.getOrdenesExportacion());
		assertEquals(List.of(),miBuque.getOrdenesImportacion());
	}
	
	@Test
	void elBuqueModificaSuDestino() {
		
		TerminalPortuaria unDestino = mock(TerminalPortuaria.class);
		miBuque.terminalAArribar(unDestino);
		assertEquals(unDestino,miBuque.terminalAArribar());
		
		TerminalPortuaria otroDestino = mock(TerminalPortuaria.class);
		miBuque.terminalAArribar(otroDestino);
		
		assertEquals(otroDestino,miBuque.terminalAArribar());
		
		
	}

}
