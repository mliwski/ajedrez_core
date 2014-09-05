package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoNoPermitidoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class MovimientoVerticalChain extends PeonDestinoOcupableChain {

	public MovimientoVerticalChain() {
		super();
	}
	
	public MovimientoVerticalChain(PeonDestinoOcupableChain next) {
		super(next);
	}

	@Override
	protected void checkPrecondition(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoDestino = tableroSnapshot.getTrebejo(destino);
		boolean destinoOcupado = trebejoDestino != null;
		boolean movimientoDiagonal = movimiento.getTipo().equals(TipoMovimiento.Diagonal);

		//TODO: Soportar peon al paso (utilizar fila segun tipo de ataque e info del tablero snapshot)
		if( movimientoDiagonal && destinoOcupado == false ) {
			throw new TipoMovimientoNoPermitidoException("El peon solo puede moverse en diagonal si va a comer.");
		}
		
	}

}
