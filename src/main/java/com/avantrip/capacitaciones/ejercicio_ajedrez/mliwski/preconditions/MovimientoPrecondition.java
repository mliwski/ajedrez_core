package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public abstract class MovimientoPrecondition {
	//FIXME: Es una mala practica, no todos necesitan al tablero y ademas huele feo como esta implementado
	protected Tablero tablero;
	
	public MovimientoPrecondition() {
		this.tablero = Tablero.getInstance();
	}

	public void check(Movimiento movimiento) {
		if(movimiento == null) {
			throw new IllegalArgumentException("Se nesecita un movimiento para poder evaluar si cumple la precondicion");
		}
	};

	public Tablero getTablero() {
		return tablero;
	}
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	
}
