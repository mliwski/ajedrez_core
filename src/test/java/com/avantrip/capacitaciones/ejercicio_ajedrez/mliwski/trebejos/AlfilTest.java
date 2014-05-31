package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CaminoOcupadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DestinoNoOcupableException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoNoPermitidoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public class AlfilTest {

	private Trebejo alfil;
	private Tablero tablero = Tablero.getInstance();

	private Movimiento movimiento;

	@Before
	public void beforeEveryTest() {
		alfil = new Alfil(Color.Blanco);
		tablero.inicializar();
	}
	
	@Test(expected=TipoMovimientoNoPermitidoException.class)
	public void shouldThrowExceptionBecauseMalTipo() {
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('d', 3));
		
		alfil.checkPreconditions(movimiento);
	}

	@Test(expected=CaminoOcupadoException.class)
	public void shouldThrowExceptionBecauseCaminoOcupado() {
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('a', 3));
		
		alfil.checkPreconditions(movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoNoOcupable() {
		Movimiento movimiento_aux_1 = new Movimiento(new Escaque('a', 2), new Escaque('a', 3));
		Movimiento movimiento_aux_2 = new Movimiento(new Escaque('b', 2), new Escaque('b', 3));
		tablero.moverTrebejo(movimiento_aux_1);
		tablero.moverTrebejo(movimiento_aux_2);
		
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('a', 3));
		
		alfil.checkPreconditions(movimiento);
	}
	
	@Test
	public void shouldNotThrowException() {
		Movimiento movimiento_aux_1 = new Movimiento(new Escaque('a', 2), new Escaque('a', 4));
		Movimiento movimiento_aux_2 = new Movimiento(new Escaque('b', 2), new Escaque('b', 3));
		tablero.moverTrebejo(movimiento_aux_1);
		tablero.moverTrebejo(movimiento_aux_2);
		
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('a', 3));
		
		alfil.checkPreconditions(movimiento);
	}

}
