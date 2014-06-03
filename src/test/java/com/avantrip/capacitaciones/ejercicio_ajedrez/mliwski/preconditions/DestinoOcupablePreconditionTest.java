package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
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
		
		Color color = Color.Blanco;
		
		escaque_1 = mock(Escaque.class);
		trebejo_1 = mock(Trebejo.class);
		when(tableroSnapshot.getTrebejo(escaque_1)).thenReturn(trebejo_1);
		when(trebejo_1.getColor()).thenReturn(color);

		escaque_2 = mock(Escaque.class);
		trebejo_2 = mock(Trebejo.class);
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		
		when(movimiento.getOrigen()).thenReturn(escaque_1);
		when(movimiento.getDestino()).thenReturn(escaque_2);
		
		destinoOcupablePrecondition = new DestinoOcupablePrecondition();
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		when(trebejo_2.getColor()).thenReturn(trebejo_1.getColor());
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDestinoOcupadoPorContrincante() {
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);

		Color color = trebejo_1.getColor();
		when(trebejo_2.getColor()).thenReturn(color.getContrincante());
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDestinoLibre() {
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(null);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseEmptyCamino() {
		when(movimiento.getCamino()).thenReturn(Collections.<Escaque>emptyList());
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}

}
