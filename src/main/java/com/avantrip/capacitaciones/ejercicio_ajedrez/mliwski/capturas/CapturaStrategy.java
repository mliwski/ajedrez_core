package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public interface CapturaStrategy {
	public Trebejo getTrebejoCapturado(TableroSnapshot tableroSnapshot, Movimiento movimiento); 
}
