package ar.edu.unq.po2.container;

import ar.edu.unq.po2.cliente.Cliente;

public class Reefer extends Container{

    private double consumoElectricidad;

    public Reefer(Cliente duenio, double ancho, double largo, double altura, double peso,double consumoElectricidad) {
        super(duenio, ancho, largo, altura, peso);
    }

    public double getConsumoElectricidad(){
        return consumoElectricidad;
    }

    @Override
    public String tipoCarga() {
        return "Reefer";
    }
}
