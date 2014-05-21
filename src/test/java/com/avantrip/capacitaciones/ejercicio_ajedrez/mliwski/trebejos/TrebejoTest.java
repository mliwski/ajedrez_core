package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class TrebejoTest {
	private Trebejo trebejo;
	
	@Before
	public void setupTest() {
//		trebejo = new TrebejoMock();
//		trebejo.setColor(Color.Blanco);
	}
	
/*	@Test(expected = MovimientoIlegalException.class)
	public void noPuedeMoverAEscaqueConTrebejoDeMismoColor() {
		Escaque destino = mock(Escaque.class);
		when(destino.isOcupadoPor(trebejo.getColor())).thenReturn(true);
		
		trebejo.mover(destino);
	}
	
	@Test(expected = IllegalStateException.class)
	public void noPuedeSetearColorNull() {
		trebejo.setColor(null);
	}
	
	private class TrebejoMock extends Trebejo {
		@Override protected void verificarMovimientoPermitidoParaMi(Escaque destino) {}
		@Override protected void verificarCaminoLibre(Escaque destino) {}
	} */

}
