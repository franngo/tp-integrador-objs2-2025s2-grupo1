package ar.edu.unq.po2.buscador_de_circuito;

import java.util.Comparator;
import java.util.List;

import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;

public class BuscadorPorCantidadTerminales implements BuscadorDeCircuito {
	
	public CircuitoMaritimo buscarCircuito(List<CircuitoMaritimo> circuitos) {
		return circuitos.stream().min(Comparator.comparing(CircuitoMaritimo::cantidadDeTerminales)).get();
	}

}
