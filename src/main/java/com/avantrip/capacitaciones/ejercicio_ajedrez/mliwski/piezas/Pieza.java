package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.piezas;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.MovimientoPrecondition;

public abstract class Pieza {
	private Color color;
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	
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

	public final Color getColor() {
		return color;
	}

	public final void setColor(Color color) {
		if(color == null) {
			throw new IllegalStateException("El color de una pieza no puede ser inexistente");
		}
		
		this.color = color;
	}
	
	public final boolean isPiezaMovida() {
		return movimientos.isEmpty();
	}
	
}
