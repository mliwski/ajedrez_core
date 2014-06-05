package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.CaminoLibrePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class CaminoLibrePreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	private List<Escaque> camino;
	private Trebejo trebejo;
	private MovimientoPrecondition caminoLibrePrecondition;

	@Before
	public void beforeEveryTest() {
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		Escaque escaque_1 = mock(Escaque.class);
		Escaque escaque_2 = mock(Escaque.class);
		Escaque escaque_3 = mock(Escaque.class);
		camino = Arrays.asList(escaque_1, escaque_2, escaque_3);
		
		trebejo = mock(Trebejo.class);

		when(movimiento.getCamino()).thenReturn(camino);
		
		caminoLibrePrecondition = new CaminoLibrePrecondition();
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Escaque escaque_2 = camino.get(1);
		when(tableroSnapshot.getTrebejo(escaque_2)).thenReturn(trebejo);
		
		caminoLibrePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseCaminoLibre() {
		caminoLibrePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionBecauseEmptyCamino() {
		when(movimiento.getCamino()).thenReturn(Collections.<Escaque>emptyList());
		
		caminoLibrePrecondition.check(tableroSnapshot, movimiento);
	}

}
