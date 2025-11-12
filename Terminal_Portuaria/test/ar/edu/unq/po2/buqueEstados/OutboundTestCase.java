package ar.edu.unq.po2.buqueEstados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.buque.Buque;
import ar.edu.unq.po2.buque.estadosBuque.OutBound;

class OutboundTestCase {
     
	Buque buqueMock;
	OutBound estadoBuque;
	@BeforeEach
	void setUp() throws Exception {
		buqueMock= mock(Buque.class);
		
		estadoBuque= new OutBound(buqueMock);
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
