

import java.util.List;

import ar.edu.unq.po2.servicio.Servicio;

public interface VisitorContainer {
    public List<Servicio> serviciosDry(Dry container);
    public List <Servicio> serviciosTanque(Tanque container);
}
