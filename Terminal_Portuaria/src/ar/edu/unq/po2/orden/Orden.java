package ar.edu.unq.po2.orden;

import java.util.List;

import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;

import ar.edu.unq.po2.servicio.Servicio;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.generadorDeReportes.VisitorReporte;


/*
 * @Autor : Benjamin Maldonado
 * @Autor: Matias Sanchez
 * */
public abstract class Orden {
	
	ConcreteVisitorContainer visitanteContainer;
	Container containerAsociado;
    List <Servicio> serviciosACobrar;	
	private Container carga;
    
    public  List <Servicio> serviciosOrden(){
    	return serviciosACobrar;
    }
	
	/*
	 * Cuando la Orden llega a la terminal, sea una orden de Exportacion o de importacion
	 * Crea una lista de servicios que se deben cobrar
	 * Este método debe llamarse UNA sola vez
	 * */
	public void crearServiciosACobrar() {
		
		
      	List<Servicio> serviciosACobrar =  containerAsociado.acceptVisitor(visitanteContainer);
		serviciosACobrar.addAll(serviciosACobrar);
		
	}

	public Container getCarga() {
		return this.carga;
	}
	
	abstract public String accept(VisitorReporte visitor);
		
}