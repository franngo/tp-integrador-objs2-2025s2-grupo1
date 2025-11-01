package ar.edu.unq.po2.container;

import java.util.List;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.servicio.Servicio;


public class Dry extends Container {
    

	public Dry(Cliente duenio, double ancho, double largo, double altura, double peso) {
		super(duenio, ancho, largo, altura, peso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante) {
		// TODO Auto-generated method stub
		return null;
	}
   
	


}
