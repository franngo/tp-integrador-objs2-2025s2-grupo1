package ar.edu.unq.po2.coordenada;
/*
 *@Autor: Matias Sanchez
 * Modelado inicial de las coordenadas de un usuario. De momento es un DATA CLASS
 *Se ira agregando comportamiento a medida que se lo precise
 */

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

public class Coordenada {

    private double latitud;
    private double longitud;

    /*
    * Constructor de Coordenada:
    * @param latitud longitud : en conjunto representan la ubicacion geográfica
    * del usuario que la usa
    * */
    public Coordenada(double latitud, double longitud){
        this.latitud=latitud;
        this.longitud=longitud;
    }

    /*
    * @return representa la latitud actual del usuario, redondeado a 4 decimales
    * */
    public double getLatitud() { return Math.round(latitud * 10000.0) / 10000.0; }

    /*
     * @return representa la longitud actual del usuario, redondeado a 4 decimales
     * */
    public double getLongitud() { return Math.round(longitud * 10000.0) / 10000.0; }

    private void setLatitud(double latitud) { this.latitud = latitud; }
    private void setLongitud(double longitud) { this.longitud = longitud; }

    /*
     * Precondicion: la latitud y longitud deben ser razonables
     * @param latitud  longitud: en conjunto reprentan la nueva ubicacion geográfica
     *                           del usuario
     */
    public void nuevaPosicion(double latitud, double longitud){
        /*
        * Precondicion: la latitud y longitud deben ser razonables
        * */
        this.setLatitud(latitud);
        this.setLongitud(longitud);
    }


	// #################################### MÉTODOS AUXILIARES ################################## \\
	
	@Override
	public boolean equals(Object object) {
		return (this == object) || (this.esCoordenada(object) && (this.esElMismoQue(object)));
	}
	
	private boolean esCoordenada(Object object) {
		return object instanceof Coordenada;
	}
	
	private boolean esElMismoQue(Object object) {
		Coordenada coordenadaAComparar = (Coordenada) object;
		return (this.getLatitud() == coordenadaAComparar.getLatitud()) && (this.getLongitud() == coordenadaAComparar.getLongitud());
	}
	
	@Override
	public int hashCode() {
		return java.util.Objects.hash(getLatitud(), getLongitud());
	}
}