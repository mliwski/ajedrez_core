package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class CaballoTest extends TrebejoTest {

	@Before
	public void beforeEveryTest() {
		super.beforeEveryTest();
	}

	//↓↓↓ This methods are used by template method tests ↓↓↓
	@Override
	protected Color getColor() {
		return Color.Blanco;
	}

	@Override
	protected Trebejo getTrebejo() {
		return new Caballo(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoIgualColor() {
		return new Caballo(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoDistintoColor() {
		Color colorContrincante = getColor().getContrincante();
		return new Caballo(colorContrincante);
	}
	
	@Override
	protected Trebejo getTrebejoContrincante() {
		Color colorContrincante = getColor().getContrincante();
		return new Torre(colorContrincante);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejo() {
		return new Escaque('c', 1);
	}

	@Override
	protected Escaque getDestino() {
		return new Escaque('d',3);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejoContrincante() {
		return new Escaque('b',1);
	}
	
	@Override
	protected List<TipoMovimiento> getMovimientosPermitidos() {
		return Arrays.asList(TipoMovimiento.Ele);
	}
	
	@Override
	protected Integer getCantidadMaxima() {
		return 1;
	}
	// ↑↑↑ This methods are used by template method tests ↑↑↑
	
	@Override
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Caballo(null);
	}
	
	@Override
	@Test
	public void shouldThrowExceptionBecauseCaminoOcupado() {
		//Este trebejo no tiene que tirar una excepcion, porque el camino siempre esta libre
	}
}
