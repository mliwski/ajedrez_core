package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoHorizontalTest {
	@Test
	public void shouldReturnTipoHorizontal() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(TipoMovimiento.Horizontal, equalTo(movimiento.getTipo()));
	}
	
	@Test
	public void shouldCountHorizontalDerecha() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Character letraOrigen = 'a';
		Escaque origen = new Escaque(letraOrigen, 2);

		Character letraDestino = 'c';
		Escaque destino = new Escaque(letraDestino, 2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		Integer cantidadAbsoluta = Math.abs(letraOrigen.compareTo(letraDestino));
		
		assertThat(cantidadAbsoluta, equalTo(movimiento.getCantidad()));
	}
	
	@Test
	public void shouldCountHorizontalIzquierda() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Character letraOrigen = 'h';
		Escaque origen = new Escaque(letraOrigen, 2);

		Character letraDestino = 'b';
		Escaque destino = new Escaque(letraDestino, 2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		Integer cantidadAbsoluta = Math.abs(letraOrigen.compareTo(letraDestino));
		
		assertThat(cantidadAbsoluta, equalTo(movimiento.getCantidad()));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante));
	}

	@Test
	public void shouldDetectDireccionDerechaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('c',1);
		Escaque destino = new Escaque('a',1);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Derecha));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('d',3);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Izquierda));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('a',3);
		Escaque destino = new Escaque('a',1);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldGetCaminoVacio() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('c',2);
		Escaque destino = new Escaque('d',2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCamino(), is(empty()));
	}
	
	@Test
	public void shouldGetCaminoDerechaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('c',5);
		Escaque destino = new Escaque('e',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('d',5);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoDerechaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('f',5);
		Escaque destino = new Escaque('d',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('e',5);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoIzquierdaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('d',7);
		Escaque destino = new Escaque('a',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('c',7);
		Escaque escaque_2 = new Escaque('b',7);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2));
	}
	
	@Test
	public void shouldGetCaminoIzquierdaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('d',7);
		Escaque destino = new Escaque('a',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('c',7);
		Escaque escaque_2 = new Escaque('b',7);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2));
	}
}