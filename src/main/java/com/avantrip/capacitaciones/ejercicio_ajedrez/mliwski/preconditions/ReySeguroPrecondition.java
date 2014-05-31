package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class ReySeguroPrecondition extends MovimientoPrecondition {

	//FIXME: Refactorizar. Para las implementaciones de rey en peligro utilizar snapshot de tablero!!!
	@Override
	public void check(Movimiento movimiento) {
		super.check(movimiento);
//		Tablero tableroCopy = null;
//		try {
//			tableroCopy = (Tablero) tablero.clone();
//		} catch (CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Escaque origen = movimiento.getOrigen();
//		Trebejo trebejo = tableroCopy.getTrebejo(origen);
//		Color color = trebejo.getColor();
//		Color colorContrincante = color.getContrincante();
//		Escaque rey = tableroCopy.getEscaqueRey(color);
//		if(tablero.isEscaqueAmenazadoPorColor(rey, colorContrincante)){
//			throw new ReyAmenazadoException("El movimiento que se intenta realizar deja al rey en jaque");
//		}
	}
}
