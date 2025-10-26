package ar.edu.unq.po2.container;

import ar.edu.unq.po2.servicio.TipoServicio;
import ar.edu.unq.po2.cliente.Cliente;

import java.util.Arrays;

public class Dry extends Container {


    @Override
    public String tipoCarga() {
        return "Dry";
    }



    public Dry(Cliente duenioConsignee, double ancho, double largo, double altura, double peso,double consumoElectricidad) {
         super(duenioConsignee, ancho, largo, altura, peso);
         // funcionalidad de servicios suspendida por ahora
        // this.servicios = Arrays.asList(TipoServicio.LAVADO, TipoServicio.PESADO);
    }



}
