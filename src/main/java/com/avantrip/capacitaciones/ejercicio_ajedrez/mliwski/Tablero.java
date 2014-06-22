package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public interface Tablero {
	public Trebejo getTrebejo(Escaque escaque);
	public Escaque getEscaque(Trebejo trebejo);
	public boolean isEscaqueAmenazadoPorColor(Escaque destino, Color color);
	
	public void ejecutarMovimiento(Movimiento movimiento);
}