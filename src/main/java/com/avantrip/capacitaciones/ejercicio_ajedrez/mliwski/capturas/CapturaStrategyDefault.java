package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class CapturaStrategyDefault implements CapturaStrategy {

	public Trebejo getTrebejoCapturado(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoCapturado = tableroSnapshot.getTrebejo(destino);
		return trebejoCapturado;
	}; 

}
