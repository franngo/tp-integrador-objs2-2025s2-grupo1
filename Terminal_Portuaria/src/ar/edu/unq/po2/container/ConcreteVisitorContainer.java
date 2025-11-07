package ar.edu.unq.po2.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.po2.container.dry.Dry;
import ar.edu.unq.po2.container.dry.DryCompuesto;
import ar.edu.unq.po2.container.dry.DryUnico;
import ar.edu.unq.po2.servicio.*;


/*
 * @Autor: Matias Sanchez: crea una lista de servicios para cada Container
 * */
public class ConcreteVisitorContainer implements VisitorContainer{
 
	
	@Override
	public List<Servicio> serviciosDry(DryUnico container) {
				
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
	public List<Servicio> serviciosDryCompuesto(DryCompuesto container) {
		//TODO implementar
		return this.serviciosTotalesDryCompuesto(container);
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
	
	private final List<Servicio> serviciosTotalesDryCompuesto(Dry containerDry){
		List<Servicio> servicios = new ArrayList<>();
		/*
		 * containersParticulares: todas las hojas del DryCompuesto
		 * */
		List<Dry> containersParticulares = containerDry.cargas();
		
		containersParticulares.stream().
		forEach(container ->  this.servicioParticular(
				                   container,
				                   this.porcentajeServicio(containersParticulares),
				                   servicios));
	
        return servicios;
	}
	
	public void servicioParticular(Dry containerHoja, double descuentoAplicable, List<Servicio> servicios) {
		if(containerHoja instanceof DryCompuesto ) {
			List<Servicio> serviciosDryParticular = this.serviciosTotalesDryCompuesto(containerHoja);
			servicios.addAll(serviciosDryParticular);
		
		}
		else {
			servicios.add(new ServicioDesconsolidado((DryUnico) containerHoja,descuentoAplicable));
		}
	}
	
	public int porcentajeServicio(List<Dry> cargas) {
  		return cargas.size();
	}

}


