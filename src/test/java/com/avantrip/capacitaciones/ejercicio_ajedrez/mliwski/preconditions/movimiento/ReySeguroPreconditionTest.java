package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.ReySeguroPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class ReySeguroPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	private Trebejo trebejoOrigen;
	private Color colorOrigen = Color.Blanco;
	
	private Escaque destino;
	
	private MovimientoPrecondition reySeguroPrecondition;
	
	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		trebejoOrigen = mock(Trebejo.class);
		when(trebejoOrigen.getColor()).thenReturn(colorOrigen);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(trebejoOrigen);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		
		when(tableroSnapshot.getEscaqueDelRey(colorOrigen)).thenReturn(destino);
		
		
		reySeguroPrecondition = new ReySeguroPrecondition();
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Color colorContrincante = colorOrigen.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(destino, colorContrincante)).thenReturn(true);
		
		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseCaminoLibre() {
		Color colorContrincante = colorOrigen.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(destino, colorContrincante)).thenReturn(false);

		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
}