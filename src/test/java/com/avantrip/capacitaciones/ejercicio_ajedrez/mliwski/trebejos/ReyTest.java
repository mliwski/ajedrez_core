package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class ReyTest extends TrebejoTest {

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
		return new Rey(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoIgualColor() {
		return new Rey(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoDistintoColor() {
		Color colorContrincante = getColor().getContrincante();
		return new Rey(colorContrincante);
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
		return new Escaque('b',2);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejoContrincante() {
		return new Escaque('b',1);
	}
	
	@Override
	protected List<TipoMovimiento> getMovimientosPermitidos() {
		return Arrays.asList(TipoMovimiento.Horizontal, TipoMovimiento.Vertical, TipoMovimiento.Diagonal);
	}
	
	@Override
	protected Integer getCantidadMaxima() {
		return 1;
	}
	// ↑↑↑ This methods are used by template method tests ↑↑↑
	
	@Override
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Rey(null);
	}	
}
