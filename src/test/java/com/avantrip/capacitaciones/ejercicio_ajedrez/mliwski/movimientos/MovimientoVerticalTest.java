package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoVerticalTest {
	@Test
	public void shouldReturnTipoVertical() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getTipo(), equalTo(TipoMovimiento.Vertical));
	}
	
	@Test
	public void shouldNotReturnTipoVertical() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',2);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getTipo(), not(equalTo(TipoMovimiento.Vertical)));
	}
	
	@Test
	public void shouldCountVerticalAdelante() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',5);
		Integer cantidadEsperada = 3;
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(cantidadEsperada));
	}
	
	@Test
	public void shouldCountVerticalAtras() {
		Escaque origen = new Escaque('a',5);
		Escaque destino = new Escaque('a',2);
		Integer cantidadEsperada = 3;
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		assertThat(movimiento.getCantidad(), equalTo(cantidadEsperada));
	}
	
	@Test
	public void shouldGetCaminoVacio() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		
		assertThat(movimiento.getCamino(), is(empty()));
	}
	
	@Test
	public void shouldGetCaminoAdelante() {
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',4);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		Escaque escaque_1 = new Escaque('a',3);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoAtras() {
		Escaque origen = new Escaque('c',8);
		Escaque destino = new Escaque('c',1);
		
		Movimiento movimiento = new Movimiento(origen, destino);
		
		Escaque escaque_1 = new Escaque('c',7);
		Escaque escaque_2 = new Escaque('c',6);
		Escaque escaque_3 = new Escaque('c',5);
		Escaque escaque_4 = new Escaque('c',4);
		Escaque escaque_5 = new Escaque('c',3);
		Escaque escaque_6 = new Escaque('c',2);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3, escaque_4, escaque_5, escaque_6));
	}
}
