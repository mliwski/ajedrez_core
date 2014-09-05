package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class PeonCantidadPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	private Color colorOrigen = Color.Blanco;
	private Peon trebejoOrigen;
	
	private Escaque destino;
	private Trebejo trebejoDestino;

	private MovimientoPrecondition cantidadPrecondition;

	@Before
	public void beforeEveryTest() {
		trebejoOrigen = new Peon(colorOrigen);
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(trebejoOrigen);

		destino = mock(Escaque.class);
		trebejoDestino = mock(Trebejo.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		
		cantidadPrecondition = new PeonCantidadPrecondition();
	}

	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseIllegalCantidad() {
		Movimiento movimientoAnterior = mock(Movimiento.class);
		trebejoOrigen.addMovimiento(movimientoAnterior);

		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen);
		when(movimiento.getCantidad()).thenReturn(2);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseIllegalCantidadOnFirst() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen);
		when(movimiento.getCantidad()).thenReturn(3);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsBecauseTwoMovesOnFirst() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(trebejoOrigen);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen);
		when(movimiento.getCantidad()).thenReturn(2);

		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsBecauseOneMoveOnFirst() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen);
		when(movimiento.getCantidad()).thenReturn(1);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsBecauseOneMoveOnMoved() {
		Movimiento movimientoAnterior = mock(Movimiento.class);
		trebejoOrigen.addMovimiento(movimientoAnterior);
		
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen);
		when(movimiento.getCantidad()).thenReturn(1);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
}