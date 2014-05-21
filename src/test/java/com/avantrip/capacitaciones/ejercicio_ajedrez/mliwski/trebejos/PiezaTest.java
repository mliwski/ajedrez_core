package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Pieza;

public class PiezaTest {
	private Pieza pieza;
	
	@Before
	public void setupTest() {
//		pieza = new PiezaMock();
//		pieza.setColor(Color.Blanco);
	}
	
/*	@Test(expected = MovimientoIlegalException.class)
	public void noPuedeMoverAEscaqueConPiezaDeMismoColor() {
		Escaque destino = mock(Escaque.class);
		when(destino.isOcupadoPor(pieza.getColor())).thenReturn(true);
		
		pieza.mover(destino);
	}
	
	@Test(expected = IllegalStateException.class)
	public void noPuedeSetearColorNull() {
		pieza.setColor(null);
	}
	
	private class PiezaMock extends Pieza {
		@Override protected void verificarMovimientoPermitidoParaMi(Escaque destino) {}
		@Override protected void verificarCaminoLibre(Escaque destino) {}
	} */

}
