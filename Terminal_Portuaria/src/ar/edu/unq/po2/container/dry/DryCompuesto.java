package ar.edu.unq.po2.container.dry;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.servicio.Servicio;

/*
public class DryCompuesto extends Container{
	
	
    public DryCompuesto(List<Container> containers) {
		super(null, 0, 0, 0, 0);
		// TODO Auto-generated constructor stub
		
		cargasAgrupadas = new ArrayList<IDry>();
		cargasAgrupadas.addAll(containers);
	}



	List <IDry> cargasAgrupadas;
    
	
	
	
	@Override
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante) {
		// TODO Auto-generated method stub
		return visitante.serviciosDryCompuesto(this);
	}
	// PENSAR


    
	public List<IDry> cargas() {
		// TODO Auto-generated method stub
		return cargasAgrupadas;
	}


	
	public String tipoCarga() {
		// TODO Auto-generated method stub
		return "Dry compuesto por "+ this.cargas().size() +" containers Dry";
	}




}
*/