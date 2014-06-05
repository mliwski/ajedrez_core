package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoNoPermitidoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class TipoMovimientoCorrectoPrecondition extends MovimientoPrecondition{
	private List<TipoMovimiento> tiposEsperados;

	public TipoMovimientoCorrectoPrecondition(List<TipoMovimiento> tiposEsperados) {
		super();
		this.tiposEsperados = tiposEsperados;
	}

	@Override
	protected void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		TipoMovimiento tipo = movimiento.getTipo();
		if(tiposEsperados.contains(tipo) == false) {
			throw new TipoMovimientoNoPermitidoException("El trebejo que desea mover solo puede moverse en " + tiposEsperados + " (movimiento deseado=" + tipo + ")");
		}
		
	}
}
