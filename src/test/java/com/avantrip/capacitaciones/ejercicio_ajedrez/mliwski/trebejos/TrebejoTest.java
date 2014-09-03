package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CaminoOcupadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CantidadMaximaSuperadaException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DestinoNoOcupableException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;

//TODO: Mejorar el codigo no son tan claros los metodos de los abstractos
public abstract class TrebejoTest {
	private Object semaphore = new Object();

	private static Map<Color, Rey> mapaTrebejosRey = null;
	private static Map<Color, Escaque> mapaEscaquesRey = null;

	private static List<TipoMovimiento> tiposDeMovimiento = null;

	private TableroSnapshot tableroSnapshot;

	@Before
	public void beforeEveryTest() {		
		synchronized (semaphore) {
			if (mapaTrebejosRey == null) {
				Rey reyBlanco = new Rey(Color.Blanco);
				Escaque escaqueReyBlanco = new Escaque('e', 1);

				Rey reyNegro = new Rey(Color.Negro);
				Escaque escaqueReyNegro = new Escaque('e', 8);

				mapaTrebejosRey = ImmutableMap.of(Color.Blanco, reyBlanco, Color.Negro, reyNegro);
				mapaEscaquesRey = ImmutableMap.of(Color.Blanco, escaqueReyBlanco, Color.Negro, escaqueReyNegro);

				tiposDeMovimiento = Arrays.asList(TipoMovimiento.Vertical, TipoMovimiento.Horizontal, TipoMovimiento.Diagonal, TipoMovimiento.Ele);
			}
		}
		
		Trebejo trebejo = getTrebejo();
		Escaque escaque = getEscaqueDeTrebejo();

		Color color = getColor();
		Color colorContrincante = color.getContrincante();

		Escaque escaqueReyPropio = mapaEscaquesRey.get(color);
		Rey reyPropio = mapaTrebejosRey.get(color);
		Escaque escaqueReyContrincante = mapaEscaquesRey.get(colorContrincante);
		Rey reyContrincante = mapaTrebejosRey.get(colorContrincante);

		Map<Escaque, Trebejo> escaquesTrebejos = new HashMap<Escaque, Trebejo>();
		escaquesTrebejos.put(escaqueReyPropio, reyPropio);
		escaquesTrebejos.put(escaqueReyContrincante, reyContrincante);
		escaquesTrebejos.put(escaque, trebejo);

		tableroSnapshot = buildTableroMock(escaquesTrebejos);

	}
	
	private TableroSnapshot buildTableroMock(Map<Escaque, Trebejo> escaquesTrebejos) {
		TableroSnapshot tablero = mock(TableroSnapshot.class);

		SetMultimap<Escaque, Trebejo> escaquesTrebejosMultimap = Multimaps .forMap(escaquesTrebejos);
		Multimap<Trebejo, Escaque> trebejosEscaques = Multimaps.invertFrom(escaquesTrebejosMultimap, HashMultimap.<Trebejo, Escaque> create());
		when(tablero.getTrebejosEscaques()).thenReturn(trebejosEscaques);

		Map<Escaque, Trebejo> escaquesTrebejosCopy = Maps.newHashMap(escaquesTrebejos);
		when(tablero.getEscaquesTrebejosMap()).thenReturn(escaquesTrebejosCopy);

		when(tablero.getTrebejosCapturados()).thenReturn(Collections.<Trebejo> emptyList());

		for (Entry<Escaque, Trebejo> escaqueTrebejo : escaquesTrebejos.entrySet()) {
			Escaque escaque = escaqueTrebejo.getKey();
			Trebejo trebejo = escaqueTrebejo.getValue();
			when(tablero.getTrebejo(escaque)).thenReturn(trebejo);
		}

		return tablero;
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
		
		getTrebejo().checkPreconditions(tableroSnapshot, movimiento);
	}
	
	//TODO: Mejorar este test
	@Test
	public void shouldThrowExceptionBecauseTipoMovimientoMal() throws Exception {
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
		Trebejo trebejoEnCamino = mock(Trebejo.class);
		Escaque escaqueEnCamino = mock(Escaque.class);
		
		Movimiento movimiento = mock(Movimiento.class);
		when(movimiento.getOrigen()).thenReturn(getOrigen());
		when(movimiento.getDestino()).thenReturn(getDestino());
		when(movimiento.getTipo()).thenReturn(getMovimientosPermitidos().get(0));
		
		Trebejo trebejoPropio = getTrebejo();
		Escaque escaquePropio = getEscaqueDeTrebejo();

		Color colorPropio = trebejoPropio.getColor();
		Color colorContrincante = colorPropio.getContrincante();

		Escaque escaqueReyPropio = mapaEscaquesRey.get(colorPropio);
		Rey reyPropio = mapaTrebejosRey.get(colorPropio);
		Escaque escaqueReyContrincante = mapaEscaquesRey.get(colorContrincante);
		Rey reyContrincante = mapaTrebejosRey.get(colorContrincante);

		Map<Escaque, Trebejo> escaquesTrebejos = new HashMap<Escaque, Trebejo>();
		escaquesTrebejos.put(escaqueReyPropio, reyPropio);
		escaquesTrebejos.put(escaqueReyContrincante, reyContrincante);
		escaquesTrebejos.put(escaquePropio, trebejoPropio);

		TableroSnapshot tableroSnapshot = buildTableroMock(escaquesTrebejos);
		
		when(tableroSnapshot.getTrebejo(escaqueEnCamino)).thenReturn(trebejoEnCamino);
		when(movimiento.getCamino()).thenReturn(Arrays.asList(escaqueEnCamino));
		
		getTrebejo().checkPreconditions(tableroSnapshot, movimiento);
	}

