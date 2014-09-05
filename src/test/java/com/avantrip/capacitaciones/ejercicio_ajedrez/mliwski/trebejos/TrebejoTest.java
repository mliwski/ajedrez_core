package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TestUtils;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CaminoOcupadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CantidadMaximaSuperadaException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DestinoNoOcupableException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot.TableroSnapshotBuilder;

//TODO: Mejorar el codigo no son tan claros los metodos de los abstractos
public abstract class TrebejoTest
 {
	private TableroSnapshotBuilder tableroSnapshotBuilder;
	private List<TipoMovimiento> tiposDeMovimiento;

	@Before
	public void beforeEveryTest() {		
		tiposDeMovimiento = Arrays.asList(TipoMovimiento.Vertical, TipoMovimiento.Horizontal, TipoMovimiento.Diagonal, TipoMovimiento.Ele);

		tableroSnapshotBuilder = TestUtils.getWorkingTableroSnapshotBuilder();

		Trebejo trebejo = getTrebejo();
		Escaque escaque = getEscaqueDeTrebejo();
		tableroSnapshotBuilder.with(escaque, trebejo);
	}
	@Test
	public abstract void shouldThrowExceptionBecauseNoColor();

	@Test
	public void preconditionsShouldBeTheSame() {
		List<MovimientoPrecondition> preconditions1 = getTrebejo().getMovimientoPreconditions();
		List<MovimientoPrecondition> preconditions2 = getMismoTrebejoIgualColor().getMovimientoPreconditions();

		assertThat(preconditions1, sameInstance(preconditions2));
	}

	@Test
	public void shouldGetColorOrigen() {
		assertThat(getTrebejo().getColor(), equalTo(getColor()));
	}

	@Test
	public void trebejoWithSameColorShouldBeEquals() {
		assertThat(getTrebejo(), equalTo(getMismoTrebejoIgualColor()));
	}

	@Test
	public void trebejoWithDifferentColorShouldntBeEquals() {
		assertThat(getTrebejo(), not(equalTo(getMismoTrebejoDistintoColor())));
	}

	@Test
	public void shouldGetTrebejoMovido() {
		Trebejo trebejo = getTrebejo();
		trebejo.addMovimiento(getMovimiento());
		assertThat(trebejo.isTrebejoMovido(), equalTo(true));
	}

	@Test
	public void shouldGetTrebejoNoMovido() {
		assertThat(getTrebejo().isTrebejoMovido(), equalTo(false));
	}
	
	@Test(expected=CantidadMaximaSuperadaException.class)
	public void shouldThrowExceptionBecauseCantidadMaximaSuperada() {
		Movimiento movimiento = mock(Movimiento.class);
		when(movimiento.getOrigen()).thenReturn(getOrigen());
		when(movimiento.getDestino()).thenReturn(getDestino());
		when(movimiento.getTipo()).thenReturn(getMovimientosPermitidos().get(0));
		when(movimiento.getCantidad()).thenReturn(getCantidadMaxima()+1);
		
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();		
		getTrebejo().checkPreconditions(tableroSnapshot, movimiento);
	}
	
	//TODO: Mejorar este test
	@Test
	public void shouldThrowExceptionBecauseTipoMovimientoMal() throws Exception {
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();
		Integer movimientosIlegalesDetectados = 0;
		
		List<TipoMovimiento> movimientosIlegales = new ArrayList<TipoMovimiento>(tiposDeMovimiento);
		movimientosIlegales.removeAll(getMovimientosPermitidos());
		for (TipoMovimiento movimientoIlegal : movimientosIlegales) {
			try{
				Movimiento movimiento = mock(Movimiento.class);
				when(movimiento.getOrigen()).thenReturn(getOrigen());
				when(movimiento.getDestino()).thenReturn(getDestino());
				when(movimiento.getTipo()).thenReturn(movimientoIlegal);
				
				getTrebejo().checkPreconditions(tableroSnapshot, movimiento);
			} catch (MovimientoIlegalException movimientoIlegalException) {
				movimientosIlegalesDetectados = movimientosIlegalesDetectados + 1;
			}
		}
		
		assertThat(movimientosIlegalesDetectados, is(movimientosIlegales.size()));
	}
	
	@Test(expected=CaminoOcupadoException.class)
	public void shouldThrowExceptionBecauseCaminoOcupado() {
		TableroSnapshot tableroSnapshot = spy(tableroSnapshotBuilder.build());
		Trebejo trebejoEnCamino = mock(Trebejo.class);
		Escaque escaqueEnCamino = mock(Escaque.class);
		
		Movimiento movimiento = mock(Movimiento.class);
		when(movimiento.getOrigen()).thenReturn(getOrigen());
		when(movimiento.getDestino()).thenReturn(getDestino());
		when(movimiento.getTipo()).thenReturn(getMovimientosPermitidos().get(0));
		
		when(tableroSnapshot.getTrebejo(escaqueEnCamino)).thenReturn(trebejoEnCamino);
		when(movimiento.getCamino()).thenReturn(Arrays.asList(escaqueEnCamino));
		
		getTrebejo().checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test(expected = DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		TableroSnapshot tableroSnapshot = spy(tableroSnapshotBuilder.build());
		Trebejo trebejoPropio = getTrebejo();

		Trebejo trebejoDestino = mock(Trebejo.class);
		Escaque destino = getMovimiento().getDestino();

		when(trebejoDestino.getColor()).thenReturn(getColor());
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);

		trebejoPropio.checkPreconditions(tableroSnapshot, getMovimiento());
	}

	@Test(expected = ReyAmenazadoException.class)
	public void reySeguroTest() throws Exception {
		Trebejo trebejoPropio = getTrebejo();

		Trebejo trebejoContrincante = getTrebejoContrincante();
		Escaque escaqueContrincante = getEscaqueDeTrebejoContrincante();

		tableroSnapshotBuilder.with(escaqueContrincante, trebejoContrincante);
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();

		trebejoPropio.checkPreconditions(tableroSnapshot, getMovimiento());
	}

	@Test
	public void shouldCheckPreconditionsWithoutException() {
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();
		getTrebejo().checkPreconditions(tableroSnapshot, getMovimiento());
		assertThat(true, is(true));
	}

	@Test
	public void shouldNotReturnTrebejoCapturado() {
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();
		Trebejo trebejo = getTrebejo();
		Trebejo trebejoCapturado = trebejo.getTrebejoCapturado(tableroSnapshot, getMovimiento());
		assertThat(trebejoCapturado, is(nullValue()));
	}

	@Test
	public void shouldReturnTrebejoCapturado() {
		Trebejo trebejoContrincante = getTrebejoContrincante();
		Escaque escaqueContrincante = getMovimiento().getDestino();

		tableroSnapshotBuilder.with(escaqueContrincante, trebejoContrincante);
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();
		
		Trebejo trebejoCapturado = getTrebejo().getTrebejoCapturado(tableroSnapshot, getMovimiento());

		assertThat(trebejoCapturado, is(getTrebejoContrincante()));
	}

	protected abstract Color getColor();

	protected abstract List<TipoMovimiento> getMovimientosPermitidos();

	protected abstract Integer getCantidadMaxima();

	protected abstract Trebejo getTrebejo();

	protected abstract Trebejo getMismoTrebejoIgualColor();

	protected abstract Trebejo getMismoTrebejoDistintoColor();

	protected abstract Trebejo getTrebejoContrincante();

	protected abstract Escaque getEscaqueDeTrebejo();

	protected abstract Escaque getEscaqueDeTrebejoContrincante();

	protected final Escaque getOrigen() {
		return getEscaqueDeTrebejo();
	}
	protected abstract Escaque getDestino();
	
	protected final Movimiento getMovimiento() {
		return new Movimiento(getEscaqueDeTrebejo(), getDestino());
	}
	
//	protected TableroSnapshot getTableroSnapshot() {
//		return tableroSnapshot;
//	}
}
