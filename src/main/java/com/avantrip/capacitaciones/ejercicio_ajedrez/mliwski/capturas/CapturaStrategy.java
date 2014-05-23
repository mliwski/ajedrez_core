package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class CapturaStrategy {
	public Trebejo getTrebejoCapturado(Movimiento movimiento) {
		Tablero tablero = Tablero.getInstance();
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoCapturado = tablero.getTrebejo(destino);
		return trebejoCapturado;
	}; 
}
