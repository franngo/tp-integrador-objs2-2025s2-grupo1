package ar.edu.unq.po2.container.dry;

import java.util.List;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.servicio.Servicio;


public abstract class Dry extends Container {
    
    
	public Dry(Cliente cliente, double ancho, double largo, double altura, double peso) {
		super(cliente, ancho, largo, altura, peso);
		// TODO Auto-generated constructor stub
	}

	

	
	


	@Override
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante) {
		// TODO Auto-generated method stub
		return visitante.serviciosDry(this);
	}

 


	
	
		
	
   
	


}
