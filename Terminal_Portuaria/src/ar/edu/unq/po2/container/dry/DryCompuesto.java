package ar.edu.unq.po2.container.dry;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.servicio.Servicio;

public class DryCompuesto extends Dry{
	
	List <Dry> cargasAgrupadas = new ArrayList<Dry>();

	public DryCompuesto( double ancho, double largo, double altura, double peso,List<Dry> cargasBLs) {
		super( ancho, largo, altura, peso);
		this.cargasAgrupadas.addAll(cargasBLs);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante) {
		// TODO Auto-generated method stub
		return visitante.serviciosDryCompuesto(this);
	}
	// PENSAR


	@Override
	public List<Dry> cargas() {
		// TODO Auto-generated method stub
		return cargasAgrupadas;
	}




}
