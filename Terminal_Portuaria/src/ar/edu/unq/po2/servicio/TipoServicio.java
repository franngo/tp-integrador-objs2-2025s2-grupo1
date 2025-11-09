package ar.edu.unq.po2.servicio;

import ar.edu.unq.po2.container.Container;
import ar.edu.unq.po2.orden.Orden;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;

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
        public Servicio crearServicio(Orden ordenQueCobra, Container conteiner) {
            // TODO IMPLEMENTAR!!!!
            return new ServicioLavado();
        }
    },

    ELECTRICIDAD {
        @Override
        public Servicio crearServicio(Orden ordenQueCobra, Container conteiner) {
            // TODO IMPLEMENTAR!!!!
            return new ServicioElectricidad();
        }
    },

    PESADO {
        @Override
        public Servicio crearServicio(Orden ordenQueCobra, Container conteiner) {
            // TODO IMPLEMENTAR!!!!
            return new ServicioPesaje();
        }
    },
    EXCEDENTE{
        @Override
        public Servicio crearServicio(Orden ordenQueCobra, Container conteiner) {
            // TODO IMPLEMENTAR!!!!
            return new ServicioExcedente();
        }
    };



    public abstract Servicio crearServicio(Orden ordenQueCobra, Container conteiner);
}
