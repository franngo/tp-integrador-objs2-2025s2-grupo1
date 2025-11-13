package ar.edu.unq.po2.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ClienteCompuesto extends Cliente{
    
	List<Cliente> clientes;
	
	public ClienteCompuesto(String nombre,List<Cliente> clientesAgregar) {
		super(null);
		clientes=new ArrayList<Cliente>();
		clientes.addAll(clientesAgregar);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String nombreCliente() {
		// @Benja: te da un string que luego tenes que mapear
		String nombres = clientes.stream()
			    .map(Cliente::nombreCliente)   
			    .collect(Collectors.joining(", "));
		return nombres;
	}
 
}
