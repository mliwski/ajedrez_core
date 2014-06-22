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
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CantidadMaximaSuperadaException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DestinoNoOcupableException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoNoPermitidoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;

public class ReyTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	
	private Escaque destino;

	private Color colorRey = Color.Blanco;
	private Trebejo rey;

	@Before
	public void beforeEveryTest() {
		
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		rey = new Rey(colorRey);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(rey);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Rey(null);
	}
	
	@Test
	public void preconditionsShouldBeTheSame() {
		Rey rey1 = new Rey(colorRey);
		List<MovimientoPrecondition> preconditions1 = rey1.getMovimientoPreconditions();
		Rey rey2 = new Rey(colorRey);
		List<MovimientoPrecondition> preconditions2 = rey2.getMovimientoPreconditions();
		
		assertThat(preconditions1, sameInstance(preconditions2));
	}
	
	@Test
	public void trebejoWithSameColorShouldBeEquals() {
		Rey rey1 = new Rey(colorRey);
		Rey rey2 = new Rey(colorRey);
		
		assertThat(rey1, equalTo(rey2));
	}
	
	@Test
	public void trebejoWithDifferentColorShouldntBeEquals() {
		Rey rey1 = new Rey(colorRey);
		Rey rey2 = new Rey(colorRey.getContrincante());
		
		assertThat(rey1, not(equalTo(rey2)));
	}
	
	@Test
	public void shouldGetColorOrigen() {
		assertThat(rey.getColor(), equalTo(colorRey));
	}
	
	@Test
	public void shouldGetTrebejoMovido() {
		rey.addMovimiento(movimiento);
		assertThat(rey.isTrebejoMovido(), equalTo(true));
	}
	
	@Test
	public void shouldGetTrebejoNoMovido() {
		assertThat(rey.isTrebejoMovido(), equalTo(false));
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoEle() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Ele);
		rey.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=CantidadMaximaSuperadaException.class)
	public void shouldThrowExceptionBecauseCantidadMaximaSuperada() {
		when(movimiento.getCantidad()).thenReturn(2);
		rey.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		Trebejo trebejoDestino = mock(Trebejo.class);
		when(trebejoDestino.getColor()).thenReturn(colorRey);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		
		rey.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=ReyAmenazadoException.class)
	public void shouldThrowExceptionBecauseReyExpuesto() {
		Escaque escaqueDelRey = mock(Escaque.class);
		when(tableroSnapshot.getEscaque(rey)).thenReturn(escaqueDelRey);
		Color colorContrincante = colorRey.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaqueDelRey, colorContrincante)).thenReturn(true);
		
		rey.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldCheckPreconditionsWithoutException() {
		rey.checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test
	public void shouldGetCapturaStrategyDefault() {
		assertThat(rey.getCapturaStrategy(), instanceOf(CapturaStrategyDefault.class));
	}

}
