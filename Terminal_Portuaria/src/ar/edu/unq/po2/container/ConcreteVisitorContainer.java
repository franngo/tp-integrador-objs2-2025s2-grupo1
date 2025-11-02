package ar.edu.unq.po2.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.po2.container.dry.Dry;
import ar.edu.unq.po2.servicio.*;


/*
 * @Autor: Matias Sanchez: crea una lista de servicios para cada Container
 * */
public class ConcreteVisitorContainer implements VisitorContainer{
 
	
	@Override
	public List<Servicio> serviciosDry(Dry container) {
				
		return this.serviciosTotales(container);
	}

	@Override
	public List<Servicio> serviciosTanque(Tanque container) {
		
		
		return this.serviciosTotales(container,new ServicioRevisionDiaria(container)) ;
	}

	@Override
	public List<Servicio> serviciosReefer(Reefer container) {
		
		
		return this.serviciosTotales(container,new ServicioElectricidad(container));
	}

	@Override
	public List<Servicio> servicioDryCompuesto(Dry container) {
		
		return null;
	}
	
	
	
	private final List<Servicio> serviciosTotales(Container container, Servicio... adicionales) {
        List<Servicio> servicios = new ArrayList<>();
        
        // Servicios comunes
        servicios.add(new ServicioLavado(container));
        servicios.add(new ServicioPesaje(container));
        
        // Servicios específicos
        servicios.addAll(Arrays.asList(adicionales));
        
        return servicios;
    } 

}


