package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroInstance;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

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

	@Test
	public void shouldBuildCommand() {
		TableroInstance tablero = mock(TableroInstance.class);
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('a',2);
		Trebejo trebejo = mock(Trebejo.class);

		when(tablero.getTrebejo(origen)).thenReturn(trebejo);
		
		new MoverCommand(tablero, origen, destino);
	}

	@Test
	public void notificationsShouldBeEmpty() {
		TableroInstance tablero = mock(TableroInstance.class);
		Escaque origen = new Escaque('a',1);
		Escaque destino = new Escaque('a',2);
		Trebejo trebejo = spy(new TrebejoMock(Color.Blanco));

		when(tablero.getTrebejo(origen)).thenReturn(trebejo);
		when(tablero.getTrebejo(destino)).thenReturn(null);
		
		MoverCommand command = new MoverCommand(tablero, origen, destino);
		List<Notification> notifications = command.ejecutar();
		assertThat(notifications, is(empty()));
	}
	
	private class TrebejoMock extends Trebejo {
		private Trebejo trebejoCapturado = null;
		
		public TrebejoMock(Color color) {
			super(color);
		}

		@Override
		protected List<MovimientoPrecondition> getMovimientoPreconditions() {
			return Collections.emptyList();
		}
		
		@Override
		public Trebejo getTrebejoCapturado(Tablero tablero, Movimiento movimiento) {
			return this.trebejoCapturado;
		}

//		public void setTrebejoCapturado(Trebejo trebejoCapturado) {
//			this.trebejoCapturado = trebejoCapturado;
//		}
		
	}
}
