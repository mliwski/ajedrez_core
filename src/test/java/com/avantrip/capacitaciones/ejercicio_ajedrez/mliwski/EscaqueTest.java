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
		Integer numeroEscaque_1 = 2;
		Integer numeroEscaque_2 = 6;
		Integer distanciaEsperada = numeroEscaque_1 - numeroEscaque_2;
		
		Escaque escaque_1 = new Escaque('a', numeroEscaque_1);
		Escaque escaque_2 = new Escaque('a', numeroEscaque_2);
		Integer distanciaNumero = escaque_1.getDistanciaNumero(escaque_2);
		
		assertThat(distanciaNumero, equalTo(distanciaEsperada));
	}
	
	@Test
	public void shouldReturnDistanciaNumeroGreatherThanZeroInverted() {
		Integer numeroEscaque_1 = 6;
		Integer numeroEscaque_2 = 2;
		Integer distanciaEsperada = numeroEscaque_1 - numeroEscaque_2;

		Escaque escaque_1 = new Escaque('a', numeroEscaque_1);
		Escaque escaque_2 = new Escaque('a', numeroEscaque_2);
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
	public void shouldReturnDistanciaLetraGreatherThanZero() {
		Character letraEscaque_1 = 'b';
		Character letraEscaque_2 = 'a';
		Integer distanciaEsperada = (int)letraEscaque_1 - (int)letraEscaque_2;
		
		Escaque escaque_1 = new Escaque(letraEscaque_1, 2);
		Escaque escaque_2 = new Escaque(letraEscaque_2, 2);
		Integer distanciaLetra = escaque_1.getDistanciaLetra(escaque_2);
		
		assertThat(distanciaLetra, equalTo(distanciaEsperada));
	}
	
	@Test
	public void shouldReturnDistanciaLetraGreatherThanZeroInverted() {
		Character letraEscaque_1 = 'a';
		Character letraEscaque_2 = 'b';
		Integer distanciaEsperada = (int)letraEscaque_1 - (int)letraEscaque_2;
		
		Escaque escaque_1 = new Escaque(letraEscaque_1, 2);
		Escaque escaque_2 = new Escaque(letraEscaque_2, 2);
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
	
	@Test
	public void shouldCompareGreaterByNumero() {
		Escaque escaque_1 = new Escaque('a', 8);
		Escaque escaque_2 = new Escaque('a', 7);
		
		assertThat(escaque_1.compareTo(escaque_2), equalTo(1));
	}
	
	@Test
	public void shouldCompareLowerByNumero() {
		Escaque escaque_1 = new Escaque('a', 1);
		Escaque escaque_2 = new Escaque('a', 5);
		
		assertThat(escaque_1.compareTo(escaque_2), equalTo(-1));
	}

	@Test
	public void shouldCompareGreaterByLetra() {
		Escaque escaque_1 = new Escaque('c', 7);
		Escaque escaque_2 = new Escaque('a', 7);
		
		assertThat(escaque_1.compareTo(escaque_2), equalTo(1));
	}
	
	@Test
	public void shouldCompareLowerByLetra() {
		Escaque escaque_1 = new Escaque('a', 1);
		Escaque escaque_2 = new Escaque('a', 5);
		
		assertThat(escaque_1.compareTo(escaque_2), equalTo(-1));
	}
	
	@Test
	public void shouldCompareGreaterByNumeroLetra() {
		Escaque escaque_1 = new Escaque('h', 8);
		Escaque escaque_2 = new Escaque('a', 1);
		
		assertThat(escaque_1.compareTo(escaque_2), equalTo(1));
	}
	
	@Test
	public void shouldCompareLowerByNumeroLetra() {
		Escaque escaque_1 = new Escaque('a', 1);
		Escaque escaque_2 = new Escaque('h', 8);
		
		assertThat(escaque_1.compareTo(escaque_2), equalTo(-1));
	}
	
	@Test
	public void shouldCompareEquals() {
		Escaque escaque_1 = new Escaque('a', 7);
		Escaque escaque_2 = new Escaque('a', 7);
		
		assertThat(escaque_1.compareTo(escaque_2), equalTo(0));
	}
}