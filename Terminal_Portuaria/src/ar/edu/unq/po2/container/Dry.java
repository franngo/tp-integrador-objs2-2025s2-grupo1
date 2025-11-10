package ar.edu.unq.po2.container;



import java.util.List;

import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.container.bls.CargaBL;
import ar.edu.unq.po2.servicio.Servicio;

public class Dry extends Container {
	
    CargaBL cargaBL;
	public Dry(CargaBL cargaBL) {
		super(null, 0, 0, 0, 0);
		this.cargaBL=cargaBL; 
		this.idContainer=this.generarIdConteiner(duenioConsignee);
	
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public String tipoCarga() {
		// TODO Auto-generated method stub
		return this.cargaSegunContenido(); 
	}
	@Override
	protected String generarIdConteiner(Cliente cliente) { //por polimorfismo queda 
		//queda por polimorfismo
        String nuevaClave = this.codigoGenerico() + this.codigoNumericoRandom();
       return nuevaClave;
    }
	
	public String cargaSegunContenido() {
		return cargaBL.tipoCargaBL();
	}
	
	private String codigoGenerico() {
		return "DRY";
	}

	@Override
	public List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante) {
		// TODO Auto-generated method stub
		return visitante.serviciosDry(this);
	}
    
	
	public CargaBL carga(){
		return cargaBL;
	}


	    public double getPeso() {
	        return cargaBL.getPeso();
	    }
	 
	    public String getDueños() {
	    	return cargaBL.dueños();
	    }
	

}
