package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class CaminoPreconditionTest {

	@Test
	public void shouldCreatePreconditionWithTablero() {
		MovimientoPrecondition mockPrecondition = new MockMovimientoPrecondition();
		
		assertThat(mockPrecondition.getTablero(), is(not(nullValue())));
	}
	
	@Test
	public void shouldCreateTwoPreconditionWithSameTablero() {
		MovimientoPrecondition mockPrecondition_1 = new MockMovimientoPrecondition();
		MovimientoPrecondition mockPrecondition_2 = new MockMovimientoPrecondition();
		
		assertThat(mockPrecondition_1.getTablero(), is( equalTo(mockPrecondition_2.getTablero()) ));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void noPermitirCaminoNull() {
		MockMovimientoPrecondition mockPrecondition = new MockMovimientoPrecondition();
		mockPrecondition.check(null);
	}

	private class MockMovimientoPrecondition extends MovimientoPrecondition {}
}