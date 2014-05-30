package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoDiagonalTest {
	
	@Test
	public void shouldReturnTipoDiagonal() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getTipo(), equalTo(TipoMovimiento.Diagonal));
	}
	
	@Test
	public void shouldNotReturnTipoDiagonal() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('c',3);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getTipo(), not(equalTo(TipoMovimiento.Diagonal)));
	}
	
	@Test
	public void shouldCountDerechaAdelante() {
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('h',8);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(7));
	}
	
	@Test
	public void shouldCountIzquierdaAdelante() {
		Escaque origen = new Escaque('d',4);
		Escaque destino = new Escaque('f',6);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(2));
	}
	
	@Test
	public void shouldCountDerechaAtras() {
		Escaque origen = new Escaque('d',5);
		Escaque destino = new Escaque('c',4);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldCountIzquierdaAtras() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
		
	@Test
	public void shouldGetCaminoDerechaAdelante() {
		Escaque origen = new Escaque('a',5);
		Escaque destino = new Escaque('c',7);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		Escaque escaque_1 = new Escaque('b',6);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoIzquierdaAdelante() {
		Escaque origen = new Escaque('e',4);
		Escaque destino = new Escaque('c',6);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		Escaque escaque_1 = new Escaque('d',5);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoDerechaAtras() {
		Escaque origen = new Escaque('f',7);
		Escaque destino = new Escaque('h',5);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		Escaque escaque_1 = new Escaque('g',6);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoIzquierdaAtras() {
		Escaque origen = new Escaque('g',5);
		Escaque destino = new Escaque('c',1);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		Escaque escaque_1 = new Escaque('f',4);
		Escaque escaque_2 = new Escaque('e',3);
		Escaque escaque_3 = new Escaque('d',2);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3));
	}
}