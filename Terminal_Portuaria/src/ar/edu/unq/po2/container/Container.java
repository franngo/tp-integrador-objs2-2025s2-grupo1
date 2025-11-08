package ar.edu.unq.po2.container;


import ar.edu.unq.po2.cliente.Cliente;
import ar.edu.unq.po2.servicio.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/*
* @Autor: Matias Sanchez
* */
public abstract class Container {
    protected Cliente duenioConsignee;

    protected String idContainer;

    private double ancho;

    private double largo;

    private double altura;

    private double peso;


    /*
     * recibe un visitante que le pregunta que Servicios instanciar y se le dice cuales
     * */
    public abstract List<Servicio> acceptVisitor(ConcreteVisitorContainer visitante);
    
    public  String billOfLading() {
    	return "Tipo de carga: " + this.tipoCarga() + ", Peso: " + this.peso + " kg";
    }
    
    public abstract String tipoCarga();
    	
    
    
    // ********************************* @Getters ********************************
    public Cliente getDuenioConsignee() {
        return duenioConsignee;
    }
    
    protected void duenioConsignee(Cliente cliente) {
    	 this.duenioConsignee=cliente;
    }

    protected String getIdConnteiner() {
        return idContainer;
    }

    public double getAncho() {
        return ancho;
    }

    public double getLargo() {
        return largo;
    }

    public double getAltura() {
        return altura;
    }


    protected double getPeso() {
        return peso;
    }

    

    // ********************************* CONSTRUCTOR ********************************
    /*
     * Constructor de conteiner
     * @param duenioConsignee representa al dueño de la carga. Por lo general sera un consignee
     * */
    public Container(Cliente duenio, double ancho, double largo, double altura, double peso) {
        this.duenioConsignee = duenio;
        this.ancho = ancho;
        this.largo = largo;
        this.altura = altura;
        this.peso = peso;
        this.idContainer = this.generarIdConteiner(this.duenioConsignee);
        
    }

    /*
     * Proposito: crea un nuevo id para el conteiner, conformado por las primeras 4 letras en mayusculas del cliente
     * seguido de 7 numero
     * @param cliente: representa al duenio de la carga
     * */
    protected String generarIdConteiner(Cliente cliente) {
        String nuevaClave = this.codigoCliente(cliente.nombreCliente()) + this.codigoNumericoRandom();
       return nuevaClave;
    }

    /*
     * @Return : devuelve las priemras 4 letras del nombre del cliente en Mayusculas
     * */
    protected String codigoCliente(String cliente) {
        return cliente.substring(0, 3).toUpperCase();
    }

    protected String codigoNumericoRandom() {
        int numero = ThreadLocalRandom.current().nextInt(0, 10_000_000); // numero aleatorio entre 0 a 9,999,999
        return this.num7Caracteres(numero);
    }

    /*
     * @Return dado un numero, devuelve una cadena de caracteres de 7 digitos, rellenando con 0 a la izquierda
     * de ser necesario
     * */
    protected String num7Caracteres(int num) {
        return String.format("%07d", num);
    }
    
   

	
}


    // *****************************************************************




