package ar.edu.unq.po2.buscador_de_circuito;

import java.util.Comparator;
import java.util.List;

import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;

public class BuscadorPorPrecio implements BuscadorDeCircuito {
	
	public CircuitoMaritimo buscarMejorCircuito(List<CircuitoMaritimo> circuitos) {
		return circuitos.stream().min(Comparator.comparing(CircuitoMaritimo::precioTotal)).get();
	}

}
