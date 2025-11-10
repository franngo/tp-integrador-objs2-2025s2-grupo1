package ar.edu.unq.po2.buque.estadosBuque;

import ar.edu.unq.po2.buque.Buque;

public interface EstadoBuque {
	public void notificarAccionesTerminal();
	public void actualizarSiSeRequiere(Buque buque);
	// la terminal necesita un visitor para ver que estado 
	// tiene el buque y saber que servicios debe cancelar
}
