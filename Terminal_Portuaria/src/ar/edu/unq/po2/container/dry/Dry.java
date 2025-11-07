package ar.edu.unq.po2.container.dry;

import java.util.List;


import ar.edu.unq.po2.container.ConcreteVisitorContainer;

import ar.edu.unq.po2.servicio.Servicio;


public interface Dry {
    
   
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante);
	public String tipoCarga();
}		
	

 


	
	
		
	
   
	



