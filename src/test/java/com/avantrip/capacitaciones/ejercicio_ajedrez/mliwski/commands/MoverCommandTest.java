package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroInstance;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TestUtils;

public class MoverCommandTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildCommandWithoutTablero() {
		new MoverCommand(null, mock(Escaque.class), mock(Escaque.class));
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildCommandWithoutOrigen() {
		new MoverCommand(mock(TableroInstance.class), null, mock(Escaque.class));
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildCommandWithoutDestino() {
		new MoverCommand(mock(TableroInstance.class), mock(Escaque.class), null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotBuildCommandWithSameOrigenDestino() {
		Escaque escaque = mock(Escaque.class);
		new MoverCommand(mock(TableroInstance.class), escaque, escaque);
	}

	@Test(expected=IllegalStateException.class)
	public void shouldNotBuildCommandWithoutTrebejoEnOrigen() {
		TableroInstance tablero = mock(TableroInstance.class);
		Escaque origen = mock(Escaque.class);
		Escaque destino = mock(Escaque.class);

		when(tablero.getTrebejo(origen)).thenReturn(null);
		
		new MoverCommand(tablero, origen, destino);
	}

}