	@Test(expected = DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoMismoColor() {
		Trebejo trebejoPropio = getTrebejo();
		Color colorPropio = trebejoPropio.getColor();

		Trebejo trebejoDestino = mock(Trebejo.class);

		Escaque destino = getMovimiento().getDestino();

		when(trebejoDestino.getColor()).thenReturn(colorPropio);
		when(tableroSnapshot.getTrebejo(destino)).thenReturn(trebejoDestino);

		trebejoPropio.checkPreconditions(tableroSnapshot, getMovimiento());
	}

	@Test(expected = ReyAmenazadoException.class)
	public void reySeguroTest() throws Exception {
		Trebejo trebejoPropio = getTrebejo();
		Escaque escaquePropio = getEscaqueDeTrebejo();

		Trebejo trebejoContrincante = getTrebejoContrincante();
		Escaque escaqueContrincante = getEscaqueDeTrebejoContrincante();

		Color colorPropio = trebejoPropio.getColor();
		Color colorContrincante = colorPropio.getContrincante();

		Escaque escaqueReyPropio = mapaEscaquesRey.get(colorPropio);
		Rey reyPropio = mapaTrebejosRey.get(colorPropio);
		Escaque escaqueReyContrincante = mapaEscaquesRey.get(colorContrincante);
		Rey reyContrincante = mapaTrebejosRey.get(colorContrincante);

		Map<Escaque, Trebejo> escaquesTrebejos = new HashMap<Escaque, Trebejo>();
		escaquesTrebejos.put(escaqueReyPropio, reyPropio);
		escaquesTrebejos.put(escaqueReyContrincante, reyContrincante);
		escaquesTrebejos.put(escaqueContrincante, trebejoContrincante);
		escaquesTrebejos.put(escaquePropio, trebejoPropio);

		TableroSnapshot tableroSnapshot = new TableroSnapshot(escaquesTrebejos, new ArrayList<Trebejo>());
		trebejoPropio.checkPreconditions(tableroSnapshot, getMovimiento());
	}

	@Test
	public void shouldCheckPreconditionsWithoutException() {
		getTrebejo().checkPreconditions(tableroSnapshot, getMovimiento());
		assertThat(true, is(true));
	}

	@Test
	public void shouldNotReturnTrebejoCapturado() {
		Trebejo trebejoCapturado = getTrebejo().getTrebejoCapturado(tableroSnapshot, getMovimiento());
		assertThat(trebejoCapturado, is(nullValue()));
	}

	@Test
	public void shouldReturnTrebejoCapturado() {
		Trebejo trebejoPropio = getTrebejo();
		Escaque escaquePropio = getEscaqueDeTrebejo();

		Trebejo trebejoContrincante = getTrebejoContrincante();
		Escaque escaqueContrincante = getMovimiento().getDestino();

		Color colorPropio = trebejoPropio.getColor();
		Color colorContrincante = colorPropio.getContrincante();

		Escaque escaqueReyPropio = mapaEscaquesRey.get(colorPropio);
		Rey reyPropio = mapaTrebejosRey.get(colorPropio);
		Escaque escaqueReyContrincante = mapaEscaquesRey.get(colorContrincante);
		Rey reyContrincante = mapaTrebejosRey.get(colorContrincante);

		Map<Escaque, Trebejo> escaquesTrebejos = new HashMap<Escaque, Trebejo>();
		escaquesTrebejos.put(escaqueReyPropio, reyPropio);
		escaquesTrebejos.put(escaqueReyContrincante, reyContrincante);
		escaquesTrebejos.put(escaqueContrincante, trebejoContrincante);
		escaquesTrebejos.put(escaquePropio, trebejoPropio);

		TableroSnapshot tableroSnapshot = buildTableroMock(escaquesTrebejos);
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
	
	protected TableroSnapshot getTableroSnapshot() {
		return tableroSnapshot;
	}
}
