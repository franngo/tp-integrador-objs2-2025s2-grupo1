package ar.edu.unq.po2.buque.estadosBuque;

public interface EstadoBuque {
	public void notificarAccionesTerminal();
	// la terminal necesita un visitor para ver que estado 
	// tiene el buque y saber que servicios debe cancelar
}
