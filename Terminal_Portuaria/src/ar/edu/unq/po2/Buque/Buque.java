/*
 * @Autor : Matias Sanchez
 *
 *
 * */
public class Buque {

    Viaje viajeActual;
    Coordenada posicionActual;
    TerminalPortuaria terminalDestino;

    EstadoBuque estadoBuque;
    /*
     * @viajeActual : el buque inicia con el viaje que le pasa la naviera
     * @posicion: el buque inicia con una coordenada particular
     * */
    public Buque(Viaje viajeActual,Posicion posicion){}

    public void avanzarHacia(Coordenada coordenadas){
        this.posicionActual(coordenadas);
        this.notificarNuevaPosicion(terminalDestino);
    }

    public void iniciarViaje(Viaje Viaje){
        viajeActual=viaje;
        estadoBuque = new OutBound();
    }
    public void notificarNuevaPosicion(TerminalPortuaria terminal){
        terminal.notificarPosicion(this);
    }



}