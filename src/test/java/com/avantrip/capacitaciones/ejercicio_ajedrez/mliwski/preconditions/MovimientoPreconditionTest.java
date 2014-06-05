package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;

public class MovimientoPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	private MovimientoPrecondition movimientoPrecondition;
	
	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		movimientoPrecondition = mock(MovimientoPrecondition.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentBecauseNullTableroSnapshot() {
		movimientoPrecondition.check(null, movimiento);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentBecauseNullMovimiento() {
		movimientoPrecondition.check(tableroSnapshot, null);
	}
	
	@Test
	public void shouldNotThrowException() {
		movimientoPrecondition.check(tableroSnapshot, movimiento);
		verify(movimientoPrecondition).checkMovimientoPreconditions(tableroSnapshot, movimiento);
	}
}
