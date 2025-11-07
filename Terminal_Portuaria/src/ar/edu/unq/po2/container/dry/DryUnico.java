package ar.edu.unq.po2.container.dry;



import ar.edu.unq.po2.cliente.Cliente;

public class DryUnico extends Dry{
	

	

	public DryUnico(Cliente cliente, double ancho, double largo, double altura, double peso) {
		super(cliente, ancho, largo, altura, peso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String tipoCarga() {
		// TODO Auto-generated method stub
		return "Dry individual";
	}

	
    
  

	
	

}
