package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.Multimap;

public class TableroTest {

	private Tablero tablero;
	private Movimiento movimiento;
	
	private Escaque origen;
	private Escaque destino;
	
	private Trebejo trebejoOrigen;
	private Trebejo trebejoDestino;
	
	@Before
	public void beforeEveryTest() {
		tablero = spy(new TableroMock());
		
		movimiento = mock(Movimiento.class);
		origen = mock(Escaque.class);
		destino = mock(Escaque.class);
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		
		trebejoOrigen = mock(Trebejo.class);
		trebejoDestino = mock(Trebejo.class);
		
		HashMap<Escaque, Trebejo> escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(32);
		escaquesTrebejosMap.put(origen, trebejoOrigen);
		escaquesTrebejosMap.put(destino, trebejoDestino);
		tablero.setEscaquesTrebejosMap(escaquesTrebejosMap);
	}
	
	@Test
	public void shouldCreateTableroWithEmptyTrebejosCapturados() {
		assertThat(tablero.getTrebejosCapturados(), is(empty()));
	}
	
	@Test
	public void shouldCreateTableroWithNoneEscaquesOcupados() {
		tablero = new TableroMock();
		assertThat(tablero.getEscaquesTrebejosMap().size(), is(0));
	}
	
	
	//EjecutarMovimiento Tests
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotEjecutarMovimientoWithNullMovimiento() {
		tablero.ejecutarMovimiento(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldNotEjecutarMovimientoWithNullTrebejoOrigen() {
		when(tablero.getTrebejo(origen)).thenReturn(null);
		tablero.ejecutarMovimiento(movimiento);
	}
	
	@Test
	public void shouldNotAddToDesplazadosOnEmptyDestino() {
		HashMap<Escaque, Trebejo> escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(32);
		escaquesTrebejosMap.put(origen, trebejoOrigen);
		tablero.setEscaquesTrebejosMap(escaquesTrebejosMap);
		
		when(trebejoOrigen.getTrebejoCapturado(any(Tablero.class), any(Movimiento.class))).thenReturn(null);
		
		tablero.ejecutarMovimiento(movimiento);
		assertThat(tablero.getTrebejosCapturados(), is(empty()));
	}
	
	@Test
	public void shouldMoveToDestinoOnEmptyDestino() {
		HashMap<Escaque, Trebejo> escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(32);
		escaquesTrebejosMap.put(origen, trebejoOrigen);
		tablero.setEscaquesTrebejosMap(escaquesTrebejosMap);
		
		tablero.ejecutarMovimiento(movimiento);
		assertThat(tablero.getTrebejo(destino), is(trebejoOrigen));
	}
	
	@Test
	public void shouldNotAddToDesplazadosOnDestinoOcupado() {
		HashMap<Escaque, Trebejo> escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(32);
		escaquesTrebejosMap.put(origen, trebejoOrigen);
		tablero.setEscaquesTrebejosMap(escaquesTrebejosMap);
		
		when(trebejoOrigen.getTrebejoCapturado(any(Tablero.class), any(Movimiento.class))).thenReturn(null);
		
		tablero.ejecutarMovimiento(movimiento);
		assertThat(tablero.getTrebejosCapturados(), is(empty()));
	}
	
	@Test
	public void shouldMoveToDestinoOnDestinoOcupado() {
		HashMap<Escaque, Trebejo> escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(32);
		escaquesTrebejosMap.put(origen, trebejoOrigen);
		tablero.setEscaquesTrebejosMap(escaquesTrebejosMap);
		
		tablero.ejecutarMovimiento(movimiento);
		assertThat(tablero.getTrebejo(destino), is(trebejoOrigen));
	}
	
	@Test
	public void shouldAddTrebejoDestinoToDesplazados() {
		when(trebejoOrigen.getTrebejoCapturado(any(Tablero.class), any(Movimiento.class))).thenReturn(trebejoDestino);
		
		tablero.ejecutarMovimiento(movimiento);
		assertThat(tablero.getTrebejosCapturados(), contains(trebejoDestino));
	}

	@Test
	public void shouldRemoveTrebejoDestinoFromEscaquesTrebejos() {
		tablero.ejecutarMovimiento(movimiento);
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		assertThat(trebejosEscaques.get(trebejoDestino), is(empty()));
	}

	@Test
	public void shouldRemoveTrebejoDesplazadoFromEscaquesTrebejos() {
		tablero.ejecutarMovimiento(movimiento);
		assertThat(tablero.getTrebejo(origen), is(nullValue()));
	}

	
	
	private class TableroMock extends Tablero {
		public TableroMock() {
			setTrebejosCapturados(new ArrayList<Trebejo>(30));
			setEscaquesTrebejosMap(new HashMap<Escaque, Trebejo>(32));
		}
	}
}