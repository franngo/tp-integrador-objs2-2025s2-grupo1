package ar.edu.unq.po2.buscador_de_viaje;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.po2.viaje.Viaje;

public class Or extends Conector {
	
	@Override
	public List<Viaje> losQueCumplen(List<Viaje> viajes) {
		
		Set<Viaje> viajes1 = this.getSubcondicionIzquierda().losQueCumplen(viajes).stream().collect(Collectors.toSet());
		Set<Viaje> viajes2 = this.getSubcondicionDerecha().losQueCumplen(viajes).stream().collect(Collectors.toSet());
		viajes1.addAll(viajes2); //Operación de conjuntos UNIÓN
		
		return viajes1.stream().collect(Collectors.toList());
		
	}
	
}
