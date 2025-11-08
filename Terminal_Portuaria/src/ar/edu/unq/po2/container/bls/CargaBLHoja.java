package ar.edu.unq.po2.container.bls;

import ar.edu.unq.po2.cliente.Cliente;

public class CargaBLHoja implements CargaBL{
   
	Cliente cliente;
	double peso;
	public CargaBLHoja(Cliente cliente,double peso) {
		this.cliente=cliente;
		this.peso=peso;
	}
	@Override
	public String tipoCargaBL() {
		// TODO Auto-generated method stub
		return "Carga BL Individual";
		
	}
	
	

	@Override
	public double getPeso() {
		// TODO Auto-generated method stub
		return peso;
	}
	@Override
	public String dueños() {
		// TODO Auto-generated method stub
		return cliente.nombreCliente();
	}

}
