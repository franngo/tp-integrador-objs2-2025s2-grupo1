package ar.edu.unq.po2.container;
import java.util.List;
import ar.edu.unq.po2.servicio.Servicio;


public interface VisitorContainer{
    public List<Servicio> serviciosDry(Dry container);
    public List <Servicio> serviciosTanque(Tanque container);
    public List <Servicio> serviciosReefer(Reefer container);
    public List <Servicio> servicioDryCompuesto(Dry container);
    
    /*
     * Se cumple flexibilidad para agregar mas Servicios y Container
     * */
}
