package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public abstract class MovimientoPrecondition {
	protected Tablero tablero;
	
	public MovimientoPrecondition() {
		this.tablero = Tablero.getInstance();
	}

	public abstract void check(Movimiento movimiento);

	public Tablero getTablero() {
		return tablero;
	}
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	
}
