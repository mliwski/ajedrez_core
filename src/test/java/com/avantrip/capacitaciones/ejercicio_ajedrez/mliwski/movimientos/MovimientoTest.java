package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoDesconocidoException;

public class MovimientoTest {

	@Test( expected = IllegalArgumentException.class)
	public void shouldNotReturnSameOrigenAndDestino() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',2);
		
		new Movimiento(origen, destino, direccion);
	}
	
	@Test
	public void shouldReturnTipoVertical() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('a',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(TipoMovimiento.Vertical, equalTo(movimiento.getTipo()));
	}
	
	@Test
	public void shouldReturnTipoHorizontal() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(TipoMovimiento.Horizontal, equalTo(movimiento.getTipo()));
	}
	
	@Test
	public void shouldReturnTipoDiagonal() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('b',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(TipoMovimiento.Diagonal, equalTo(movimiento.getTipo()));
	}
	
	@Test
	public void shouldReturnTipoEle() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('c',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(TipoMovimiento.Ele, equalTo(movimiento.getTipo()));
	}
	
	@Test( expected = TipoMovimientoDesconocidoException.class)
	public void shouldThrowTipoMovimientoDesconocidoException() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('d',4);
		
		new Movimiento(origen, destino, direccion);
	}
	
	@Test
	public void shouldCountVertical() {
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
	public void shouldCountHorizontal() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		
		Character letraOrigen = 'h';
		Escaque origen = new Escaque(letraOrigen, 2);

		Character letraDestino = 'c';
		Escaque destino = new Escaque(letraDestino, 2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		Integer cantidadAbsoluta = Math.abs(letraOrigen.compareTo(letraDestino));
		
		assertThat(cantidadAbsoluta, equalTo(movimiento.getCantidad()));
	}
	
	@Test
	public void shouldCountDiagonal() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;

		Character letraOrigen = 'a';
		Integer numeroOrigen = 1;
		Escaque origen = new Escaque(letraOrigen, numeroOrigen);
		
		Character letraDestino = 'h';
		Integer numeroDestino = 8;
		Escaque destino = new Escaque(letraDestino, numeroDestino);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		Integer cantidadAbsoluta = Math.abs(letraOrigen.compareTo(letraDestino));
		
		assertThat(cantidadAbsoluta, equalTo(movimiento.getCantidad()));
	}
	
	@Test
	public void shouldCountEle() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('c',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(1, equalTo(movimiento.getCantidad()));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',2);
		Escaque destino = new Escaque('c',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), hasItem(DireccionMovimiento.Derecha));
	}
	
	@Test
	public void shouldDetectDireccionDerechaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('c',3);
		Escaque destino = new Escaque('a',2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), hasItem(DireccionMovimiento.Derecha));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('f',2);
		Escaque destino = new Escaque('e',3);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), hasItem(DireccionMovimiento.Izquierda));
	}
	
	@Test
	public void shouldDetectDireccionIzquierdaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('e',3);
		Escaque destino = new Escaque('f',2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), hasItem(DireccionMovimiento.Izquierda));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('e',5);
		Escaque destino = new Escaque('e',8);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), hasItem(DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteDerechaAtaqueAdelante() {
		DireccionAtaque direccion = DireccionAtaque.Adelante;
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('b',2);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Derecha, DireccionMovimiento.Adelante));
	}
	
	@Test
	public void shouldDetectDireccionAdelanteIzquierdaAtaqueAtras() {
		DireccionAtaque direccion = DireccionAtaque.Atras;
		Escaque origen = new Escaque('d',8);
		Escaque destino = new Escaque('e',6);
		
		Movimiento movimiento = new Movimiento(origen, destino, direccion);
		
		assertThat(movimiento.getDireccion(), contains(DireccionMovimiento.Adelante, DireccionMovimiento.Izquierda));
	}
	
	
}
