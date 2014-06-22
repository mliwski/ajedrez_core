package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

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

public class CaballoTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	
	private Escaque destino;

	private Color colorCaballo = Color.Blanco;
	private Trebejo caballo;

	@Before
	public void beforeEveryTest() {
		
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		caballo = new Caballo(colorCaballo);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(caballo);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Ele);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Caballo(null);
	}
	
	@Test
	public void preconditionsShouldBeTheSame() {
		Caballo caballo1 = new Caballo(colorCaballo);
		List<MovimientoPrecondition> preconditions1 = caballo1.getMovimientoPreconditions();
		Caballo caballo2 = new Caballo(colorCaballo);
		List<MovimientoPrecondition> preconditions2 = caballo2.getMovimientoPreconditions();
		
		assertThat(preconditions1, sameInstance(preconditions2));
	}
	
	@Test
	public void trebejoWithSameColorShouldBeEquals() {
		Caballo caballo1 = new Caballo(colorCaballo);
		Caballo caballo2 = new Caballo(colorCaballo);
		
		assertThat(caballo1, equalTo(caballo2));
	}
	
	@Test
	public void trebejoWithDifferentColorShouldntBeEquals() {
		Caballo caballo1 = new Caballo(colorCaballo);
		Caballo caballo2 = new Caballo(colorCaballo.getContrincante());
		
		assertThat(caballo1, not(equalTo(caballo2)));
	}
	
	
	@Test
	public void shouldGetColorOrigen() {
		assertThat(caballo.getColor(), equalTo(colorCaballo));
	}
	
	@Test
	public void shouldGetTrebejoMovido() {
		caballo.addMovimiento(movimiento);
		assertThat(caballo.isTrebejoMovido(), equalTo(true));
	}
	
	@Test
	public void shouldGetTrebejoNoMovido() {
		assertThat(caballo.isTrebejoMovido(), equalTo(false));
	}

	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoVertical() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		caballo.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoHorizontal() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Horizontal);
		caballo.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoDiagonal() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
		caballo.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		Trebejo trebejoDestino = mock(Trebejo.class);
		when(trebejoDestino.getColor()).thenReturn(colorCaballo);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		
		caballo.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=ReyAmenazadoException.class)
	public void shouldThrowExceptionBecauseReyExpuesto() {
		Escaque escaqueDelRey = mock(Escaque.class);
		Rey rey = new Rey(colorCaballo);
		when(tableroSnapshot.getEscaque(rey)).thenReturn(escaqueDelRey);
		Color colorContrincante = colorCaballo.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaqueDelRey, colorContrincante)).thenReturn(true);
		
		caballo.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldCheckPreconditionsWithoutException() {
		caballo.checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test
	public void shouldGetCapturaStrategyDefault() {
		assertThat(caballo.getCapturaStrategy(), instanceOf(CapturaStrategyDefault.class));
	}

}
