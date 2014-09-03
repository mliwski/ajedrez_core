package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class DireccionAtaqueTest {

	@Test
	public void signoDireccionAtaqueAdelanteShouldBePositive() {
		assertThat(DireccionAtaque.Adelante.getSigno(), is(1));
	}

	@Test
	public void signoDireccionAtaqueAtrasShouldBeNegative() {
		assertThat(DireccionAtaque.Atras.getSigno(), is(-1));
	}
}
