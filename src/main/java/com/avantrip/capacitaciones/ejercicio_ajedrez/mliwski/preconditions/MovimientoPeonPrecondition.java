package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public abstract class MovimientoPeonPrecondition extends MovimientoPrecondition {

	@Override
	protected final void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		checkIsPeonPrecondition(tableroSnapshot, movimiento);
		checkMovimientoPeonPreconditions(tableroSnapshot, movimiento);
	}

	private void checkIsPeonPrecondition(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejoOrigen = tableroSnapshot.getTrebejo(origen);
		
		if(trebejoOrigen instanceof Peon == false) {
			throw new IllegalArgumentException("Esta precondicion solo es utilizable por un Peon");
		}
	}
	
	protected abstract void checkMovimientoPeonPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento);

}
