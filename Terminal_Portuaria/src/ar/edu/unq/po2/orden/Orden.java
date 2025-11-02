package ar.edu.unq.po2.orden;



import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.po2.container.ConcreteVisitorContainer;
import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.servicio.Servicio;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

/*
 * @Autor : Benjamin Maldonado
 * @Autor: Matias Sanchez
 * */
public abstract class Orden {
	
	ConcreteVisitorContainer visitanteContainer;
	Container containerAsociado;
    List <Servicio> serviciosACobrar;	
    
    LocalDate arriboTerminal;
	
	/*
	 * Cuando la Orden llega a la terminal, sea una orden de Exportacion o de importacion
	 * Crea una lista de servicios que se deben cobrar
	 * Este método debe llamarse UNA sola vez
	 * */
	public void serviciciosACobrar() {
       
		List<Servicio> serviciosExtra =  containerAsociado.acceptVisitor(visitanteContainer);
		serviciosACobrar.addAll(serviciosExtra);
	}
	
	 
	
	public  double montoTotalServicios(TerminalPortuaria terminalPortuaria) {
		return serviciosACobrar.stream().
				mapToDouble(servicio -> servicio.costoServicio(terminalPortuaria)).
				sum();
	}
	
	
}