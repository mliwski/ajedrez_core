package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class DestinoOcupablePorPeonPrecondition extends MovimientoPeonPrecondition {
	
	private DestinoOcupablePrecondition destinoOcupable = new DestinoOcupablePrecondition();

	@Override
	protected void checkMovimientoPeonPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		// Este paso solo chequea que el destino este vacio u ocupado por un contrincante,
		// no revisa como come o mueve el peon
		destinoOcupable.check(tableroSnapshot, movimiento);
		
		//TODO: Mejorar la calidad de este codigo (chain) para evaluar el resto de las maneras 
		//		en que mueve o come el peon
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoDestino = tableroSnapshot.getTrebejo(destino);
		boolean destinoOcupado = trebejoDestino != null;
		boolean movimientoDiagonal = movimiento.getTipo().equals(TipoMovimiento.Diagonal);

		if( destinoOcupado && movimientoDiagonal == false ) {
			throw new IllegalArgumentException("El peon solo puede comer en diagonal");
		}
	}
}
