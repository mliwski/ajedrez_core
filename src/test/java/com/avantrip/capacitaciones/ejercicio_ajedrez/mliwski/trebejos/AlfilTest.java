package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategyDefault;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CaminoOcupadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DestinoNoOcupableException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoNoPermitidoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;

public class AlfilTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	
	private Escaque destino;

	private Color colorAlfil = Color.Blanco;
	private Trebejo alfil;

	@Before
	public void beforeEveryTest() {
		
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		alfil = new Alfil(colorAlfil);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(alfil);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Alfil(null);
	}
	
	@Test
	public void preconditionsShouldBeTheSame() {
		Alfil alfil1 = new Alfil(colorAlfil);
		List<MovimientoPrecondition> preconditions1 = alfil1.getMovimientoPreconditions();
		Alfil alfil2 = new Alfil(colorAlfil);
		List<MovimientoPrecondition> preconditions2 = alfil2.getMovimientoPreconditions();
		
		assertThat(preconditions1, sameInstance(preconditions2));
	}
	
	@Test
	public void trebejoWithSameColorShouldBeEquals() {
		Alfil alfil1 = new Alfil(colorAlfil);
		Alfil alfil2 = new Alfil(colorAlfil);
		
		assertThat(alfil1, equalTo(alfil2));
	}
	
	@Test
	public void trebejoWithDifferentColorShouldntBeEquals() {
		Alfil alfil1 = new Alfil(colorAlfil);
		Alfil alfil2 = new Alfil(colorAlfil.getContrincante());
		
		assertThat(alfil1, not(equalTo(alfil2)));
	}
	
	@Test
	public void shouldGetColorOrigen() {
		assertThat(alfil.getColor(), equalTo(colorAlfil));
	}
	
	@Test
	public void shouldGetTrebejoMovido() {
		alfil.addMovimiento(movimiento);
		assertThat(alfil.isTrebejoMovido(), equalTo(true));
	}
	
	@Test
	public void shouldGetTrebejoNoMovido() {
		assertThat(alfil.isTrebejoMovido(), equalTo(false));
	}
	
	@Test
	public void shouldGetCapturaStrategyDefault() {
		assertThat(alfil.getCapturaStrategy(), instanceOf(CapturaStrategyDefault.class));
	}

	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoVertical() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		alfil.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoHorizontal() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Horizontal);
		alfil.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoEle() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Ele);
		alfil.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=CaminoOcupadoException.class)
	public void shouldThrowExceptionBecauseCaminoOcupado() {
		Trebejo trebejoEnCamino = mock(Trebejo.class);
		Escaque escaqueEnCamino = mock(Escaque.class);
		when(tableroSnapshot.getTrebejo(escaqueEnCamino)).thenReturn(trebejoEnCamino);
		when(movimiento.getCamino()).thenReturn(Arrays.asList(escaqueEnCamino));
		
		alfil.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		Trebejo trebejoDestino = mock(Trebejo.class);
		when(trebejoDestino.getColor()).thenReturn(colorAlfil);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		
		alfil.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=ReyAmenazadoException.class)
	public void shouldThrowExceptionBecauseReyExpuesto() {
		Escaque escaqueDelRey = mock(Escaque.class);
		Rey rey = new Rey(colorAlfil);

		when(tableroSnapshot.getEscaque(rey)).thenReturn(escaqueDelRey);
		Color colorContrincante = colorAlfil.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaqueDelRey, colorContrincante)).thenReturn(true);
		
		alfil.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldCheckPreconditionsWithoutException() {
		alfil.checkPreconditions(tableroSnapshot, movimiento);
	}

}
