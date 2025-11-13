package ar.edu.unq.po2.coordenada;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordenadaTestCase {
    private Coordenada coordenada;
    private Coordenada coordenada2;

    @BeforeEach
    public void setUp(){
        coordenada = new Coordenada(-34.6837231,-58.3816232);
        coordenada2 = new Coordenada(-58.3816232,-34.6837231);
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
    
    @Test
    public void testDistanciaEntreCoordenadas() {
    	double distancia1a2 = coordenada.distanciaA(coordenada2);
    	double distancia2a1 = coordenada2.distanciaA(coordenada);
    	assertEquals(distancia1a2, distancia2a1);
    }
    
    @Test
    public void testMismaPosicionEntreCoordenadas() {
    	assertFalse(coordenada.enMismaPosicionCon(coordenada2));
    	coordenada2.nuevaPosicion(-34.6837231,-58.3816232);
    	assertTrue(coordenada.enMismaPosicionCon(coordenada2));
    }
    
    @Test
    public void testFuncionamientoEquals() {
		assertTrue(coordenada.equals(coordenada));
		assertEquals(coordenada.hashCode(), coordenada.hashCode());
		
		assertFalse(coordenada.equals(coordenada2));
		assertNotEquals(coordenada.hashCode(), coordenada2.hashCode());

		coordenada2.nuevaPosicion(-34.6837231,-58.3816232);
		assertTrue(coordenada.equals(coordenada2));
		assertEquals(coordenada.hashCode(), coordenada2.hashCode());
    }
}