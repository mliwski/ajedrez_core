package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoEleTest {

	@Test
	public void shouldReturnTipoEle() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getTipo(), equalTo(TipoMovimiento.Ele));
	}
	
	@Test
	public void shouldNotReturnTipoEle() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getTipo(), not(equalTo(TipoMovimiento.Ele)));
	}
	
	@Test
	public void shouldCountAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('d',3);
		Escaque destino = new Escaque('e',1);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldCountAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('b',8);
 		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getCantidad(), equalTo(1));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('e',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);

		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Derecha, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteDerechaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('d',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);

		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante, DireccionMovimiento.Derecha));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('a',5);
 		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Derecha, DireccionMovimiento.Adelante));
	}

	@Test
	public void shouldDetectDireccionAdelanteDerechaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('b',4);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante, DireccionMovimiento.Derecha));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('a',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Izquierda, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteIzquierdaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('b',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante, DireccionMovimiento.Izquierda));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAdelanteAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('e',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Izquierda, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteIzquierdaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('d',4);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante, DireccionMovimiento.Izquierda));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('e',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Derecha, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionAtrasDerechaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('d',4);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Atras, DireccionMovimiento.Derecha));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('a',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Derecha, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionAtrasDerechaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('b',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Atras, DireccionMovimiento.Derecha));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtrasAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('a',5);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Izquierda, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionAtrasIzquierdaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('b',4);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Atras, DireccionMovimiento.Izquierda));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtrasAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('e',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Izquierda, DireccionMovimiento.Atras));
	}
	
	@Test
	public void shouldDetectDireccionAtrasIzquierdaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('d',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Atras, DireccionMovimiento.Izquierda));
	}
	
	@Test
	public void shouldGetCaminoVacio() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Escaque origen = new Escaque('c',6);
		Escaque destino = new Escaque('e',7);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		assertThat(movimiento.getCamino(), is(empty()));	
	}
}
