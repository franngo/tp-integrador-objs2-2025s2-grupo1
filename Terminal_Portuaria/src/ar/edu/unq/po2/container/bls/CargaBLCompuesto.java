package ar.edu.unq.po2.container.bls;

import java.util.List;

public class CargaBLCompuesto implements CargaBL{
   
	public CargaBLCompuesto(List<CargaBL> cargas) {
	
		this.cargas = cargas;
	}

	List<CargaBL> cargas;
	
	@Override
	public String tipoCargaBL() {
		// TODO Auto-generated method stub
		return "Carga compuesta";
	}

	@Override
	public double getPeso() {
		// TODO Auto-generated method stub
		return cargas.stream().
				mapToDouble(carga -> carga.getPeso()).
				sum();
	}

}
