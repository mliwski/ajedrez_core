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

public class PeonPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque escaque_1;
	private Color color_peon = Color.Blanco;
	private Peon trebejo_1;
	
	private Escaque escaque_2;
	private Trebejo trebejo_2;

	private MovimientoPrecondition destinoOcupablePrecondition;

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
		
		destinoOcupablePrecondition = new PeonDestinoOcupablePrecondition();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowsIllegalArgumentBecauseNotPeon() {
		when(tableroSnapshot.getTrebejo(escaque_1)).thenReturn(new Rey(Color.Blanco));
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseDiagonalAndDestinoEmpty() {
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(null);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseDiagonalAndDestinoSameColor() {
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseVerticalAndDestinoNotEmpty() {
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1.getContrincante());
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseDiagonalAndDestinoContrincante() {
		Color colorTrebejo_1 = trebejo_1.getColor();
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo_2);
		when(trebejo_2.getColor()).thenReturn(colorTrebejo_1.getContrincante());
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseVerticalAndDestinoEmpty() {
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(null);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		
		destinoOcupablePrecondition.check(tableroSnapshot, movimiento);
	}
}