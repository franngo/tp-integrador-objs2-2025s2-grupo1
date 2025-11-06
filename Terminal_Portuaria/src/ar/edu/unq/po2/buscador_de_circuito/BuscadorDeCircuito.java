package ar.edu.unq.po2.buscador_de_circuito;

import java.util.List;

import ar.edu.unq.po2.circuito_maritimo.CircuitoMaritimo;

public interface BuscadorDeCircuito {
	
	/*
	 * Ya se le pasa directamente la lista de circuitos entre la terminal gestionada y la otra terminal de la consulta.
	 * No hace falta realizar ese filtro sobre la lista de circuitos acá.
	 */
	public CircuitoMaritimo buscarMejorCircuito(List<CircuitoMaritimo> circuitos);
	
}
