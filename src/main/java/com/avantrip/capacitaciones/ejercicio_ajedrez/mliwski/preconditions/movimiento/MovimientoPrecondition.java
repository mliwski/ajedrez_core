package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public abstract class MovimientoPrecondition {

	public final void check(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		checkArgumentPreconditions(tableroSnapshot, movimiento);
		checkMovimientoPreconditions(tableroSnapshot, movimiento);
	}

	private void checkArgumentPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		if(tableroSnapshot == null) {
			throw new IllegalArgumentException("Se nesecita un tablero para poder evaluar si cumple la precondicion");
		}
		if(movimiento == null) {
			throw new IllegalArgumentException("Se nesecita un movimiento para poder evaluar si cumple la precondicion");
		}
	};
	
	protected abstract void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento);
}
