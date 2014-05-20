package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.piezas.Pieza;

public class CaminoLibrePrecondition extends MovimientoPrecondition {

	@Override
	public void check(Movimiento movimiento) {
		for (Escaque escaque : movimiento.getCamino()) {
			Pieza pieza = tablero.getPieza(escaque);
			if(pieza != null) {
				throw new MovimientoIlegalException("El camino por el que se intenta transitar esta ocupado (" + escaque + " : " + pieza + ")");
			}
		}
	}
	
}
