package ar.edu.unq.po2.container;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.servicio.*;

public class ConcreteVisitorContainer implements VisitorContainer{
    //TODO implementar esta seccion
	@Override
	public List<Servicio> serviciosDry(Dry container) {
		List<Servicio> serviciosDry = new ArrayList<Servicio>();
		return serviciosDry;
	}

	@Override
	public List<Servicio> serviciosTanque(Tanque container) {
		List<Servicio> serviciosTanque = new ArrayList<Servicio>();
		// TODO Auto-generated method stub
		return serviciosTanque;
	}

	@Override
	public List<Servicio> serviciosReefer(Reefer container) {
		List<Servicio> serviciosReefer = new ArrayList<Servicio>();
		
		// TODO Auto-generated method stub
		return serviciosReefer;
	}

}
