package ar.edu.unq.po2.cliente;

public class Consignee implements Cliente{
    
	String nombreCliente;
	
	public Consignee(String nombre) {
		this.nombreCliente=nombre;
	}

	@Override
	public String nombreCliente() {
		// TODO Auto-generated method stub
		return nombreCliente;
	}

}
