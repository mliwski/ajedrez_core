package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoDesconocidoException;

public class MovimientoTest {

	@Test( expected = IllegalArgumentException.class)
	public void shouldThrowExceptionSameOrigenAndDestino() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',2);
		
		new Movimiento(origen, destino);
	}
	
	@Test(expected=TipoMovimientoDesconocidoException.class)
	public void shouldThrowTipoDesconocidoException() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('h',3);
		
		new Movimiento(origen, destino);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentBecauseNullOrigen() {
		Escaque destino = new Escaque('h',3);
		
		new Movimiento(null, destino);
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentBecauseNullDestino() {
		Escaque origen = new Escaque('a',2);
		
		new Movimiento(origen, null);
	}	
}
