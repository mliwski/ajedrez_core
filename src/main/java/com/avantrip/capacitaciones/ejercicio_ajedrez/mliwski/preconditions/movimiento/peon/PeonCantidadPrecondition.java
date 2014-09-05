package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.CantidadMaximaSuperadaException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.CantidadMaximaPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class PeonCantidadPrecondition extends MovimientoPeonPrecondition{
	private static final Integer MOVIMIENTO_DOBLE = 2;
	MovimientoPrecondition cantidadMaxima = new CantidadMaximaPrecondition(MOVIMIENTO_DOBLE);
	
	@Override
	protected void checkMovimientoPeonPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		cantidadMaxima.check(tableroSnapshot, movimiento);
		checkMovimientoDobleOnFirstMove(tableroSnapshot, movimiento);
	}

	private void checkMovimientoDobleOnFirstMove(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque origen = movimiento.getOrigen();
		Trebejo peon = tableroSnapshot.getTrebejo(origen);
		
		boolean firstMoveAttempt = movimiento.getCantidad() == MOVIMIENTO_DOBLE;
		if(firstMoveAttempt && peon.isTrebejoMovido()) {
			throw new CantidadMaximaSuperadaException("El peon solo puede mover dos posiciones en el primer movimiento");
		}
	}

}
