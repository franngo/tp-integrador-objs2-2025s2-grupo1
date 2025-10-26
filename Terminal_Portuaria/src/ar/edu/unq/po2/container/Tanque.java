package ar.edu.unq.po2.container;

import ar.edu.unq.po2.cliente.Cliente;

public class Tanque extends Container{


    public Tanque(Cliente duenio, double ancho, double largo, double altura, double peso) {
        super(duenio, ancho, largo, altura, peso);
    }

    @Override
    public String tipoCarga() {
        return "Tanque";
    }
}
