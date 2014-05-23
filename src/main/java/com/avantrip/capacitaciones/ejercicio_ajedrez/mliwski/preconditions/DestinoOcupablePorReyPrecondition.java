package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class DestinoOcupablePorReyPrecondition extends DestinoOcupablePrecondition {
	
	@Override
	public void check(Movimiento movimiento) {
		checkArgumentPreconditions(movimiento);
		
		super.check(movimiento);
		
		Escaque destino = movimiento.getDestino();
		boolean destinoAmenazado = tablero.isAmenazado(destino);
		
		if(destinoAmenazado) {
			throw new MovimientoIlegalException("El rey no puede moverse a un escaque donde se le pueda hacer jaque mate");
		}
	}

	private void checkArgumentPreconditions(Movimiento movimiento) {
		if(movimiento == null) {
			throw new IllegalArgumentException("Se nesecita un movimiento para poder evaluar si cumple la precondicion");
		}
		
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejoOrigen = tablero.getTrebejo(origen);
		
		if(trebejoOrigen instanceof Rey == false) {
			throw new IllegalArgumentException("Esta precondicion solo es utilizable por un Rey");
		}
	}
	
}
