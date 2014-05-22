package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CaminoOcupadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class CaminoLibrePrecondition extends MovimientoPrecondition {

	@Override
	public void check(Movimiento movimiento) {
		super.check(movimiento);
		for (Escaque escaque : movimiento.getCamino()) {
			Trebejo trebejo = tablero.getTrebejo(escaque);
			if(trebejo != null) {
				throw new CaminoOcupadoException("El camino por el que se intenta transitar esta ocupado (" + escaque + " : " + trebejo + ")");
			}
		}
	}
	
}
