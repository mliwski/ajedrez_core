package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class ReySeguroPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	private Trebejo trebejo;
	private MovimientoPrecondition reySeguroPrecondition;
	private Escaque escaque_1;
	private Escaque escaque_2;
	private Color color = Color.Blanco;
	
	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		escaque_1 = mock(Escaque.class);
		trebejo = mock(Trebejo.class);
		when(trebejo.getColor()).thenReturn(color);
		when(tableroSnapshot.getTrebejo(escaque_1)).thenReturn(trebejo);

		escaque_2 = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(escaque_1);
		when(movimiento.getDestino()).thenReturn(escaque_2);
		
		when(tableroSnapshot.getEscaqueDelRey(color)).thenReturn(escaque_2);
		
		
		reySeguroPrecondition = new ReySeguroPrecondition();
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaque_2, color.getContrincante())).thenReturn(true);
		
		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseCaminoLibre() {
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaque_2, color.getContrincante())).thenReturn(false);

		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
}