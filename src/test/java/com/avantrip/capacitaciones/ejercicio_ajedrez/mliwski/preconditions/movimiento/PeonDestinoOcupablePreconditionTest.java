package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon.PeonDestinoOcupablePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class PeonDestinoOcupablePreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	private Color colorOrigen = Color.Blanco;
	private Peon trebejoOrigen;
	
	private Escaque destino;
	private Trebejo trebejoDestino;

	private MovimientoPrecondition destinoOcupablePrecondition;

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
		
		destinoOcupablePrecondition = new PeonDestinoOcupablePrecondition();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowsIllegalArgumentBecauseNotPeon() {
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(new Rey(Color.Blanco));
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseDiagonalAndDestinoEmpty() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(null);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseDiagonalAndDestinoSameColor() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseVerticalAndDestinoNotEmpty() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen.getContrincante());
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDiagonalAndDestinoContrincante() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		when(trebejoDestino.getColor()).thenReturn(colorOrigen.getContrincante());
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseVerticalAndDestinoEmpty() {
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(null);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
}