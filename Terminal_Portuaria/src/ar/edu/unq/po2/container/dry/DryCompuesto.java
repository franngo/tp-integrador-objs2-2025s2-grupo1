package ar.edu.unq.po2.container.dry;

import java.util.ArrayList;
import java.util.List;

public class DryCompuesto extends Dry{
	
	List <Dry> cargasAgrupadas = new ArrayList<Dry>();

	public DryCompuesto( double ancho, double largo, double altura, double peso,List<Dry> cargasBLs) {
		super( ancho, largo, altura, peso);
		this.cargasAgrupadas.addAll(cargasBLs);
		// TODO Auto-generated constructor stub
	}
	
	// PENSAR

}
