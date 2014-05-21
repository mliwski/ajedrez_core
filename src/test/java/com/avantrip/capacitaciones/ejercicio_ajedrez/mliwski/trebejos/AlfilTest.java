package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class AlfilTest {

	private Trebejo alfil;

	private Movimiento movimiento;
	private List<Escaque> camino;

	@Before
	public void beforeEveryTest() {
		alfil = new Alfil(Color.Negro);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowExceptionBecauseMalTipo() {
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('d', 3), DireccionAtaque.Adelante);
		
		alfil.checkPreconditions(movimiento);
	}

	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowExceptionBecauseCaminoOcupafo() {
		when(movimiento.getTipo()).thenReturn(TipoMovimiento.Vertical);
		
		alfil.checkPreconditions(movimiento);
	}

}
