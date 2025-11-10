package ar.edu.unq.po2.buque.estadosBuque;



public abstract class EstadoBuque {
	//public void notificarAccionesTerminal(Buque buque);

	
	public void actualizarSiSeRequiere() {
	      if(this.debeCambiarDeFase()) {
	    	  	this.modificarEstadoBuque();
	      }
		
	}
	
	public abstract boolean debeCambiarDeFase();
	public abstract void modificarEstadoBuque();
	// la terminal necesita un visitor para ver que estado 
	// tiene el buque y saber que servicios debe cancelar

	public void notificarAccionesTerminal() {
		// TODO Auto-generated method stub
		
	}
}
