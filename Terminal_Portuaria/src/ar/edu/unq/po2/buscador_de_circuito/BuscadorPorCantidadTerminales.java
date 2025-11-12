package ar.edu.unq.po2.buscador_de_circuito;

import java.util.Comparator;
import java.util.List;

import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class BuscadorPorCantidadTerminales implements BuscadorDeCircuito {
	
	public CircuitoMaritimo buscarMejorCircuito(List<CircuitoMaritimo> circuitos, TerminalPortuaria origen, 
			TerminalPortuaria destino) {
		return circuitos.stream().min(Comparator.comparing(c -> c.cantTerminalesEnTramosDesdeHasta(origen, destino))).get();
	}

}
