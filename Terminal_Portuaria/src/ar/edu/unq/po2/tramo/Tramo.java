package ar.edu.unq.po2.tramo;

import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

import java.time.Duration;


/*
* @Autor: Matias Sanchez
* */
public class Tramo {

    public Tramo(TerminalPortuaria terminalDestino, TerminalPortuaria terminalOrigen, Duration tiempoTotal, double precioTramo) {
        this.terminalDestino = terminalDestino;
        this.terminalOrigen = terminalOrigen;
        this.tiempoTotal = tiempoTotal;
        this.precioTramo = precioTramo;
    }
    TerminalPortuaria terminalDestino;
    TerminalPortuaria terminalOrigen;

    Duration tiempoTotal;
    double precioTramo;


    public TerminalPortuaria getTerminalDestino() {
        return terminalDestino;
    }

    public TerminalPortuaria getTerminalOrigen() {
        return terminalOrigen;
    }

    public Duration getTiempoTotal() {
        return tiempoTotal;
    }

    public double getPrecioTramo() {
        return precioTramo;
    }


}
