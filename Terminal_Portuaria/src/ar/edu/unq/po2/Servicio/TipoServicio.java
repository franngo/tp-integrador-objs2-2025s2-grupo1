package ar.edu.unq.po2.Servicio;

/*
* @Autor: Matias Sanchez
* */
/*
* Servicios que tendran los Container
* Esto va a extenderse mas adelante cuando se implementen los servicios
* Queda flexible por si hay que tener argumentos y mantener polimorfismo, dependiendo
* de que la terminal le pase algun valor
*
* */
public enum TipoServicio {

    LAVADO {
        @Override
        public Servicio crearServicio() {
            return new ServicioLavado();
        }
    },

    ELECTRICIDAD {
        @Override
        public Servicio crearServicio() {
            return new ServicioElectricidad();
        }
    },

    PESADO {
        @Override
        public Servicio crearServicio() {
            return new ServicioPesaje();
        }
    };

    // Este metodo es abstracto del enum y cada enum implementa su propio comportamiento
    public abstract Servicio crearServicio();
}
