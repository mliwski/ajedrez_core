package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DestinoNoOcupableException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class DestinoOcupablePrecondition extends MovimientoPrecondition{

	@Override
	protected void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejoOrigen = tableroSnapshot.getTrebejo(origen);
		Color colorOrigen = trebejoOrigen.getColor();
		
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoDestino = tableroSnapshot.getTrebejo(destino);
		boolean destinoOcupado = trebejoDestino != null;
		Color colorDestino = destinoOcupado ? trebejoDestino.getColor() : null;
		
		if(destinoOcupado && colorDestino.equals(colorOrigen)){
			throw new DestinoNoOcupableException("El escaque destino esta ocupado por un trebejo el mismo color");
		}
	}
	
}
