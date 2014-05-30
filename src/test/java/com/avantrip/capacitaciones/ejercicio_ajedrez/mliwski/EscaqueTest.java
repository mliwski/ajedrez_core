package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class EscaqueTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildIlegalEscaqueByLetraMinLimit() {
		Character badCharacter = (char) ((int)'a' - 1);
		new Escaque(badCharacter, 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildIlegalEscaqueByLetraMaxLimit() {
		new Escaque('j', 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildIlegalEscaqueByNumeroMinLimit() {
		new Escaque('d', -3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildIlegalEscaqueByNumeroMaxLimit() {
		new Escaque('d', 9);
	}
	
	@Test
	public void shouldReturnDistanciaNumeroGreatherThanZero() {
		Integer distanciaEsperada = 4;
		Escaque escaque_1 = new Escaque('a', 2);
		Escaque escaque_2 = new Escaque('a', 6);

		Integer distanciaNumero = escaque_1.getDistanciaNumero(escaque_2);
		
		assertThat(distanciaNumero, equalTo(distanciaEsperada));
	}
	
	@Test
	public void shouldReturnDistanciaNumeroLowerThanZero() {
		Integer distanciaEsperada = -4;
		Escaque escaque_1 = new Escaque('a', 6);
		Escaque escaque_2 = new Escaque('a', 2);

		Integer distanciaNumero = escaque_1.getDistanciaNumero(escaque_2);
		
		assertThat(distanciaNumero, equalTo(distanciaEsperada));
	}
	
	@Test
	public void shouldReturnDistanciaNumeroEqualToZero() {
		Escaque escaque_1 = new Escaque('a', 8);
		Escaque escaque_2 = new Escaque('b', 8);
		Integer distanciaNumero = escaque_1.getDistanciaNumero(escaque_2);
		
		assertThat(distanciaNumero, equalTo(0));
	}
	
	@Test
	public void shouldReturnDistanciaLetraLowerThanZero() {
		Integer distanciaEsperada = -1;
		Escaque escaque_1 = new Escaque('b', 2);
		Escaque escaque_2 = new Escaque('a', 2);
		Integer distanciaLetra = escaque_1.getDistanciaLetra(escaque_2);
		
		assertThat(distanciaLetra, equalTo(distanciaEsperada));
	}
	
	@Test
	public void shouldReturnDistanciaLetraGreatherThanZero() {
		Integer distanciaEsperada = 1;
		
		Escaque escaque_1 = new Escaque('a', 2);
		Escaque escaque_2 = new Escaque('b', 2);
		Integer distanciaLetra = escaque_1.getDistanciaLetra(escaque_2);
		
		assertThat(distanciaLetra, equalTo(distanciaEsperada));
	}
	
	@Test
	public void shouldReturnDistanciaLetraEqualToZero() {
		Escaque escaque_1 = new Escaque('a', 8);
		Escaque escaque_2 = new Escaque('a', 7);
		Integer distanciaLetra = escaque_1.getDistanciaLetra(escaque_2);
		
		assertThat(distanciaLetra, equalTo(0));
	}
}