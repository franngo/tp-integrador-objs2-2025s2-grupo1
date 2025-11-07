package ar.edu.unq.po2.container.dry;

import java.util.List;


import ar.edu.unq.po2.container.ConcreteVisitorContainer;

import ar.edu.unq.po2.servicio.Servicio;


public interface IDry {
    
   
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante);
	public String tipoCarga();
	public List<IDry> cargas();
	
}		
	

 


	
	
		
	
   
	



