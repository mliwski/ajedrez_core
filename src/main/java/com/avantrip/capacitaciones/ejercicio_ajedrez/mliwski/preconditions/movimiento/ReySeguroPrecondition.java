package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class ReySeguroPrecondition extends MovimientoPrecondition {

	@Override
	protected void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejo = tableroSnapshot.getTrebejo(origen);
		Color color = trebejo.getColor();
		Color colorContrincante = color.getContrincante();
		
		Rey rey = new Rey(color);
		Escaque escaqueRey = tableroSnapshot.getEscaque(rey);
		
		if(tableroSnapshot.isEscaqueAmenazadoPorColor(escaqueRey, colorContrincante)){
			throw new ReyAmenazadoException("El movimiento que se intenta realizar deja al rey en jaque");
		}
	}
}
