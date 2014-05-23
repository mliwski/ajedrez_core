package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategy;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.MovimientoPrecondition;

public abstract class Trebejo {
	private Color color;
	//TODO: Cambiar a Stack/Deque para cuando despues de realizar el movimiento dejo en jaque 
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	private CapturaStrategy capturaStrategy;
	
	public Trebejo(Color color) {
		this.color = color;
		this.capturaStrategy = new CapturaStrategy();
	}

	public final void addMovimiento(Movimiento movimiento) {
		movimientos.add(movimiento);
	}
	
	public final void checkPreconditions(Movimiento movimiento) {
		List<MovimientoPrecondition> movimientoPreconditions = getMovimientoPreconditions();
		for (MovimientoPrecondition precondition : movimientoPreconditions) {
			precondition.check(movimiento);
		}
	}; 
	protected abstract List<MovimientoPrecondition> getMovimientoPreconditions();

	public CapturaStrategy getCapturaStrategy() {
		return capturaStrategy;
	}
	
	public final Color getColor() {
		return color;
	}

	public final boolean isTrebejoMovido() {
		return movimientos.isEmpty();
	}
	
}
