package ar.edu.unq.po2.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ar.edu.unq.po2.servicio.*;


/*
 * @Autor: Matias Sanchez: crea una lista de servicios para cada Container
 * */
public class ConcreteVisitorContainer implements VisitorContainer{
 
	
	@Override
	public List<Servicio> serviciosDry(Dry container) {
				
		return this.serviciosTotales(container,new ServicioDesconsolidado(container));
	}

	@Override
	public List<Servicio> serviciosTanque(Tanque container) {
		
		
		return this.serviciosTotales(container,new ServicioRevisionDiaria(container)) ;
	}

	@Override
	public List<Servicio> serviciosReefer(Reefer container) {
		
		
		return this.serviciosTotales(container,new ServicioElectricidad(container));
	}
     
	
	private final List<Servicio> serviciosTotales(Container container, Servicio... adicionales) {
        List<Servicio> servicios = new ArrayList<>();
        
        // Servicios comunes
        servicios.add(new ServicioLavado(container));
        servicios.add(new ServicioPesaje(container));
        servicios.add(new ServicioExcedente(container));
        
        // Servicios específicos
        servicios.addAll(Arrays.asList(adicionales));
        
        return servicios;
    } 
	/*
	public List<Servicio> serviciosEspecificosDry(Dry container) {
		List<Servicio> servicios = new ArrayList<>();
		servicios.add(new ServicioDesconsolidado(container));
		return servicios;
	}*/
	/*
	private final List<Servicio> serviciosTotalesDryCompuesto(IDry containerDry){
		List<Servicio> servicios = new ArrayList<>();
		/*
		 * containersParticulares: todas las hojas del DryCompuesto
		 *
		List<IDry> containersParticulares = containerDry.cargas();
		
		containersParticulares.stream().
		forEach(container ->  this.servicioParticular(
				                   container,
				                   this.porcentajeServicio(containersParticulares),
				                   servicios));
	
        return servicios;
	}
	
	public void servicioParticular(IDry containerHoja, double descuentoAplicable, List<Servicio> servicios) {
		if(containerHoja instanceof DryCompuesto ) {
			List<Servicio> serviciosDryParticular = this.serviciosTotalesDryCompuesto(containerHoja);
			servicios.addAll(serviciosDryParticular);
		
		}
		else {
			servicios.add(new ServicioDesconsolidado((Dry) containerHoja,descuentoAplicable));
		}
	}
	
	public int porcentajeServicio(List<IDry> cargas) {
  		return cargas.size();
	}
    */
}


