package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class TorreTest extends TrebejoTest {

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
		return new Torre(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoIgualColor() {
		return new Torre(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoDistintoColor() {
		Color colorContrincante = getColor().getContrincante();
		return new Torre(colorContrincante);
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
		return new Escaque('c',5);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejoContrincante() {
		return new Escaque('b',1);
	}
	
	@Override
	protected List<TipoMovimiento> getMovimientosPermitidos() {
		return Arrays.asList(TipoMovimiento.Horizontal, TipoMovimiento.Vertical);
	}
	
	@Override
	protected Integer getCantidadMaxima() {
		return 8;
	}
	// ↑↑↑ This methods are used by template method tests ↑↑↑
	
	@Override
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Torre(null);
	}
	
	@Override
	@Test
	public void shouldThrowExceptionBecauseCantidadMaximaSuperada() {
		// Este trebejo no tiene limiter por cantidad maxima
	}
}
