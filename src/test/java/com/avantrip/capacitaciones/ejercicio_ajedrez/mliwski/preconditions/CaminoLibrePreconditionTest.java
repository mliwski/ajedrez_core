package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class CaminoLibrePreconditionTest {

	private Tablero unTablero;
	private Movimiento unMovimiento;
	private List<Escaque> camino;
	private Trebejo unTrebejo;
	private MovimientoPrecondition caminoLibrePrecondition;

	@Before
	public void beforeEveryTest() {
		unTablero = mock(Tablero.class);
		unMovimiento = mock(Movimiento.class);
		
		Escaque escaque_1 = mock(Escaque.class);
		Escaque escaque_2 = mock(Escaque.class);
		Escaque escaque_3 = mock(Escaque.class);
		camino = Arrays.asList(escaque_1, escaque_2, escaque_3);
		
		unTrebejo = mock(Trebejo.class);

		when(unMovimiento.getCamino()).thenReturn(camino);
		
		caminoLibrePrecondition = new CaminoLibrePrecondition();
		caminoLibrePrecondition.setTablero(unTablero);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Escaque escaque_2 = camino.get(1);
		when(unTablero.getTrebejo(escaque_2)).thenReturn(unTrebejo);
		
		caminoLibrePrecondition.check(unMovimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseCaminoLibre() {
		caminoLibrePrecondition.check(unMovimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseEmptyCamino() {
		when(unMovimiento.getCamino()).thenReturn(Collections.<Escaque>emptyList());
		
		caminoLibrePrecondition.check(unMovimiento);
	}

}
