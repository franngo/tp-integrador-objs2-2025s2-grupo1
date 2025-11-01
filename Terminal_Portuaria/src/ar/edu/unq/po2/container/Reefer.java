package ar.edu.unq.po2.container;

import java.util.List;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.servicio.*;

public class Reefer extends Container{

	public Reefer(Cliente duenio, double ancho, double largo, double altura, double peso) {
		super(duenio, ancho, largo, altura, peso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante) {
		// TODO Auto-generated method stub
		return visitante.serviciosReefer(this);
	}

	
	@Override
	public String tipoCarga() {
		// TODO Auto-generated method stub
		return "Reefer";
	}

}
