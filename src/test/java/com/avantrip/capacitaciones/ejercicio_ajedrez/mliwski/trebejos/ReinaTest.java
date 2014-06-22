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

public class ReinaTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	
	private Escaque destino;

	private Color colorReina = Color.Blanco;
	private Trebejo reina;

	@Before
	public void beforeEveryTest() {
		
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		reina = new Reina(colorReina);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(reina);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Diagonal);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Reina(null);
	}
	
	@Test
	public void preconditionsShouldBeTheSame() {
		Reina reina1 = new Reina(colorReina);
		List<MovimientoPrecondition> preconditions1 = reina1.getMovimientoPreconditions();
		Reina reina2 = new Reina(colorReina);
		List<MovimientoPrecondition> preconditions2 = reina2.getMovimientoPreconditions();
		
		assertThat(preconditions1, sameInstance(preconditions2));
	}
	
	@Test
	public void trebejoWithSameColorShouldBeEquals() {
		Reina reina1 = new Reina(colorReina);
		Reina reina2 = new Reina(colorReina);
		
		assertThat(reina1, equalTo(reina2));
	}
	
	@Test
	public void trebejoWithDifferentColorShouldntBeEquals() {
		Reina reina1 = new Reina(colorReina);
		Reina reina2 = new Reina(colorReina.getContrincante());
		
		assertThat(reina1, not(equalTo(reina2)));
	}
	
	@Test
	public void shouldGetColorOrigen() {
		assertThat(reina.getColor(), equalTo(colorReina));
	}
	
	@Test
	public void shouldGetTrebejoMovido() {
		reina.addMovimiento(movimiento);
		assertThat(reina.isTrebejoMovido(), equalTo(true));
	}
	
	@Test
	public void shouldGetTrebejoNoMovido() {
		assertThat(reina.isTrebejoMovido(), equalTo(false));
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseTipoMovimientoEle() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Ele);
		reina.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		Trebejo trebejoDestino = mock(Trebejo.class);
		when(trebejoDestino.getColor()).thenReturn(colorReina);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);
		
		reina.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test(expected=ReyAmenazadoException.class)
	public void shouldThrowExceptionBecauseReyExpuesto() {
		Escaque escaqueDelRey = mock(Escaque.class);
		Rey rey = new Rey(colorReina);
		when(tableroSnapshot.getEscaque(rey)).thenReturn(escaqueDelRey);
		Color colorContrincante = colorReina.getContrincante();
		when(tableroSnapshot.isEscaqueAmenazadoPorColor(escaqueDelRey, colorContrincante)).thenReturn(true);
		
		reina.checkPreconditions(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldCheckPreconditionsWithoutException() {
		reina.checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test
	public void shouldGetCapturaStrategyDefault() {
		assertThat(reina.getCapturaStrategy(), instanceOf(CapturaStrategyDefault.class));
	}

}
