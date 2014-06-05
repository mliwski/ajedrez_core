package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon.PeonCantidadPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class PeonCantidadPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque escaque_1;
	private Color color_peon = Color.Blanco;
	private Peon trebejo_1;
	
	private Escaque escaque_2;
	private Trebejo trebejo_2;

	private MovimientoPrecondition cantidadPrecondition;

	@Before
	public void beforeEveryTest() {
		trebejo_1 = new Peon(color_peon);
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		escaque_1 = mock(Escaque.class);
		when(tableroSnapshot.getTrebejo(escaque_1)).thenReturn(trebejo_1);

		escaque_2 = mock(Escaque.class);
		trebejo_2 = mock(Trebejo.class);
		
		when(movimiento.getOrigen()).thenReturn(escaque_1);
		when(movimiento.getDestino()).thenReturn(escaque_2);
		
		cantidadPrecondition = new PeonCantidadPrecondition();
	}

	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseIllegalCantidad() {
		trebejo_1 = spy(new Peon(color_peon));
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1);
		when(movimiento.getCantidad()).thenReturn(2);
		when(trebejo_1.isTrebejoMovido()).thenReturn(true);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseIllegalCantidadOnFirst() {
		trebejo_1 = spy(new Peon(color_peon));
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1);
		when(movimiento.getCantidad()).thenReturn(3);
		when(trebejo_1.isTrebejoMovido()).thenReturn(false);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsBecauseTwoMovesOnFirst() {
		trebejo_1 = spy(new Peon(color_peon));
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(tableroSnapshot.getTrebejo(escaque_1)).thenReturn(trebejo_1);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1);
		when(movimiento.getCantidad()).thenReturn(2);
		when(trebejo_1.isTrebejoMovido()).thenReturn(false);
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsBecauseOneMoveOnFirst() {
		trebejo_1 = spy(new Peon(color_peon));
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1);
		when(movimiento.getCantidad()).thenReturn(1);
		when(trebejo_1.isTrebejoMovido()).thenReturn(false);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsBecauseOneMoveOnMoved() {
		trebejo_1 = spy(new Peon(color_peon));
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1);
		when(movimiento.getCantidad()).thenReturn(1);
		when(trebejo_1.isTrebejoMovido()).thenReturn(true);
		
		cantidadPrecondition.check(tableroSnapshot, movimiento);
	}
}