package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategyDefault;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DestinoNoOcupableException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoNoPermitidoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;

public class TorreTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	
	private Escaque destino;

	private Color colorTorre = Color.Blanco;
	private Trebejo torre;

	@Before
	public void beforeEveryTest() {
		
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		torre = new Torre(colorTorre);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(torre);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Horizontal);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Torre(null);
	}
	
	@Test
	public void preconditionsShouldBeTheSame() {
		Torre torre1 = new Torre(colorTorre);
		List<MovimientoPrecondition> preconditions1 = torre1.getMovimientoPreconditions();
		Torre torre2 = new Torre(colorTorre);
		List<MovimientoPrecondition> preconditions2 = torre2.getMovimientoPreconditions();
		
		assertThat(preconditions1, sameInstance(preconditions2));
	}
	
	@Test
	public void trebejoWithSameColorShouldBeEquals() {
		Torre torre1 = new Torre(colorTorre);
		Torre torre2 = new Torre(colorTorre);
		
		assertThat(torre1, equalTo(torre2));
	}
	
	@Test
	public void trebejoWithDifferentColorShouldntBeEquals() {
		Torre torre1 = new Torre(colorTorre);
		Torre torre2 = new Torre(colorTorre.getContrincante());
		
		assertThat(torre1, not(equalTo(torre2)));
	}
	
	@Test
	public void shouldGetColorOrigen() {
		assertThat(torre.getColor(), equalTo(colorTorre));
	}
	
	@Test
	public void shouldGetTrebejoMovido() {
		torre.addMovimiento(movimiento);
		assertThat(torre.isTrebejoMovido(), equalTo(true));
	}
	
	@Test
	public void shouldGetTrebejoNoMovido() {
		assertThat(torre.isTrebejoMovido(), equalTo(false));
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoEle() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Ele);
		torre.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		Trebejo trebejoDestino = mock(Trebejo.class);
		when(trebejoDestino.getColor()).thenReturn(colorTorre);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		
		torre.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=ReyAmenazadoException.class)
	public void shouldThrowExceptionBecauseReyExpuesto() {
		Escaque escaqueDelRey = mock(Escaque.class);
		Rey rey = new Rey(colorTorre);
		when(tableroSnapshot.getEscaque(rey)).thenReturn(escaqueDelRey);
		Color colorContrincante = colorTorre.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaqueDelRey, colorContrincante)).thenReturn(true);
		
		torre.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldCheckPreconditionsWithoutException() {
		torre.checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test
	public void shouldGetCapturaStrategyDefault() {
		assertThat(torre.getCapturaStrategy(), instanceOf(CapturaStrategyDefault.class));
	}

}
