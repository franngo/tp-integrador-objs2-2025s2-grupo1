

/*
 *@Autor: Matias Sanchez
 * Modelado inicial de las coordenadas de un buque. De momento es un DATA CLASS
 *Se ira agregando comportamiento a medida que se lo precise
 */
 */
public class Coordenada {

    private double latidud
    private double longitud
    /*
    * Constructor de Coordenada
    * */
    public Coordenada(double latitud, double longitud){
        this.latidud=latitud;
        this.longitud=longitud;
    }
    /*
    *
    * */
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }

    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public posicionActual(double latitud, double longitud){
        /*
        * Precondicion: la latitud y longitud deben ser razonables
        * */
        this.latidud=latitud;
        this.longitud=longitud;
    }

}
