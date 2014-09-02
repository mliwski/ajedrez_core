package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class PeonTest extends TrebejoTest {

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
		return new Peon(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoIgualColor() {
		return new Peon(getColor());
	}
	
	@Override
	protected Trebejo getMismoTrebejoDistintoColor() {
		Color colorContrincante = getColor().getContrincante();
		return new Peon(colorContrincante);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejo() {
		return new Escaque('d', 2);
	}
	
	@Override
	protected Escaque getDestino() {
		return new Escaque('d',3);
	}
	
	// --- ↓↓↓ This methods are used by rey seguro tests ↓↓↓
	@Override
	protected Trebejo getTrebejoContrincante() {
		Color colorContrincante = getColor().getContrincante();
		return new Alfil(colorContrincante);
	}
	
	@Override
	protected Escaque getEscaqueDeTrebejoContrincante() {
		return new Escaque('a',5);
	}
	// --- ↑↑↑ This methods are used by rey seguro tests ↑↑↑
	
	@Override
	protected List<TipoMovimiento> getMovimientosPermitidos() {
		return Arrays.asList(TipoMovimiento.Vertical, TipoMovimiento.Diagonal);
	}
	
	@Override
	protected Integer getCantidadMaxima() {
		return 2;
	}
	// ↑↑↑ This methods are used by template method tests ↑↑↑
	
	//FIXME: Agregar los test especializados de este trebejo
//	@Test(expected=DireccionNoPermitidaException.class)
//	public void shouldThrowExceptionBecauseDireccionNoPermitida() {
//		Movimiento movimiento = new Movimiento(getDestino(), getEscaqueDeTrebejo());
//		getTrebejo().checkPreconditions(getTableroSnapshot(), movimiento);
//	}
	
	@Override
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseNoColor() {
		new Peon(null);
	}
}