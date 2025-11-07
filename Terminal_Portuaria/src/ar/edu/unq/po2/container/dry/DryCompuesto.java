package ar.edu.unq.po2.container.dry;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.servicio.Servicio;

public class DryCompuesto implements Dry{
	
	
	


	public DryCompuesto(List<Dry> cargas) {
		cargasAgrupadas = new ArrayList<Dry>();
		cargasAgrupadas.addAll(cargas);
		
	}

    List <Dry> cargasAgrupadas;
    
	
	
	
	@Override
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante) {
		// TODO Auto-generated method stub
		return visitante.serviciosDryCompuesto(this);
	}
	// PENSAR


    
	public List<Dry> cargas() {
		// TODO Auto-generated method stub
		return cargasAgrupadas;
	}


	
	public String tipoCarga() {
		// TODO Auto-generated method stub
		return "Dry compuesto por "+ this.cargas().size() +" containers Dry";
	}




}
