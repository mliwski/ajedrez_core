package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
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
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DireccionNoPermitidaException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoNoPermitidoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;

public class PeonTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	
	private Escaque destino;

	private Color colorPeon = Color.Blanco;
	private Trebejo peon;

	@Before
	public void beforeEveryTest() {
		
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		peon = new Peon(colorPeon);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(peon);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		when(origen.getDistanciaNumero(destino)).thenReturn(1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Peon(null);
	}
	
	@Test
	public void preconditionsShouldBeTheSame() {
		Peon peon1 = new Peon(colorPeon);
		List<MovimientoPrecondition> preconditions1 = peon1.getMovimientoPreconditions();
		Peon peon2 = new Peon(colorPeon);
		List<MovimientoPrecondition> preconditions2 = peon2.getMovimientoPreconditions();
		
		assertThat(preconditions1, sameInstance(preconditions2));
	}
	
	@Test
	public void shouldGetColorOrigen() {
		assertThat(peon.getColor(), equalTo(colorPeon));
	}
	
	@Test
	public void shouldGetTrebejoMovido() {
		peon.addMovimiento(movimiento);
		assertThat(peon.isTrebejoMovido(), equalTo(true));
	}
	
	@Test
	public void shouldGetTrebejoNoMovido() {
		assertThat(peon.isTrebejoMovido(), equalTo(false));
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoEle() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Ele);
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoHorizontal() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Horizontal);
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoDiagonal() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(null);
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=CantidadMaximaSuperadaException.class)
	public void shouldThrowExceptionBecauseCantidadMaximaSuperada() {
		when(movimiento.getCantidad()).thenReturn(3);
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=CantidadMaximaSuperadaException.class)
	public void shouldThrowExceptionBecauseCantidadMaximaSuperadaOnMoved() {
		when(movimiento.getCantidad()).thenReturn(2);
		peon.addMovimiento(movimiento);
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=DireccionNoPermitidaException.class)
	public void shouldThrowExceptionBecauseDireccionNoPermitida() {
		when(origen.getDistanciaNumero(destino)).thenReturn(-1);
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test
	public void shouldNotThrowExceptionOnNotMoved() {
		when(movimiento.getCantidad()).thenReturn(2);
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		Trebejo trebejoDestino = mock(Trebejo.class);
		when(trebejoDestino.getColor()).thenReturn(colorPeon);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=ReyAmenazadoException.class)
	public void shouldThrowExceptionBecauseReyExpuesto() {
		Escaque escaqueDelRey = mock(Escaque.class);
		when(tableroSnapshot.getEscaqueDelRey(colorPeon)).thenReturn(escaqueDelRey);
		Color colorContrincante = colorPeon.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaqueDelRey, colorContrincante)).thenReturn(true);
		
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldCheckPreconditionsWithoutException() {
		peon.checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test
	public void shouldGetCapturaStrategyDefault() {
		assertThat(peon.getCapturaStrategy(), instanceOf(CapturaStrategyDefault.class));
	}

}
