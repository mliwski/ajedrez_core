package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class TipoMovimientoCorrectoPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	private MovimientoPrecondition tipoCorrectoPreconditionPrecondition;
	private List<TipoMovimiento> tipoMovimiento;

	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		tipoMovimiento = Arrays.asList(TipoMovimiento.Vertical, TipoMovimiento.Diagonal);
		
		tipoCorrectoPreconditionPrecondition = new TipoMovimientoCorrectoPrecondition(tipoMovimiento);
	}
		
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Horizontal);
		
		tipoCorrectoPreconditionPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsMovimientoIlegalException() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		
		tipoCorrectoPreconditionPrecondition.check(tableroSnapshot, movimiento);
	}
}
