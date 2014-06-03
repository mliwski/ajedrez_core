package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public class CantidadMaximaPrecondition extends MovimientoPrecondition{
	private Integer cantidadMaxima;

	public CantidadMaximaPrecondition(Integer cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	@Override
	protected void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Integer cantidad = movimiento.getCantidad();
		if(cantidad > cantidadMaxima ) {
			throw new MovimientoIlegalException("El trebejo que desea mover solo puede moverse hasta " + cantidadMaxima + " veces (cantidad deseada=" + cantidad + ")");
		}
	}
}
