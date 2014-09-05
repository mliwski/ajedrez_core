package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.DestinoOcupablePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;

public class PeonDestinoOcupablePrecondition extends MovimientoPeonPrecondition {
	
	private DestinoOcupablePrecondition destinoOcupable = new DestinoOcupablePrecondition();
	private PeonDestinoOcupableChain movimientoVerticalChain = new MovimientoVerticalChain();
	private PeonDestinoOcupableChain destinoOcupableChain = new MovimientoDiagonalChain(movimientoVerticalChain);

	@Override
	protected void checkMovimientoPeonPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		// Este paso solo chequea que el destino este vacio u ocupado por un contrincante,
		// no revisa como come o mueve el peon
		destinoOcupable.check(tableroSnapshot, movimiento);
		
		destinoOcupableChain.check(tableroSnapshot, movimiento);
	}
}
