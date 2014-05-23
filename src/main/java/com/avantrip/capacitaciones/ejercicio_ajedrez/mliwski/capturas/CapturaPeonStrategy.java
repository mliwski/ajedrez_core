package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class CapturaPeonStrategy extends CapturaStrategy {

	@Override
	public Trebejo getTrebejoCapturado(Movimiento movimiento) {
		Trebejo trebejoCapturado = super.getTrebejoCapturado(movimiento);
		checkPreconditionsCaptura(movimiento, trebejoCapturado);
		
		if(trebejoCapturado == null) {
			//TODO: Evaluar si come con peon al paso;
		}
		return trebejoCapturado;
	}

	private void checkPreconditionsCaptura(Movimiento movimiento, Trebejo trebejoCapturado) {
		boolean movimientoDeCaptura = movimiento.getTipo().equals(TipoMovimiento.Diagonal);
		if (trebejoCapturado != null && movimientoDeCaptura == false) {
			throw new MovimientoIlegalException("El Peon solo come en diagonal");
		}
	}

}
