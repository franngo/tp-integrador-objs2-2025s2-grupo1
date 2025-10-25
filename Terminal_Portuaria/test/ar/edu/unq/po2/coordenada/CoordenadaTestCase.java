package ar.edu.unq.po2.coordenada;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordenadaTestCase {
    private Coordenada coordenada;

    @BeforeEach
    public void setUp(){
        coordenada = new Coordenada(-34.6837231,-58.3816232);
    }

    /*
    * Test que verifica que se devuelven las coordenadas correctas
    * */
    @Test
    public void testObtencionDeCoordenadas(){
        assertEquals(-34.6837,coordenada.getLatitud());
        assertEquals(-58.3816,coordenada.getLongitud());
    }

    /*
    *  Test que verifica que al cambiar la posicion geografica,
    * es coherente  el estado de la Coordenada
    * */
    @Test
    public void testCambioDeCoordenadas(){
         coordenada.nuevaPosicion(-54.31223231,12.1233232);
        assertEquals(-54.3122,coordenada.getLatitud());
        assertEquals(12.1233,coordenada.getLongitud());
    }

    /*
    * Test que verifica que se redonde a 4 decimales las coordenadas
    * */
    @Test
    public void testRedondeoAdecuado(){
        /*Casos INVÁLIDOS*/
       assertNotEquals(-34.6837231,coordenada.getLatitud());
        assertNotEquals(-58.3816232,coordenada.getLongitud());

        /*Casos VÁLIDOS*/
        assertEquals(-34.6837,coordenada.getLatitud());
        assertEquals(-58.3816,coordenada.getLongitud());


    }
}
