package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public class CantidadMaximaPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	private MovimientoPrecondition cantidadMaximaPrecondition;
	private Integer cantidadMaxima;

	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		cantidadMaxima = 5;
		
		cantidadMaximaPrecondition = new CantidadMaximaPrecondition(cantidadMaxima);
	}
		
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		when(movimiento.getCantidad()).thenReturn(cantidadMaxima + 1);
		
		cantidadMaximaPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsMovimientoIlegalException() {
		when(movimiento.getCantidad()).thenReturn(cantidadMaxima - 1);
		
		cantidadMaximaPrecondition.check(tableroSnapshot, movimiento);
	}
}
