package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.DestinoOcupablePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class DestinoOcupablePreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	private Escaque escaque_1;
	private Trebejo trebejo_1;
	private Escaque escaque_2;
	private Trebejo trebejo_2;

	private MovimientoPrecondition destinoOcupablePrecondition;

	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		escaque_1 = mock(Escaque.class);
		trebejo_1 = mock(Trebejo.class);
		when(trebejo_1.getColor()).thenReturn(Color.Blanco);
		when(tableroSnapshot.getTrebejo(escaque_1)).thenReturn(trebejo_1);

		escaque_2 = mock(Escaque.class);
		trebejo_2 = mock(Trebejo.class);
		
		when(movimiento.getOrigen()).thenReturn(escaque_1);
		when(movimiento.getDestino()).thenReturn(escaque_2);
		
		destinoOcupablePrecondition = new DestinoOcupablePrecondition();
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDestinoOcupadoPorContrincante() {
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1.getContrincante());
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDestinoLibre() {
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(null);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
}