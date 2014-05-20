package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoDiagonalTest {
	
	@Test
	public void shouldReturnTipoDiagonal() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getTipo(), equalTo(TipoMovimiento.Diagonal));
	}
	
	@Test
	public void shouldNotReturnTipoDiagonal() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('c',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getTipo(), not(equalTo(TipoMovimiento.Diagonal)));
	}
	
	@Test
	public void shouldCountDerechaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('h',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(7));
	}
	
	@Test
	public void shouldCountDerechaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('f',5);
		Escaque destino = new Escaque('b',1);
 		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(4));
	}
	
	@Test
	public void shouldCountIzquierdaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('d',4);
		Escaque destino = new Escaque('f',6);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(2));
	}
	
	@Test
	public void shouldCountIzquierdaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('b',8);
		Escaque destino = new Escaque('g',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(5));
	}
	
	@Test
	public void shouldCountDerechaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('d',5);
		Escaque destino = new Escaque('c',4);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldCountDerechaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('b',3);
		Escaque destino = new Escaque('a',2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldCountIzquierdaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldCountIzquierdaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('h',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(7));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('a',5);
		Escaque destino = new Escaque('c',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Derecha, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('f',5);
		Escaque destino = new Escaque('b',1);
 		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Derecha, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('e',4);
		Escaque destino = new Escaque('d',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Izquierda, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('b',8);
		Escaque destino = new Escaque('g',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Izquierda, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('f',7);
		Escaque destino = new Escaque('h',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Derecha, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('e',2);
		Escaque destino = new Escaque('b',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Derecha, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('g',5);
		Escaque destino = new Escaque('c',1);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Izquierda, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('h',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), containsInAnyOrder(DireccionMovimiento.Izquierda, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldGetCaminoDerechaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('a',5);
		Escaque destino = new Escaque('c',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('b',6);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoDerechaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('f',5);
		Escaque destino = new Escaque('b',1);
 		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('e',4);
		Escaque escaque_2 = new Escaque('d',3);
		Escaque escaque_3 = new Escaque('c',2);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3));
	}
	
	@Test
	public void shouldGetCaminoIzquierdaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('e',4);
		Escaque destino = new Escaque('c',6);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('d',5);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoIzquierdaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('b',8);
		Escaque destino = new Escaque('g',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('c',7);
		Escaque escaque_2 = new Escaque('d',6);
		Escaque escaque_3 = new Escaque('e',5);
		Escaque escaque_4 = new Escaque('f',4);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3, escaque_4));
	}
	
	@Test
	public void shouldGetCaminoDerechaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('f',7);
		Escaque destino = new Escaque('h',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('g',6);
		
		assertThat(movimiento.getCamino(), contains(escaque_1));
	}
	
	@Test
	public void shouldGetCaminoDerechaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('e',2);
		Escaque destino = new Escaque('b',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('d',3);
		Escaque escaque_2 = new Escaque('c',4);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2));
	}
	
	@Test
	public void shouldGetCaminoIzquierdaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('g',5);
		Escaque destino = new Escaque('c',1);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('f',4);
		Escaque escaque_2 = new Escaque('e',3);
		Escaque escaque_3 = new Escaque('d',2);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3));
}
	
	@Test
	public void shouldGetCaminoIzquierdaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('h',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		Escaque escaque_1 = new Escaque('b',2);
		Escaque escaque_2 = new Escaque('c',3);
		Escaque escaque_3 = new Escaque('d',4);
		Escaque escaque_4 = new Escaque('e',5);
		Escaque escaque_5 = new Escaque('f',6);
		Escaque escaque_6 = new Escaque('g',7);
		
		assertThat(movimiento.getCamino(), contains(escaque_1, escaque_2, escaque_3, escaque_4, escaque_5, escaque_6));
	}
}
