package ar.edu.unq.po2.container.bls;

import ar.edu.unq.po2.cliente.Cliente;

public class CargaBLHoja implements CargaBL{
    
	Cliente cliente;
	double peso;
	public CargaBLHoja(Cliente cliente,double peso) {
		this.cliente=cliente;
	}
	@Override
	public String tipoCargaBL() {
		// TODO Auto-generated method stub
		return "CargaBL";
	}

	@Override
	public double getPeso() {
		// TODO Auto-generated method stub
		return peso;
	}

}
