package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public class CantidadMaximaPreconditionTest {

	private Tablero unTablero;
	private Movimiento unMovimiento;
	private MovimientoPrecondition cantidadMaximaPrecondition;
	private Integer cantidadMaxima;

	@Before
	public void beforeEveryTest() {
		unTablero = mock(Tablero.class);
		unMovimiento = mock(Movimiento.class);
		cantidadMaxima = 5;
		
		
		cantidadMaximaPrecondition = new CantidadMaximaPrecondition(cantidadMaxima);
		cantidadMaximaPrecondition.setTablero(unTablero);
	}
		
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		when(unMovimiento.getCantidad()).thenReturn(cantidadMaxima + 1);
		
		cantidadMaximaPrecondition.check(unMovimiento);
	}
	
	@Test
	public void shouldNotThrowsMovimientoIlegalException() {
		when(unMovimiento.getCantidad()).thenReturn(cantidadMaxima - 1);
		
		cantidadMaximaPrecondition.check(unMovimiento);
	}

}
