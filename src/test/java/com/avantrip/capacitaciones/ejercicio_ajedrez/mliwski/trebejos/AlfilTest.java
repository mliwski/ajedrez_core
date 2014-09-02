package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class AlfilTest extends TrebejoTest {

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
		return new Alfil(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoIgualColor() {
		return new Alfil(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoDistintoColor() {
		Color colorContrincante = getColor().getContrincante();
		return new Alfil(colorContrincante);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejo() {
		return new Escaque('c', 1);
	}
	
	@Override
	protected Escaque getDestino() {
		return new Escaque('e',3);
	}
	
	// --- ↓↓↓ This methods are used by rey seguro tests ↓↓↓
	@Override
	protected Trebejo getTrebejoContrincante() {
		Color colorContrincante = getColor().getContrincante();
		return new Torre(colorContrincante);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejoContrincante() {
		return new Escaque('a',1);
	}
	// --- ↑↑↑ This methods are used by rey seguro tests ↑↑↑
	
	@Override
	protected List<TipoMovimiento> getMovimientosPermitidos() {
		return Arrays.asList(TipoMovimiento.Diagonal);
	}
	
	@Override
	protected Integer getCantidadMaxima() {
		return 8;
	}
	// ↑↑↑ This methods are used by template method tests ↑↑↑
	
	@Override
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Alfil(null);
	}
	
	@Override
	@Test
	public void shouldThrowExceptionBecauseCantidadMaximaSuperada() {
		// Este trebejo no tiene limiter por cantidad maxima
	}
}