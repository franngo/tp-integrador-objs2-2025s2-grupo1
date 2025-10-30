package ar.edu.unq.po2.container;

import ar.edu.unq.po2.Servicio.TipoServicio;
import ar.edu.unq.po2.cliente.Cliente;

import java.util.Arrays;

public class Dry extends Container {
    public Dry(Cliente duenio, double ancho, double largo, double altura, double peso) {
        super(duenio, ancho, largo, altura, peso);
    }
  /*
    public Dry(Cliente duenioConsignee, double ancho, double largo, double altura, double peso) {
        super(duenioConsignee, ancho, largo, altura, peso);
         this.servicios = Arrays.asList(TipoServicio.LAVADO, TipoServicio.PESADO);
    }
    */


}
