package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class MovimientoDiagonalChain extends PeonDestinoOcupableChain {

	public MovimientoDiagonalChain() {
		super();
	}
	
	public MovimientoDiagonalChain(PeonDestinoOcupableChain next) {
		super(next);
	}

	@Override
	protected void checkPrecondition(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoDestino = tableroSnapshot.getTrebejo(destino);
		boolean destinoOcupado = trebejoDestino != null;
		boolean movimientoVertical = movimiento.getTipo().equals(TipoMovimiento.Vertical);

		if( movimientoVertical && destinoOcupado == true ) {
			throw new MovimientoIlegalException("El peon solo puede comer en diagonal.");
		}
		
	}

}
