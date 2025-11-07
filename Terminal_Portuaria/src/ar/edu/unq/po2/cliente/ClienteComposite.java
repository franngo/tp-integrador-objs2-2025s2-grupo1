package ar.edu.unq.po2.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteComposite implements Cliente{
    
	
	public List<Cliente> clientes; 
	
	public ClienteComposite(List<Cliente> clientes) {
		this.clientes= new ArrayList<Cliente>();
		clientes.addAll(clientes);
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
