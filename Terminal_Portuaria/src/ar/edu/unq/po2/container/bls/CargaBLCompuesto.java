package ar.edu.unq.po2.container.bls;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.cliente.ClienteCompuesto;

public class CargaBLCompuesto implements CargaBL{
   
	public CargaBLCompuesto(List<CargaBL> cargas) {
	
		this.cargas = cargas;
	}

	List<CargaBL> cargas;
	
	@Override
	public String tipoCargaBL() {
		// TODO Auto-generated method stub
		return "Carga compuesta por "+ this.cantidadCarga() + " BL's";
	}
	
	public int cantidadCarga() {
		return this.cargas.size();
	}

	@Override
	public double getPeso() {
		// TODO Auto-generated method stub
		return cargas.stream().
				mapToDouble(carga -> carga.getPeso()).
				sum();
	}

	@Override
	public String dueños() {
		// TODO Auto-generated method stub
		return cargas.stream()
                .map(CargaBL::dueños)
                .collect(Collectors.joining(","));
	}

	@Override
	public Cliente getDuenioConsignee() {
		List<Cliente> clientesCargaBL = this.obtenerClientes();
		// TODO Auto-generated method stub
		return new ClienteCompuesto(null,clientesCargaBL);
				
	}
	
	private List<Cliente> obtenerClientes(){
		
		return cargas.stream()
	             .map(CargaBL::getDuenioConsignee)
	             .collect(Collectors.toList());
	}

}
