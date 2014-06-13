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
	private Escaque origen;
	private Trebejo trebejoOrigen;
	private Escaque destino;
	private Trebejo trebejoDestino;

	private MovimientoPrecondition destinoOcupablePrecondition;

	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		trebejoOrigen = mock(Trebejo.class);
		when(trebejoOrigen.getColor()).thenReturn(Color.Blanco);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(trebejoOrigen);

		destino = mock(Escaque.class);
		trebejoDestino = mock(Trebejo.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		
		destinoOcupablePrecondition = new DestinoOcupablePrecondition();
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Color colorTrebejoOrigen = trebejoOrigen.getColor();
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorTrebejoOrigen);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDestinoOcupadoPorContrincante() {
		Color colorOrigen = trebejoOrigen.getColor();
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen.getContrincante());
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDestinoLibre() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(null);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
}