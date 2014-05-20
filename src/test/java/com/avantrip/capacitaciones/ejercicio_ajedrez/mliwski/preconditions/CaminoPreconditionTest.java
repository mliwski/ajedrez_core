package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

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

	private class MockMovimientoPrecondition extends MovimientoPrecondition {

		@Override
		public void check(Movimiento movimiento) {
		}
		
	}
}