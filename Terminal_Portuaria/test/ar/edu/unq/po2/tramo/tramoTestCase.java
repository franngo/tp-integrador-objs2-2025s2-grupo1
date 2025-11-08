package ar.edu.unq.po2.tramo;

import ar.edu.unq.po2.coordenada.Coordenada;
import ar.edu.unq.po2.terminal_portuaria.TerminalPortuaria;
import ar.edu.unq.po2.tramo.Tramo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/*
* @autor Matias Sanchez
*/
public class tramoTestCase {


    TerminalPortuaria buenosAires;
    TerminalPortuaria nuevaYork;

    Tramo miTramo;

    @BeforeEach
    public void setUp(){
        //buenosAires = Mockito.mock(TerminalPortuaria.class);
        //nuevaYork = Mockito.mock(TerminalPortuaria.class);
    	Coordenada coordenadaBuenosAires = new Coordenada(-34.6417, -58.3443);
    	Coordenada coordenadaNuevaYork = new Coordenada(40.6721, -74.0808);
    	
        buenosAires = new TerminalPortuaria(coordenadaBuenosAires);
        nuevaYork = new TerminalPortuaria(coordenadaNuevaYork);
        miTramo = new Tramo(nuevaYork,buenosAires, Duration.ofDays(2),15000);
    }


    /*
    * El tramo efectivamente dura 2 dias
    * */
    @Test
    public void duracionTramo(){
        Duration twoDays =  Duration.ofDays(2);
        assertEquals(twoDays,miTramo.getTiempoTotal());
    }

    /*
    * Las terminales destino y origen son coherentes con el tramo
    */

    @Test
    public void terminalesCoherentes(){
        //caso valido
        assertEquals(buenosAires,miTramo.getTerminalOrigen());
        assertEquals(nuevaYork,miTramo.getTerminalDestino());

        // caso invalido
        assertNotEquals(nuevaYork,miTramo.getTerminalOrigen());
        assertNotEquals(buenosAires,miTramo.getTerminalDestino());
    }
    /*
    * El precio del tramo es de 15000
     */
    @Test
    public void precioTramo(){
        assertEquals((double) 15000,miTramo.getPrecioTramo());
    }
}