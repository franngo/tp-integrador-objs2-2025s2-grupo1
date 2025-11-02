package ar.edu.unq.po2.container.dry;

import java.util.List;

import ar.edu.unq.po2.cliente.Cliente;

public class DryUnico extends Dry{
	

	public DryUnico(double ancho, double largo, double altura, double peso,Cliente duenio) {
		super(ancho, largo, altura, peso);
		this.duenioConsignee(duenio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Dry> cargas() {
		// TODO Auto-generated method stub
		return null;
	}
    
  

	
	

}
