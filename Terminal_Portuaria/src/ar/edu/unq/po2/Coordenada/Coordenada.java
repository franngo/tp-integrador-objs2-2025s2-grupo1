

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
    * Constructor de Coordenada:
    * @param latitud longitud : en conjunto representan la ubicacion geográfica
    * del usuario que la usa
    * */
    public Coordenada(double latitud, double longitud){
        this.latidud=latitud;
        this.longitud=longitud;
    }


    private double getLatitud() { return latitud; }
    private double getLongitud() { return longitud; }

    private void setLatitud(double latitud) { this.latitud = latitud; }
    private void setLongitud(double longitud) { this.longitud = longitud; }

    /*
     * Precondicion: la latitud y longitud deben ser razonables
     * @param latitud  longitud: en conjunto reprentan la nueva ubicacion geográfica
     *                           del usuario
     */
    public nuevaPosicion(double latitud, double longitud){
        /*
        * Precondicion: la latitud y longitud deben ser razonables
        * */
        this.latidud=latitud;
        this.longitud=longitud;
    }
    /*
    * @return devuelve un par que representa la posicion geográfica actual
    * del usuario
    * */
    public Pair<double,double>posicionActual(){
        return new Pair<>(latidud,longitud);
    }

}
