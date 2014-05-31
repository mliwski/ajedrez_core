package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoEleTest {

	@Test
	public void shouldReturnTipoEleAdelanteDerecha() {
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getTipo(), equalTo(TipoMovimiento.Ele));
	}
	
	@Test
	public void shouldNotReturnTipoEle() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getTipo(), not(equalTo(TipoMovimiento.Ele)));
	}
	
	@Test
	public void shouldCountAtaqueAdelante() {
		Escaque origen = new Escaque('d',3);
		Escaque destino = new Escaque('e',1);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldCountAtaqueAtras() {
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('b',8);
 		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldGetCaminoVacio() {
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('e',7);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		assertThat(movimiento.getCamino(), is(empty()));	
	}
}
