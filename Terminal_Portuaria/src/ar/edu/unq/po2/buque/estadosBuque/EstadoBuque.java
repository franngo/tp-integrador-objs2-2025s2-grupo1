package ar.edu.unq.po2.buque.estadosBuque;



public abstract class EstadoBuque {
	//public void notificarAccionesTerminal(Buque buque);

     // TEMPLATE METHOD 	
	public void actualizarSiSeRequiere() {
	      if(this.debeCambiarDeFase()) {
	    	  	this.modificarEstadoBuque();
	      }
		
	}
	
	public abstract boolean debeCambiarDeFase();
	public abstract void modificarEstadoBuque();
	


	
}
