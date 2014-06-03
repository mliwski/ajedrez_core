package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class DestinoOcupablePorPeonPrecondition extends DestinoOcupablePrecondition {
	
	@Override
	protected void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		checkPeonPrecondition(tableroSnapshot, movimiento);
		super.checkMovimientoPreconditions(tableroSnapshot, movimiento);
		
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoDestino = tableroSnapshot.getTrebejo(destino);
		boolean destinoOcupado = trebejoDestino != null;
		boolean movimientoDiagonal = movimiento.getTipo().equals(TipoMovimiento.Diagonal);

		//TODO: Mejorar la calidad de este codigo (chain o algun strategy)
		if( destinoOcupado && movimientoDiagonal == false ) {
			throw new IllegalArgumentException("El peon solo puede comer en diagonal");
		} else if( destinoOcupado == false && movimientoDiagonal ) {
			if( isPeonAlPaso() == false ) {
				throw new IllegalArgumentException("El peon solo puede comer en diagonal pasante por peon al paso");
			}
		}
	}

	private void checkPeonPrecondition(TableroSnapshot tableroSnapshot,
			Movimiento movimiento) {
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejoOrigen = tableroSnapshot.getTrebejo(origen);
		
		if(trebejoOrigen instanceof Peon == false) {
			throw new IllegalArgumentException("Esta precondicion solo es utilizable por un Peon");
		}
	}

	private boolean isPeonAlPaso() {
		//TODO: Implementar deteccion de peon al paso
		return false;
	}
}
