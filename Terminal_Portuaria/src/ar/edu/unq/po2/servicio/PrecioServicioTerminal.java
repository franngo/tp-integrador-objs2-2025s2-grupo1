package ar.edu.unq.po2.servicio;


public enum PrecioServicioTerminal {
    LAVADOPESADO(20000.0),
    LAVADOCOMUN(15000.0),
    KILOWATTCONSUMIDO(10.0),
    PESAJE(5000),
    DIAEXCEDENTE(3000.0),
	PRECIODESCONSOLIDADO(20000);
    

    private final double precio;

    PrecioServicioTerminal(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}