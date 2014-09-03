package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ColorTest {

	@Test
	public void direccionAtaqueNegroShouldBeAtras() {
		assertThat(Color.Negro.getDireccionAtaque(), equalTo(DireccionAtaque.Atras));
	}

	@Test
	public void direccionAtaqueBlancoShouldBeAdelante() {
		assertThat(Color.Blanco.getDireccionAtaque(), equalTo(DireccionAtaque.Adelante));
	}
	
	@Test
	public void shouldReturnContrincanteBlanco() {
		assertThat(Color.Negro.getContrincante(), equalTo(Color.Blanco));
	}

	@Test
	public void shouldReturnContrincanteNegro() {
		assertThat(Color.Blanco.getContrincante(), equalTo(Color.Negro));
	}

}
