package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.DireccionNoPermitidaException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class PeonDireccionAdelantePrecondition extends MovimientoPeonPrecondition{
	
	@Override
	protected void checkMovimientoPeonPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque destino = movimiento.getDestino();
		Escaque origen = movimiento.getOrigen();
		Trebejo peon = tableroSnapshot.getTrebejo(origen);
		
		Integer signoColor = getSignoColor(peon);
		Integer signoMovimiento = getSignoMovimiento(destino, origen);
		
		if(signoColor.equals(signoMovimiento) == false) {
			throw new DireccionNoPermitidaException("El peon solo puede moverse hacia adelante.");
		}
	}

	private Integer getSignoColor(Trebejo peon) {
		Color color = peon.getColor();
		DireccionAtaque direccionAtaque = color.getDireccionAtaque();
		return direccionAtaque.getSigno();
	}

	private Integer getSignoMovimiento(Escaque destino, Escaque origen) {
		Integer distanciaMovimiento = origen.getDistanciaNumero(destino);
		Integer signoMovimiento = distanciaMovimiento / Math.abs(distanciaMovimiento);
		return signoMovimiento;
	}
}
