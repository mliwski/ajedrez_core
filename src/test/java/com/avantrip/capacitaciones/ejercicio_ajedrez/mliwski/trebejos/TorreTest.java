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

public class TorreTest {

//	private Trebejo torre;
//	private Tablero tablero = Tablero.getInstance();
//
//	private Movimiento movimiento;
//
//	@Before
//	public void beforeEveryTest() {
//		torre = new Torre(Color.Blanco);
//		tablero.inicializar();
//	}
//	
//	@Test(expected=TipoMovimientoNoPermitidoException.class)
//	public void shouldThrowExceptionBecauseMalTipo() {
//		movimiento = new Movimiento(new Escaque('c', 4), new Escaque('d', 3));
//		
//		torre.checkPreconditions(movimiento);
//	}
//
//	@Test(expected=CaminoOcupadoException.class)
//	public void shouldThrowExceptionBecauseCaminoOcupado() {
//		movimiento = new Movimiento(new Escaque('h', 1), new Escaque('h', 3));
//		
//		torre.checkPreconditions(movimiento);
//	}
//	
//	@Test(expected=DestinoNoOcupableException.class)
//	public void shouldThrowExceptionBecauseDestinoNoOcupable() {
//		movimiento = new Movimiento(new Escaque('h', 1), new Escaque('h', 2));
//		
//		torre.checkPreconditions(movimiento);
//	}
//	
//	@Test
//	public void shouldNotThrowExceptionOnVertical() {
//		tablero.moverTrebejo(new Movimiento(new Escaque('h', 2), new Escaque('h', 6)));
//
//		movimiento = new Movimiento(new Escaque('h', 1), new Escaque('h', 5));
//		
//		torre.checkPreconditions(movimiento);
//	}
//	
//	@Test
//	public void shouldNotThrowExceptionOnHorizontal() {
//		tablero.moverTrebejo(new Movimiento(new Escaque('g', 1), new Escaque('g', 5)));
//		tablero.moverTrebejo(new Movimiento(new Escaque('f', 1), new Escaque('f', 5)));
//		
//		movimiento = new Movimiento(new Escaque('h', 1), new Escaque('f', 1));
//		
//		torre.checkPreconditions(movimiento);
//	}

}
