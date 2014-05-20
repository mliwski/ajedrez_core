package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoVerticalTest {
	@Test
	public void shouldReturnTipoVertical() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(TipoMovimiento.Vertical, equalTo(movimiento.getTipo()));
	}
	
	@Test
	public void shouldCountVerticalAdelante() {
		Integer numeroOrigen = 5;
		Integer numeroDestino = 2;
		Integer cantidadAbsoluta = Math.abs(numeroOrigen - numeroDestino);

		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',numeroOrigen);
		Escaque destino = new Escaque('a',numeroDestino);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(cantidadAbsoluta, equalTo(movimiento.getCantidad()));
	}
	
	@Test
	public void shouldCountVerticalAtras() {
		Integer numeroOrigen = 2;
		Integer numeroDestino = 5;
		Integer cantidadAbsoluta = Math.abs(numeroOrigen - numeroDestino);

		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('a',numeroOrigen);
		Escaque destino = new Escaque('a',numeroDestino);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(cantidadAbsoluta, equalTo(movimiento.getCantidad()));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('a',3);
		Escaque destino = new Escaque('a',1);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('c',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('g',4);
		Escaque destino = new Escaque('g',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldGetCaminoVacio() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		
		assertThat(movimiento.getCamino(), is(empty()));
	}
	
	@Test
	public void shouldGetCaminoAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',4);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('a',3);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('d',7);
		Escaque destino = new Escaque('d',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('d',6);
		Escaque escaque_2 = new Escaque('d',5);
		Escaque escaque_3 = new Escaque('d',4);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3));
	}
	
	@Test
	public void shouldGetCaminoAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('c',8);
		Escaque destino = new Escaque('c',1);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('c',7);
		Escaque escaque_2 = new Escaque('c',6);
		Escaque escaque_3 = new Escaque('c',5);
		Escaque escaque_4 = new Escaque('c',4);
		Escaque escaque_5 = new Escaque('c',3);
		Escaque escaque_6 = new Escaque('c',2);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3, escaque_4, escaque_5, escaque_6));
	}
	
	@Test
	public void shouldGetCaminoAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('g',4);
		Escaque destino = new Escaque('g',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('g',5);
		Escaque escaque_2 = new Escaque('g',6);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2));
	}
	
}
