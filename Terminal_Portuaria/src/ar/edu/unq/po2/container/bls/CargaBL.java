package ar.edu.unq.po2.container.bls;

import ar.edu.unq.po2.cliente.Cliente;

public interface CargaBL {
	
	public String dueños();
	public String tipoCargaBL();
    public double getPeso();
	public Cliente getDuenioConsignee();
}
