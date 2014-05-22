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
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('d', 3), DireccionAtaque.Adelante);
		
		alfil.checkPreconditions(movimiento);
	}

	@Test(expected=CaminoOcupadoException.class)
	public void shouldThrowExceptionBecauseCaminoOcupado() {
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('a', 3), DireccionAtaque.Adelante);
		
		alfil.checkPreconditions(movimiento);
	}
	
	@Test(expected=DestinoNoOcupableException.class)
	public void shouldThrowExceptionBecauseDestinoNoOcupable() {
		Trebejo peon_a2 = tablero.getTrebejo(new Escaque('a', 2));
		Trebejo peon_b2 = tablero.getTrebejo(new Escaque('b', 2));
		tablero.moverTrebejoAEscaque(peon_a2, new Escaque('a', 3));
		tablero.moverTrebejoAEscaque(peon_b2, new Escaque('b', 3));
		
		
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('a', 3), DireccionAtaque.Adelante);
		
		alfil.checkPreconditions(movimiento);
	}
	
	@Test
	public void shouldNotThrowException() {
		Trebejo peon_a2 = tablero.getTrebejo(new Escaque('a', 2));
		Trebejo peon_b2 = tablero.getTrebejo(new Escaque('b', 2));
		tablero.moverTrebejoAEscaque(peon_a2, new Escaque('a', 4));
		tablero.moverTrebejoAEscaque(peon_b2, new Escaque('b', 3));
		
		
		movimiento = new Movimiento(new Escaque('c', 1), new Escaque('a', 3), DireccionAtaque.Adelante);
		
		alfil.checkPreconditions(movimiento);
	}

}
