package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class DestinoOcupablePorPeonPrecondition extends DestinoOcupablePrecondition {
	
	@Override
	public void check(Movimiento movimiento) {
		checkArgumentPreconditions(movimiento);
		
		super.check(movimiento);
		
		
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoDestino = tablero.getTrebejo(destino);
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

	private boolean isPeonAlPaso() {
		//TODO: Implementar deteccion de peon al paso
		return false;
	}

	private void checkArgumentPreconditions(Movimiento movimiento) {
		if(movimiento == null) {
			throw new IllegalArgumentException("Se necesita un movimiento para poder evaluar si cumple la precondicion");
		}
		
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejoOrigen = tablero.getTrebejo(origen);
		
		if(trebejoOrigen instanceof Peon == false) {
			throw new IllegalArgumentException("Esta precondicion solo es utilizable por un Peon");
		}
	}
	
}
