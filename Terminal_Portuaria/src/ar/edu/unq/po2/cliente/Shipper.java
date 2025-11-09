package ar.edu.unq.po2.cliente;

public class Shipper implements Cliente{
    
	
	String nombreCliente;
	@Override
	public String nombreCliente() {
		// TODO Auto-generated method stub
		return nombreCliente;
	}
	
	public Shipper(String nombreCliente) {
		this.nombreCliente=nombreCliente;
	}

}
